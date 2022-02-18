/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import database.KoneksiDatabase;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class ManageJadwalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
//  button element    ===============================================================================================
     @FXML 
    private Button informasiJadwal;
     
     @FXML
    private Button Keluar;
     
     @FXML
    private Button TambahJadwal;
     
     @FXML
    private Button ManageJadwal;
     
     @FXML
    private Button Login;
     
    @FXML
    private Button dashboardJadwal;
    
     @FXML
    private Button tambahAkunBTN;
     
//    input element ===============================================================================================
       @FXML
    private TextField hari;
       
     @FXML
    private TextField kelas;

    @FXML
    private TextField kode;

    @FXML
    private TextField lama;
    
    @FXML
    private TextField keyword;
    
//===================================================================================================================
      @FXML
    private TableView<getDataJadwal> tableViewJadwal;

      @FXML
    private TableColumn<getDataJadwal, String> columnHari;
      
      @FXML
    private TableColumn<getDataJadwal, String> columnNumber;

    @FXML
    private TableColumn<getDataJadwal, String> columnJam;

    @FXML
    private TableColumn<getDataJadwal, String> columnKelas;

    @FXML
    private TableColumn<getDataJadwal, String> columnKode;
    
    
    ObservableList<getDataJadwal> getDataJadwalObservableList = FXCollections.observableArrayList();
    
//    ===================================================================================================
//    Global Variable
    int id_data;
    
    Alert alert = new Alert(AlertType.INFORMATION);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getData();
    }  
    
    
    public void getData(){
        try{
            String r_hari,r_kelas,r_jam,r_kode;
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal");
            int number = 1;
            
            while(rst.next()){
                String queryKelas = rst.getString("kelas");
                String queryHari = rst.getString("hari");
                String queryJam = rst.getString("jam");
                String queryKode = rst.getString("kode");
                int queryId = rst.getInt("id");
                
                getDataJadwalObservableList.add(new getDataJadwal(number,queryId,queryKelas,queryHari,queryJam,queryKode));
                number +=1;
            }
            
            columnHari.setCellValueFactory(new PropertyValueFactory<>("hari"));
            columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
            columnKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
            columnJam.setCellValueFactory(new PropertyValueFactory<>("jam"));
            columnKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
            
            tableViewJadwal.setItems(getDataJadwalObservableList);
            
            tableViewJadwal.setOnMouseClicked(event->{
               this.events();
            });
                                           
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
//    get data when row clicked ==============================================================================================================
    private void events(){
        for(getDataJadwal getData: tableViewJadwal.getSelectionModel().getSelectedItems()){
            for(int i=0;i<1;i++){
                hari.setText(getData.getHari());
                kelas.setText(getData.getKelas());
                kode.setText(getData.getKode());
                lama.setText(getData.getJam());
                this.id_data = getData.getId();
            }
        }
    }
    

//hapus data  =================================================================================================================================
    @FXML
    void deleteData(ActionEvent event){
        try{
            String sql = "DELETE FROM jadwal WHERE id="+this.id_data; 
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            alert.setTitle("Berhasil!!");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menghapus Data");
            alert.showAndWait();
            
            kelas.setText("");
            lama.setText("");
            kode.setText("");
            hari.setText("");
                    
            tableViewJadwal.getItems().clear();
            this.getData();
        }catch(SQLException e){
            System.out.print(e);
        }
    }
    
// edit data ==================================================================================================================================
    @FXML
    void updateData(ActionEvent event){        
        try{
//          get data
            String _kelas = kelas.getText();
            String _lama = lama.getText();
            String _kode = kode.getText();
            String _hari = hari.getText();
            
            String sql = "UPDATE jadwal SET hari='"+_hari+"',kelas='"+_kelas+"',jam='"+_lama+"',kode='"+_kode+"' WHERE id='"+this.id_data+"'";
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            alert.setTitle("Berhasil!!");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Memperbarui Data");
            alert.showAndWait();
            
            kelas.setText("");
            lama.setText("");
            kode.setText("");
            hari.setText("");
            
            tableViewJadwal.getItems().clear();
            this.getData();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
   
  
//   live searching  ========================================================================================================
    @FXML
    void searchData(ActionEvent event) {  
        try{
            tableViewJadwal.getItems().clear();
            String key = keyword.getText().trim();
            int lengthKey = key.length();
        
            String r_hari,r_kelas,r_jam,r_kode;
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            String sql;
            if(lengthKey==0){
                sql = "SELECT * FROM jadwal";
            }else{
                 sql = "SELECT * FROM jadwal WHERE kode='"+key+"'";
            }
            java.sql.ResultSet rst = stm.executeQuery(sql);
            int number = 1;
            
            while(rst.next()){
                String queryKelas = rst.getString("kelas");
                String queryHari = rst.getString("hari");
                String queryJam = rst.getString("jam");
                String queryKode = rst.getString("kode");
                int queryId = rst.getInt("id");
                
                getDataJadwalObservableList.add(new getDataJadwal(number,queryId,queryKelas,queryHari,queryJam,queryKode));
                number +=1;
            }
            
            columnHari.setCellValueFactory(new PropertyValueFactory<>("hari"));
            columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
            columnKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
            columnJam.setCellValueFactory(new PropertyValueFactory<>("jam"));
            columnKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
            
            tableViewJadwal.setItems(getDataJadwalObservableList);
            
                                           
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
        
    }
    
    
//    move page ===============================================================================================================================
    @FXML
    void informasiPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("DashboardLogin.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Dashboard");
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void tambahPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("AddJadwal.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Tambah Jadwal");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void managePage(ActionEvent event) {
         try{
            Parent parent = FXMLLoader.load(getClass().getResource("ManageJadwal.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Manage Jadwal");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void loginPage(ActionEvent event) {
         try{
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Login");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void keluarPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Dashboard");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void dashboardPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Dashboard");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
      @FXML
    void tambahAkunPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("TambahAkun.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("Tambah Akun");
            window.setMaximized(true);
            window.setMaximized(true);
            window.centerOnScreen();
            window.setFullScreen(true);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

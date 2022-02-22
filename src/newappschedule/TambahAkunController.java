/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import database.KoneksiDatabase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import database.KoneksiDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class TambahAkunController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
//    ======================================================================================================================
        
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
     
      @FXML
    private Button b_tambahData;
      
       @FXML
    private TextField password;

    @FXML
    private TextField r_password;
    
     
    @FXML
    private TextField username;
    
    
//         @FXML
//    private TableView<getDataAkun> tableViewJadwal;
//      
//       @FXML
//    private TableColumn<getDataAkun, String> columnHari;
//
//    @FXML
//    private TableColumn<getDataAkun, String> columnKelas;
//
//    @FXML
//    private TableColumn<getDataAkun, String> columnNumber;  
  
     ObservableList<getDataAkun> dataAkunObservableList = FXCollections.observableArrayList();
//     =====================================================================================================================
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
     @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tableViewJadwal.getItems().clear();
//         tableViewJadwal.setItems(dataAkunObservableList);
         getData();
    }
    
    
     
    public void getData(){
        System.out.println("RUnnnnn");
        try{
           
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal");
            int number = 1;
            
            while(rst.next()){
               String queryUsername = rst.getString("kelas");
               String queryPassword = rst.getString("kode");  
               dataAkunObservableList.add(new getDataAkun(number,queryUsername,queryPassword));
               
               number +=1;
            }
            
//            columnNumber.setCellValueFactory(new PropertyValueFactory<>("no"));
//            columnHari.setCellValueFactory(new PropertyValueFactory<>("username"));
//            columnKelas.setCellValueFactory(new PropertyValueFactory<>("password"));
//            
//           tableViewJadwal.setItems(dataAkunObservableList);
                                           
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    
    
// =========================================================================================================================
//     add account backend fitur
    @FXML
    void insertData(ActionEvent event) {
           String textUsername = username.getText().trim();
           String textPassword = password.getText().trim();
           String textRPassword = r_password.getText().trim();
           
           
           if(textPassword.equals(textRPassword)){
               try{
                int id;
                String sql = "INSERT INTO akun VALUES(id,'"+textUsername+"','"+textRPassword+"')";
                java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
//            alert
                alert.setTitle("Berhasil!!");
                alert.setHeaderText(null);
                alert.setContentText("Berhasil Menambahkan Data");
                alert.showAndWait();
                
//            pengkosongan data akun
                username.setText("");
                password.setText("");
                r_password.setText("");

            }catch(SQLException e){
                //            alert
                JOptionPane.showMessageDialog(null, e);

            }
           }else{
//               JOptionPane.showMessageDialog(null, "Password doesn't Match!!");
                alert.setTitle("Warning!!");
                alert.setHeaderText(null);
                alert.setContentText("Password doesn't Match!!");
                alert.showAndWait();
           }

    }

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
    
     @FXML
    void laporanPage(ActionEvent event) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("Laporan.fxml"));
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

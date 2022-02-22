/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import java.sql.*;
import database.KoneksiDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class AddAkunController implements Initializable {
    
    @FXML
    private TableView<getDataAkun> tableViewAkun;
    
     @FXML
    private TableColumn<getDataAkun, String> colPassword;

    @FXML
    private TableColumn<getDataAkun, String> columnNumber;

    @FXML
    private TableColumn<getDataAkun, String> columnUsername;
    
     ObservableList<getDataAkun> getDataAkunObservableList = FXCollections.observableArrayList();
     
     
       @FXML
    private TextField t_password;

    @FXML
    private TextField t_rpassword;

    @FXML
    private TextField t_username;
    
    @FXML
    private TextField key;

    /**
     * Initializes the controller class.
     */
    
      Alert alert = new Alert(AlertType.INFORMATION);
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.getData();
    }    
    
    int id_data;
    
    
    //    get data
    public void getData(){
           try{
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("select * from akun");
            int number = 1;
            
            while(rst.next()){
                int id = rst.getInt("id");
                String Username = rst.getString("username");
                String Password = rst.getString("password");
                getDataAkunObservableList.add(new getDataAkun(id,number,Username,Password));
                
                number+=1;
            }
                columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
                columnNumber.setCellValueFactory(new PropertyValueFactory<>("no"));
                colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

                tableViewAkun.setItems(getDataAkunObservableList);
                
                tableViewAkun.setOnMouseClicked(event->{
                    this.events();
                });
               
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }  
    }
    
    
    //    get data when row clicked ==============================================================================================================
    private void events(){
        for(getDataAkun getData: tableViewAkun.getSelectionModel().getSelectedItems()){
            for(int i=0;i<1;i++){
                t_username.setText(getData.getUsername());
                t_password.setText(getData.getPassword());
                this.id_data = getData.getId();
            }
        }
    }
    
    
    @FXML
    void searchData(ActionEvent event) {
        try{
            
            tableViewAkun.getItems().clear();
            String keyword = key.getText().trim();
            int lengthKey = keyword.length();
        
            String r_hari,r_kelas,r_jam,r_kode;
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            String sql;
            if(lengthKey==0){
                sql = "SELECT * FROM akun";
            }else{
                 sql = "SELECT * FROM akun WHERE username='"+keyword+"'";
            }
            java.sql.ResultSet rst = stm.executeQuery(sql);
            int number = 1;
            
            while(rst.next()){
                int id = rst.getInt("id");
                String Username = rst.getString("username");
                String Password = rst.getString("password");
                getDataAkunObservableList.add(new getDataAkun(id,number,Username,Password));
                
                number+=1;
            }
                columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
                columnNumber.setCellValueFactory(new PropertyValueFactory<>("no"));
                colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

                tableViewAkun.setItems(getDataAkunObservableList);
                
               
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }  
    }
    
    
     @FXML
    void perbaruiData(ActionEvent event) {
          try{
//          get data
            String _username = t_username.getText();
            String _password = t_password.getText();
 
            
            String sql = "UPDATE akun SET username='"+_username+"',password='"+_password+"' WHERE id='"+this.id_data+"'";
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            alert.setTitle("Berhasil!!");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Memperbarui Data");
            alert.showAndWait();
            
            t_username.setText("");
            t_password.setText("");
            t_rpassword.setText("");
                    
            tableViewAkun.getItems().clear();
            this.getData();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    void tambahData(ActionEvent event) {
         String textUsername = t_username.getText().trim();
           String textPassword = t_password.getText().trim();
           String textRPassword = t_rpassword.getText().trim();
           
           
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
                    t_username.setText("");
                    t_password.setText("");
                    t_rpassword.setText("");

                    tableViewAkun.getItems().clear();
                    this.getData();

                }catch(SQLException e){
                    alert.setTitle("Warning!!");
                    alert.setHeaderText(null);
                    alert.setContentText(""+e);
                    alert.showAndWait();
                }
           }else{
                alert.setTitle("Warning!!");
                alert.setHeaderText(null);
                alert.setContentText("Password doesn't Match!!");
                alert.showAndWait();
           }
    }
    
    @FXML
    void hapusData(ActionEvent event) {
           try{
            String sql = "DELETE FROM akun WHERE id="+this.id_data; 
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            alert.setTitle("Berhasil!!");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menghapus Data");
            alert.showAndWait();
            
            t_username.setText("");
            t_password.setText("");
            t_rpassword.setText("");
                    
            tableViewAkun.getItems().clear();
            this.getData();
        }catch(SQLException e){
            System.out.print(e);
        }
    }

   
  
   
  
    
    
    
    
    
    
    //     change page  =========================================================================================================================
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
            Parent parent = FXMLLoader.load(getClass().getResource("AddAkun.fxml"));
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

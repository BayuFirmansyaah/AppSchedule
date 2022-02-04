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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class AddJadwalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    // koneksi    
//    private Connection conecction;
//    private Statement statement;
//    private ResultSet resultSet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
//        final String DB_URL = "jdbc:mysql://localhost/schedule";
//        final String USERNAME_DB = "root";
//        final String PASSWORD_DB = "";
        
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(DB_URL,USERNAME_DB,PASSWORD_DB);
//            statement = connection.createStatement();
//        } catch (ClassNotFoundException ex) {
//           System.out.println("Driver JDBC failed : "+ex);
//        } catch (SQLException ex) {
//            System.out.println("Error "+ex);
//        }
    }  
    
    
//    element
    @FXML
    private TextField hari_penggunaan;
    
    @FXML
    private TextField kode_laboratorium;

    @FXML
    private TextField lama_penggunaan;

    @FXML
    private TextField nama_kelas;

    @FXML
    private Button tambahData;
    
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
    private Button tambahDataBtn;


     
     
     
// Tambah Data    
     @FXML
    void addData(ActionEvent event) {
        String namaKelas = nama_kelas.getText().trim();
        String hariPenggunaan = hari_penggunaan.getText().trim();
        String lamaPenggunaan = lama_penggunaan.getText().trim();
        String kodeLaboratorium = kode_laboratorium.getText().trim();
        
        
//       kondisi bila semua inputan kosong namun tetap di kirim
        if(namaKelas.equals("")||
           hariPenggunaan.equals("")||
           lamaPenggunaan.equals("")||
           kodeLaboratorium.equals("")){
//          alert
            JOptionPane.showMessageDialog(null, "Data harus di isi semua!! ");

        }else{
            try{
                int id;
                String sql = "INSERT INTO jadwal VALUES(id,'"+hariPenggunaan+"','"+namaKelas+"','"+lamaPenggunaan+"','"+kodeLaboratorium+"')"; 
                java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
//            alert
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");

//            pengkosongan jadwal
                nama_kelas.setText("");
                lama_penggunaan.setText("");
                hari_penggunaan.setText("");
                kode_laboratorium.setText("");
            }catch(SQLException e){
                //            alert
                JOptionPane.showMessageDialog(null, e);

            }
            
        }
        
    }
     
     
     
//     change page
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

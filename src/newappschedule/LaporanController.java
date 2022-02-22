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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class LaporanController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Text counterlab;
    
     @FXML
    private Text counterKelas;
     
       @FXML
    private Text countingJam;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
        String countLab = counter("SELECT DISTINCT kode FROM jadwal");
        counterlab.setText(countLab);
        
        String countKelas = counter("SELECT DISTINCT kelas FROM jadwal ORDER BY kelas");
        counterKelas.setText(countKelas);
        
        String countJam = counterJam();
        countingJam.setText(countJam);
    }   
    
    
      @FXML
    private TableView<dataLaporan> showJadwal;

      @FXML
    private javafx.scene.control.TableColumn<dataLaporan, String> hari;
      
       @FXML
    private javafx.scene.control.TableColumn<dataLaporan, String> no;

    @FXML
    private javafx.scene.control.TableColumn<dataLaporan, String> jam;

    @FXML
    private javafx.scene.control.TableColumn<dataLaporan, String> kelas;

    @FXML
    private javafx.scene.control.TableColumn<dataLaporan, String> kode;
    
    
    ObservableList<dataLaporan> dataLaporanObservableList = FXCollections.observableArrayList();
    
    
    //   ===================================================================================================================================== 
//    get data
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
                
                dataLaporanObservableList.add(new dataLaporan(number,queryId,queryKelas,queryHari,queryJam,queryKode));
                number+=1;
            }
            
            hari.setCellValueFactory(new PropertyValueFactory<>("hari"));
            no.setCellValueFactory(new PropertyValueFactory<>("number"));
            kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
            jam.setCellValueFactory(new PropertyValueFactory<>("jam"));
            kode.setCellValueFactory(new PropertyValueFactory<>("kode"));
            
            showJadwal.setItems(dataLaporanObservableList);
                                           
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
//    count data
    public String counter(String sql){
        int counter = 0;
         
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery(sql);
            
            while(rst.next()){
                counter+=1;
            }
        }catch(SQLException e){
            System.out.println("Errro : "+e);
        }
         
        return ""+counter;
    }
    
    
     public String counterJam(){
        int counter = 0;
         
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal");
            while(rst.next()){
                counter+=Integer.parseInt(rst.getString("jam"));
            }
        }catch(SQLException e){
            System.out.println("Errro : "+e);
        }
         
        return ""+counter;
    }
    
    
    
    
//    ==============================================================================================================================
    
    
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

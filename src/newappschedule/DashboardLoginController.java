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
import java.util.ArrayList;
import java.util.Collections;
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
public class DashboardLoginController implements Initializable {

         @FXML 
    private Button informasiJadwal;
         
       @FXML
    private TextField keyword;
     
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
    private TableView<shortDataShow> showDataJadwal;
    
     @FXML
    private TableColumn<shortDataShow, String> columnJumat;

    @FXML
    private TableColumn<shortDataShow, String> columnKamis;

    @FXML
    private TableColumn<shortDataShow, String> columnRabu;

    @FXML
    private TableColumn<shortDataShow, String> columnSelasa;

    @FXML
    private TableColumn<shortDataShow, String> columnSenin;
    
      @FXML
    private TableColumn<shortDataShow, String> columnJam;
    
    
    ObservableList<shortDataShow> shortDataShowObservableList = FXCollections.observableArrayList();
    
//    ======================================================================================================================
//    Deklarasi Array
    int n=10;
    ArrayList<String> dataSenin = new ArrayList<String>();
    ArrayList<String> dataSelasa = new ArrayList<String>();
    ArrayList<String> dataRabu = new ArrayList<String>();
    ArrayList<String> dataKamis = new ArrayList<String>();
    ArrayList<String> dataJumat = new ArrayList<String>();
    ArrayList<Integer> lengthArray = new ArrayList<Integer>();
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            String r_hari,r_kelas,r_jam,r_kode;
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal WHERE kode='rpl1'");

            while(rst.next()){
                r_hari = rst.getString("hari");
                r_kelas = rst.getString("kelas");
                r_jam = rst.getString("jam");
                r_kode = rst.getString("kode");
       
                
                this.filterData(r_hari, r_kelas, r_jam, r_kode);             
            }
            
            String jSenin,jSelasa,jRabu,jKamis,jJumat;
            
//           cek length array after add data
            int lengthSenin = this.dataSenin.size();
            int lengthSelasa = this.dataSelasa.size();
            int lengthRabu = this.dataRabu.size();
            int lengthKamis = this.dataKamis.size();
            int lengthJumat = this.dataJumat.size();
   
//          tambahkan panjang array ke array baru  
            this.lengthArray.add(lengthSenin);
            this.lengthArray.add(lengthSelasa);
            this.lengthArray.add(lengthRabu);
            this.lengthArray.add(lengthKamis);
            this.lengthArray.add(lengthJumat);

//          cari nilai array paling panjang
            int longArray = Collections.max(lengthArray);

//          tambahkan nilai kosong untuk menyamakan array
            if(lengthSenin != longArray){
                while(lengthSenin!= longArray){
                    dataSenin.add(" ");
                    lengthSenin = this.dataSenin.size();
                }
            }
            
            if(lengthSelasa != longArray){
                while(lengthSelasa!= longArray){
                    dataSelasa.add(" ");
                    lengthSelasa = this.dataSelasa.size();
                }
            }
            
            if(lengthRabu != longArray){
                while(lengthRabu!= longArray){
                    dataRabu.add(" ");
                    lengthRabu = this.dataRabu.size();
                }
            }

            if(lengthKamis != longArray){
                while(lengthKamis!= longArray){
                    dataKamis.add(" ");
                    lengthKamis = this.dataKamis.size();
                }
            }
            
            if(lengthJumat != longArray){
                while(lengthJumat!= longArray){
                    dataJumat.add(" ");
                    lengthJumat = this.dataJumat.size();
                }
            }
            
            int lengthData = this.dataSenin.size();
            int jam = 1;
            
            
            for(int i=0;i<lengthData;i++){
                jSenin = this.dataSenin.get(i);
                jSelasa = this.dataSelasa.get(i);
                jRabu = this.dataRabu.get(i);
                jKamis = this.dataKamis.get(i);
                jJumat = this.dataJumat.get(i);
                
                shortDataShowObservableList.add(new shortDataShow(jam,jSenin,jSelasa,jRabu,jKamis,jJumat));
                
                jam+=1;
               
            }
            
            columnJam.setCellValueFactory(new PropertyValueFactory<>("number"));
            columnSenin.setCellValueFactory(new PropertyValueFactory<>("senin"));
            columnSelasa.setCellValueFactory(new PropertyValueFactory<>("selasa"));
            columnRabu.setCellValueFactory(new PropertyValueFactory<>("rabu"));
            columnKamis.setCellValueFactory(new PropertyValueFactory<>("kamis"));
            columnJumat.setCellValueFactory(new PropertyValueFactory<>("jumat"));
            
            showDataJadwal.setItems(shortDataShowObservableList);
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    } 
    
     public void filterData(String hari,String kelas,String jam,String kode){
        
        if(hari.equals("SENIN")){
            int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                 this.dataSenin.add(kelas);
            }
                      
        }else if(hari.equals("SELASA")){
            int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
               this.dataSelasa .add(kelas);
            }
            
        }else if(hari.equals("RABU")){
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
               this.dataRabu.add(kelas);
            }
            
        }else if(hari.equals("KAMIS")){
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.dataKamis.add(kelas);
            }
            
        }else if(hari.equals("JUMAT")){
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.dataJumat.add(kelas);
            }

        }
    }

    
    public int convertJam(String jam){
        int resultConvert = Integer.parseInt(jam);
        
        return resultConvert;
    }
    
    
// ======================================================================================================   
    
    @FXML
    void searchData(ActionEvent event) {
         showDataJadwal.getItems().clear();
        String key= keyword.getText().trim();
        int keyLength = key.length();
        
        try{
            
            this.dataSenin.clear();
            this.dataSelasa.clear();
            this.dataRabu.clear();
            this.dataKamis.clear();
            this.dataJumat.clear();
            this.lengthArray.clear();
            
            String r_hari,r_kelas,r_jam,r_kode;
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            String sql;
            if(keyLength == 0){
                sql = "SELECT * FROM jadwal WHERE kode='rpl1'";
            }else{
                 sql = "SELECT * FROM jadwal WHERE kode='"+key+"'";
            }
            
            java.sql.ResultSet rst = stm.executeQuery(sql);

            while(rst.next()){
                r_hari = rst.getString("hari");
                r_kelas = rst.getString("kelas");
                r_jam = rst.getString("jam");
                r_kode = rst.getString("kode");
       
                
                this.filterData(r_hari, r_kelas, r_jam, r_kode);             
            }
            
            String jSenin,jSelasa,jRabu,jKamis,jJumat;
            
//           cek length array after add data
            int lengthSenin = this.dataSenin.size();
            int lengthSelasa = this.dataSelasa.size();
            int lengthRabu = this.dataRabu.size();
            int lengthKamis = this.dataKamis.size();
            int lengthJumat = this.dataJumat.size();
   
//          tambahkan panjang array ke array baru  
            this.lengthArray.add(lengthSenin);
            this.lengthArray.add(lengthSelasa);
            this.lengthArray.add(lengthRabu);
            this.lengthArray.add(lengthKamis);
            this.lengthArray.add(lengthJumat);

//          cari nilai array paling panjang
            int longArray = Collections.max(lengthArray);

//          tambahkan nilai kosong untuk menyamakan array
            if(lengthSenin != longArray){
                while(lengthSenin!= longArray){
                    dataSenin.add(" ");
                    lengthSenin = this.dataSenin.size();
                }
            }
            
            if(lengthSelasa != longArray){
                while(lengthSelasa!= longArray){
                    dataSelasa.add(" ");
                    lengthSelasa = this.dataSelasa.size();
                }
            }
            
            if(lengthRabu != longArray){
                while(lengthRabu!= longArray){
                    dataRabu.add(" ");
                    lengthRabu = this.dataRabu.size();
                }
            }

            if(lengthKamis != longArray){
                while(lengthKamis!= longArray){
                    dataKamis.add(" ");
                    lengthKamis = this.dataKamis.size();
                }
            }
            
            if(lengthJumat != longArray){
                while(lengthJumat!= longArray){
                    dataJumat.add(" ");
                    lengthJumat = this.dataJumat.size();
                }
            }
            
            int lengthData = this.dataSenin.size();
            int jam = 1;
            
            
            for(int i=0;i<lengthData;i++){
                jSenin = this.dataSenin.get(i);
                jSelasa = this.dataSelasa.get(i);
                jRabu = this.dataRabu.get(i);
                jKamis = this.dataKamis.get(i);
                jJumat = this.dataJumat.get(i);
                
                shortDataShowObservableList.add(new shortDataShow(jam,jSenin,jSelasa,jRabu,jKamis,jJumat));
                
                jam+=1;
               
            }
            
            columnJam.setCellValueFactory(new PropertyValueFactory<>("number"));
            columnSenin.setCellValueFactory(new PropertyValueFactory<>("senin"));
            columnSelasa.setCellValueFactory(new PropertyValueFactory<>("selasa"));
            columnRabu.setCellValueFactory(new PropertyValueFactory<>("rabu"));
            columnKamis.setCellValueFactory(new PropertyValueFactory<>("kamis"));
            columnJumat.setCellValueFactory(new PropertyValueFactory<>("jumat"));
            
            showDataJadwal.setItems(shortDataShowObservableList);
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
// =======================================================================================================    
    

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

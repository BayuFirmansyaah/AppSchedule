/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import database.KoneksiDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class DashboardController implements Initializable {
    
    @FXML
    Button login;
    
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
    
    
    ObservableList<shortDataShow> shortDataShowObservableList = FXCollections.observableArrayList();
    
//    ======================================================================================================================
//    Deklarasi Array
    int n=10;
    ArrayList<String> dataSenin = new ArrayList<String>();
    ArrayList<String> dataSelasa = new ArrayList<String>();
    ArrayList<String> dataRabu = new ArrayList<String>();
    ArrayList<String> dataKamis = new ArrayList<String>();
    ArrayList<String> dataJumat = new ArrayList<String>();
    
 
      
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
                
            int lengthData = this.dataSenin.size();
            System.out.println(lengthData);
            
            for(int i=0;i<lengthData;i++){
                jSenin = this.dataSenin.get(i);
                jSelasa = this.dataSelasa.get(i);
                jRabu = this.dataRabu.get(i);
                jKamis = this.dataKamis.get(i);
                jJumat = this.dataJumat.get(i);
                shortDataShowObservableList.add(new shortDataShow(jSenin,jSelasa,jRabu,jKamis,jJumat));
                System.out.println(jSelasa);
            }
            
            columnSenin.setCellValueFactory(new PropertyValueFactory<>("senin"));
            columnSelasa.setCellValueFactory(new PropertyValueFactory<>("selasa"));
            columnRabu.setCellValueFactory(new PropertyValueFactory<>("rabu"));
            columnKamis.setCellValueFactory(new PropertyValueFactory<>("kamis"));
            columnJumat.setCellValueFactory(new PropertyValueFactory<>("jumat"));
            
            showDataJadwal.setItems(shortDataShowObservableList);
               
//            
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    } 
    
       
    public void handdleButonLogin () throws Exception{
//         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//         Stage window = (Stage) login.getScene().getWindow();
//         window.setScene(new Scene(root,789,500));
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
    
    
   
    
    
    
    
}

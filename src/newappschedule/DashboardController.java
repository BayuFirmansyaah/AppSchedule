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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class DashboardController implements Initializable {
    
    @FXML
    Button login;
    
    ArrayList<String> jadwalSenin = new ArrayList<String>();
    ArrayList<String> jadwalSelasa = new ArrayList<String>();
    ArrayList<String> jadwalRabu = new ArrayList<String>();
    ArrayList<String> jadwalKamis = new ArrayList<String>();
    ArrayList<String> jadwalJumat = new ArrayList<String>();
    ArrayList<String> jadwalLab = new ArrayList<String>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getData();
    } 
    
       
    public void handdleButonLogin () throws Exception{
//         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//         Stage window = (Stage) login.getScene().getWindow();
//         window.setScene(new Scene(root,789,500));
            this.consoleLog();
    }
    
    
    public void consoleLog(){
        System.out.print(this.jadwalSelasa);
    }
    
    
    public void getData(){
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
                
               
//            
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
    public void filterData(String hari,String kelas,String jam,String kode){
        if(hari == "SENIN"){
            int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.jadwalSenin.add(kelas);
            }
                      
        }else if(hari == "SELASA"){
            int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.jadwalSelasa.add(kelas);
            }
            
        }else if(hari == "RABU"){
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.jadwalRabu.add(kelas);
            }
            
        }else if(hari == "KAMIS"){
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.jadwalKamis.add(kelas);
            }
            
        }else{
           int totalJam = this.convertJam(jam);
            
            for(int i=1;i<=totalJam;i++){
                this.jadwalJumat.add(kelas);
            }

        }
    }

    
    public int convertJam(String jam){
        int resultConvert = Integer.parseInt(jam);
        
        return resultConvert;
    }
    
    
   
    
    
    
    
}

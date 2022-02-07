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

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Button login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    
   ArrayList<String> dataJadwal = new ArrayList<String>();
    
    public void handdleButonLogin () throws Exception{
//         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//         Stage window = (Stage) login.getScene().getWindow();
//         window.setScene(new Scene(root,789,500));


        try{
            String r_hari,r_kelas,r_jam,r_kode;
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal WHERE kode='RPL1'");
            if(rst.next()){
                while(rst.next()){
                    r_hari = rst.getString("hari");
                    r_kelas = rst.getString("kelas");
                    r_jam = rst.getString("jam");
                    r_kode = rst.getString("kode");
            
                    this.filterData(r_hari, r_kelas, r_jam, r_kode);
                }
            }else{
                System.out.println("Data gagal di tampilkan");
            }
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
    public void filterData(String hari,String kelas,String jam,String kode){
        if(hari == "SENIN"){
            int totalJam = this.convertJam(jam);
            System.out.println(hari+" | "+kelas+" | "+totalJam+" | "+kode);
        }else if(hari == "SELASA"){
            int totalJam = this.convertJam(jam);
            System.out.println(hari+" | "+kelas+" | "+totalJam+" | "+kode);
        }else if(hari == "RABU"){
            int totalJam = this.convertJam(jam);
            System.out.println(hari+" | "+kelas+" | "+totalJam+" | "+kode);
        }else if(hari == "KAMIS"){
            int totalJam = this.convertJam(jam);
            System.out.println(hari+" | "+kelas+" | "+totalJam+" | "+kode);
        }else{
            int totalJam = this.convertJam(jam);
            System.out.println(hari+" | "+kelas+" | "+totalJam+" | "+kode);
        }
    }
    
    
    public int convertJam(String jam){
        int resultConvert = Integer.parseInt(jam);
        
        return resultConvert;
    }
    
    
   
    
    
    
    
}

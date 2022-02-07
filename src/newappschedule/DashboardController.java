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
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM jadwal");
            if(rst.next()){
                System.out.println(rst);
            }else{
                System.out.println("Data gagal di tampilkan");
            }
        }catch(SQLException e){
            System.out.println(" Kode program salah");
        }
    }
    
    
   
    
    
    
    
}

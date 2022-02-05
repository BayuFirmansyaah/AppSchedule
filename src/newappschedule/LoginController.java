/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import database.KoneksiDatabase;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
// element
   @FXML
    private Button butonLogin;
   
   @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
     @FXML
    private Text notif_password;

    @FXML
    private Text notif_username;

    @FXML
    void actionLogin(ActionEvent event) {
           try{
               java.sql.Statement stm = (Statement)KoneksiDatabase.koneksiDB().createStatement();
               java.sql.ResultSet rst = stm.executeQuery("SELECT * FROM akun WHERE username='"+username.getText()+"'");
               if(rst.next()){
                    if(password.getText().equals(rst.getString("password"))){
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
                    }else{
                        notif_password.setText("password wrong");
                    }
                }
           }catch(SQLException e){
               notif_username.setText("username not found");
           }
    }
    
}

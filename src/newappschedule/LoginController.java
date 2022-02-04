/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import java.io.IOException;
import java.net.URL;
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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author bayu firmansyah
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
       
// koneksi    
    private Connection conecction;
    private Statement statement;
    private ResultSet resultSet;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/schedule";
        final String USERNAME_DB = "root";
        final String PASSWORD_DB = "";
        
        try{
            Class.forName(JDBC_Driver);
            Connection connection = DriverManager.getConnection(DB_URL,USERNAME_DB,PASSWORD_DB);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
           System.out.println("Driver JDBC failed");
        } catch (SQLException ex) {
            System.out.println("Error "+ex);
        }
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
        //            System.out.println(username.getText());
//            System.out.println(password.getText());
//            Parent parent = FXMLLoader.load(getClass().getResource("DashboardLogin.fxml"));
//            Scene scene = new Scene(parent);
//            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
//            window.setScene(scene);
//            window.setMaximized(true);
//            window.centerOnScreen();
//            window.setFullScreen(true);
//            window.show();
            String username_user = username.getText();
            String password_user = password.getText();
            
            if(username_user != "bayu"){
                notif_username.setText("username not found !!");
            }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

import java.net.URL;
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
        // TODO
    } 
    
    public void handdleButonLogin () throws Exception{
         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
         Stage window = (Stage) login.getScene().getWindow();
         window.setScene(new Scene(root,789,500));
    }
    
    
}

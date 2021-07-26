/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permis;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class Page_PermisController implements Initializable {

    @FXML
    private JFXButton FormPermis;
    @FXML
    private AnchorPane zonePermis;
    @FXML
    public static int id_user;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
    private void permisForm(ActionEvent event) throws IOException {
        
        Permis_formController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/Permis_form.fxml"));
        zonePermis.getChildren().removeAll();
        zonePermis.getChildren().setAll(fxml);
        
    }
    
     @FXML
    private void infoForm(ActionEvent event) throws IOException {
        
        InfoPermisFormController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/InfoPermisForm.fxml"));
        zonePermis.getChildren().removeAll();
        zonePermis.getChildren().setAll(fxml);
        
    }
    
    
}

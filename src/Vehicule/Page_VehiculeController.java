/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicule;

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
public class Page_VehiculeController implements Initializable {
    
    @FXML
    private AnchorPane zoneVehicule;
    @FXML
    public static int id_user;
    @FXML
    private JFXButton vehiculeForm;
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
    private void vehiculeForm(ActionEvent event) throws IOException {
        Vehicule_FormController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/Vehicule_Form.fxml"));
        zoneVehicule.getChildren().removeAll();
        zoneVehicule.getChildren().setAll(fxml);
        
    }

   @FXML
    private void infoVehiculeForm(ActionEvent event) throws IOException {
        InfoVehiculeFormController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/InfoVehiculeForm.fxml"));
        zoneVehicule.getChildren().removeAll();
        zoneVehicule.getChildren().setAll(fxml);
        
    } 
    
}

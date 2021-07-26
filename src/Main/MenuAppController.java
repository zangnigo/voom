/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Annonce.Page_AnnonceController;
import Client.Client_UpdateController;
import Client.Page_clientController;
import Message.Page_MessageController;
import Permis.Page_PermisController;
import Reservation.Page_reservationController;
import Vehicule.Page_VehiculeController;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class MenuAppController implements Initializable {

    @FXML
    private JFXButton deconnecte;
    @FXML
    private AnchorPane page;
    @FXML
    private JFXButton page_permis;
    @FXML
    private JFXButton annonce;
    public static int ids;
    @FXML
    private AnchorPane zone;
    
    public static Stage window;  
   
     /**
     * Initializes the controller class.
     * @param url
     * @param rb    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void close(ActionEvent event) {
         System.exit(0);
    }
    
    @FXML
    private void page_client(ActionEvent event) throws Exception {
        Client_UpdateController.id_user = ids ;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Client/Client_Update.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
    }

    @FXML
    private void page_permis(ActionEvent event) throws IOException {
        Page_PermisController.id_user = ids;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/Page_Permis.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
        
    }
    
     @FXML
    private void page_annonce(ActionEvent event) throws IOException {
        Page_AnnonceController.id_user = ids;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/Page_Annonce.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
        
    }
    
     @FXML
    private void page_reservation(ActionEvent event) throws IOException {
        Page_reservationController.id_user = ids;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Page_reservation.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
        
    }

    @FXML
    private void page_vehicule(ActionEvent event) throws IOException {
        Page_VehiculeController.id_user = ids;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/Page_Vehicule.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
        
    }
    
    @FXML
    private void deconnecte(ActionEvent event) throws IOException {
        ids = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Main/MainUI.fxml"));
        zone.getChildren().removeAll();
        zone.getChildren().setAll(fxml);
        
    }
    
    @FXML
    private void page_message(ActionEvent event) throws IOException {
        Page_MessageController.id_user = ids;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Message/Page_Message.fxml"));
        page.getChildren().removeAll();
        page.getChildren().setAll(fxml);
        
    }
    
}

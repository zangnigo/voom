/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

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
public class Page_reservationController implements Initializable {
    
    @FXML
    public static int id_user;
    @FXML
    private AnchorPane zoneReservation;
   

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
    private void reservationForm(ActionEvent event) throws IOException {
        Reservation_formController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Reservation_form.fxml"));
        zoneReservation.getChildren().removeAll();
        zoneReservation.getChildren().setAll(fxml);
        
    }    
    
     @FXML
    private void infoReservationForm(ActionEvent event) throws IOException {
        
        InfoReservationFormController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/InfoReservationForm.fxml"));
        zoneReservation.getChildren().removeAll();
        zoneReservation.getChildren().setAll(fxml);
        
    }    
    
}

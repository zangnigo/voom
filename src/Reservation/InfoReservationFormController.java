/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import Main.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InfoReservationFormController implements Initializable {

    @FXML
    private AnchorPane rest;
    @FXML
    private JFXButton rechercher;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXComboBox<String> dep;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> dest;
    public static int id_user;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        ResultSet rs ;
        
        String req1 = "SELECT * FROM lieux";
        
        try {
            
             stat = con.prepareStatement(req1);
             rs = stat.executeQuery();
             while(rs.next()){
                 
                 dep.getItems().add(rs.getString("nom"));
                 dest.getItems().add(rs.getString("nom"));
                 
                 }
        
    }   catch (SQLException ex) {    
           
        }
    }

    @FXML
    private void recherche(ActionEvent event) throws IOException {
        
        Date dat = null;
        dat = java.sql.Date.valueOf(date.getValue());
        String depart = dep.getValue();
        String destination = dest.getValue();
        
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        ResultSet rs ;
        
        String req1 = "SELECT * FROM trajetreserver WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        
        try {
            
            stat = con.prepareStatement(req1);
            stat.setInt(1, id_user);
            stat.setString(2, depart);
            stat.setString(3, destination);
            stat.setDate(4, dat);
            rs = stat.executeQuery();
            
            if(rs.next()){
                
                Mes_reservationsController.id_user = id_user;
                Mes_reservationsController.depart = depart;
                Mes_reservationsController.destination = destination;
                Mes_reservationsController.dat = dat;
                
                Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Mes_reservations.fxml"));
                rest.getChildren().removeAll();
                rest.getChildren().setAll(fxml);
                
            }
            
            else {
                
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Vous n'avez pas de réservation sur un trajet à cette date!");
                    alert.showAndWait();     
                
            }
            
        } catch (SQLException e) {
        }
        
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
       
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/InfoReservationForm.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
        
    }
    
}

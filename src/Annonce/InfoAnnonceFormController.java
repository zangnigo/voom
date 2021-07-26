/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonce;

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
public class InfoAnnonceFormController implements Initializable {

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
        
        String req1 ="SELECT * FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        
        Connection con = Connexion.getConnection();
        PreparedStatement st ;
        ResultSet rs ;
        
        try {
            
            if(depart.isEmpty() || destination.isEmpty()){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();    
                
            }
            
            else {
             st = con.prepareStatement(req1);
             st.setInt(1, id_user);
             st.setString(2, depart);
             st.setString(3, destination);
             st.setDate(4, dat);
             rs = st.executeQuery();
             
             if(rs.next()){
                 
                 Info_AnnonceController.id_user = id_user;
                 Info_AnnonceController.depart = depart;
                 Info_AnnonceController.destination = destination;
                 Info_AnnonceController.dat = dat;
                 
                 Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/Info_Annonce.fxml"));
                 rest.getChildren().removeAll();
                 rest.getChildren().setAll(fxml);
                 
             }
             
             else {
                 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Vous n'avez pas d'annonce de ce genre!");
                    alert.showAndWait();  
                 
             }
            }
            
        } catch (SQLException e) {
        }
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/InfoAnnonceForm.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
        
    }
    
}

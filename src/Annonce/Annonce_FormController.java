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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class Annonce_FormController implements Initializable {

    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXComboBox<String> dep;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> dest;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXComboBox<String> place;
    private ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5");
    @FXML
    private JFXComboBox<String> vehicule;
    @FXML
    private AnchorPane rest;
    @FXML
    public static int id_user;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          place.setItems(list);
          
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement st ;
         ResultSet ts ;
         ResultSet rs ;
         
         String req1 = "SELECT * FROM lieux";
         String req2 = "SELECT * FROM vehicule WHERE id_client = ?";
         try {
            
             stat = con.prepareStatement(req1);
             rs = stat.executeQuery();
             while(rs.next()){
                 
                 dep.getItems().add(rs.getString("nom"));
                 dest.getItems().add(rs.getString("nom"));
                 
                 }
            st = con.prepareStatement(req2);
            st.setInt(1, id_user);
            ts = st.executeQuery();
            while(ts.next()){
                
             vehicule.getItems().add(ts.getString("Matricule"));
                
            }
             
        } catch (SQLException e) {
        }
      
    }   
  @FXML  
 private void reset(ActionEvent e) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/Annonce_Form.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
     
 }
 
  @FXML  
 private void enregistrer(ActionEvent event) {
     
       Date dat = null;
       Time time = null;  
     dat = java.sql.Date.valueOf(date.getValue());
     time = java.sql.Time.valueOf(heure.getValue());
     String montant = prix.getText();
     String adresse = lieu.getText();
     String depart = dep.getValue();
     String destination = dest.getValue();
     String siege = place.getValue();
     String voiture = vehicule.getValue();
     
     String req1 ="SELECT * FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
     String req2 ="SELECT * FROM trajet WHERE id_client = ? AND Date = ? AND vehicule = ?";
     String sql = "INSERT INTO trajet (id_client,Depart,Destination,Heure_depart,Date,Lieu_rencontre,nbr_place,prix,vehicule) VALUES (?,?,?,?,?,?,?,?,?)";
     String sql1 = "INSERT INTO trajetmodifier (id_client,Depart,Destination,Heure_depart,Date,Lieu_rencontre,nbr_place,prix,vehicule) VALUES (?,?,?,?,?,?,?,?,?)";
     Connection con = Connexion.getConnection();
     PreparedStatement stat ;
     PreparedStatement stm ;
     PreparedStatement st ;
      PreparedStatement sta ;
     ResultSet rs ;
     ResultSet ts ;
     
      try {
          
          if (adresse.isEmpty() || montant.isEmpty() || voiture.isEmpty() || siege.isEmpty() || depart.isEmpty() || destination.isEmpty() ){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();    
            }
          else{
              
              stat = con.prepareStatement(req1);
              stat.setInt(1, id_user);
              stat.setString(2, depart);
              stat.setString(3, destination);
              stat.setDate(4, dat);  
              rs = stat.executeQuery();
              
              if(rs.next()){
                  
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Vous avez déjà une annonce de ce type, aller y jeter un oeil!");
                    alert.showAndWait();        
                }   
              else {
                  
                  stm = con.prepareStatement(req2);
                  stm.setInt(1, id_user);
                  stm.setDate(2, dat);
                  stm.setString(3, voiture);
                  ts = stm.executeQuery();
                  
                  if(ts.next()){
                      
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText("Erreur");
                      alert.setTitle("Alert");
                      alert.setContentText("Vous avez déjà utilisé cet véhicule dans une autre annonce en ce jour!");
                      alert.showAndWait();        
                  }
                  
                  else{
                      
                      st = con.prepareStatement(sql);
                      st.setInt(1, id_user);
                      st.setString(2, depart);
                      st.setString(3, destination);
                      st.setTime(4, time);
                      st.setDate(5, dat);
                      st.setString(6, adresse);
                      st.setString(7, siege);
                      st.setString(8, montant);
                      st.setString(9, voiture);
                      st.executeUpdate();
                      
                      sta = con.prepareStatement(sql1);
                      sta.setInt(1, id_user);
                      sta.setString(2, depart);
                      sta.setString(3, destination);
                      sta.setTime(4, time);
                      sta.setDate(5, dat);
                      sta.setString(6, adresse);
                      sta.setString(7, siege);
                      sta.setString(8, montant);
                      sta.setString(9, voiture);
                      sta.executeUpdate();
                      
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText("Information");
                      alert.setTitle("Alert");
                      alert.setContentText("Votre annoce à bien été ajouter!");
                      alert.showAndWait();
                      
                  }
                  
              }
              
          }
          
          
      } catch (SQLException e) {
      }
     
 }
    
}

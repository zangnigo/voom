/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicule;

import Main.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class Vehicule_FormController implements Initializable {
    
    @FXML
    private AnchorPane rest;
    @FXML
    private JFXTextField numCarteGrise;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField modele;
    @FXML
    private JFXTextField couleur;
    @FXML
    private JFXTextField matricule;
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
 private void reset(ActionEvent e) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/Vehicule_Form.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
     
 }   

@FXML
private void enregistrer(ActionEvent event){
    
     String marq = marque.getText();
     String model = modele.getText();
     String coul = couleur.getText();
     String matr = matricule.getText();
     String num = numCarteGrise.getText();
     
    String req = "SELECT * FROM vehicule WHERE id_client = ? AND Matricule = ?";
    String sql = "INSERT INTO vehicule (id_client,Marque,Modele,Matricule,Couleur,num_carte_grise) VALUES (?,?,?,?,?,?)";
    
     Connection con = Connexion.getConnection();
     PreparedStatement stat ;
     PreparedStatement st ;
     ResultSet rs ;
     
     try {
        
         if(marq.isEmpty() || model.isEmpty() || coul.isEmpty() || matr.isEmpty() || num.isEmpty()){
             
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();        
         }
         
         else {
             
             stat = con.prepareStatement(req);
             stat.setInt(1, id_user);
             stat.setString(2, matr);
             rs = stat.executeQuery();
             
             if(rs.next()){
                 
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Un véhicule est déjà enregistré sous ce matricule!");
               alert.showAndWait();
                 
             }
             
             else {
                 
                 st = con.prepareStatement(sql);
                 st.setInt(1, id_user);
                 st.setString(2, marq);
                 st.setString(3, model);
                 st.setString(4, matr);
                 st.setString(5, coul);
                 st.setString(6, num);
                 st.executeUpdate();
                 
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Information");
               alert.setTitle("Alert");
               alert.setContentText("Votre véhicule à été ajouter!");
               alert.showAndWait();
                 
             }
             
         }
         
    } catch (SQLException e) {
    }
}
    
}

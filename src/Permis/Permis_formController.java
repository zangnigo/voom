/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permis;

import Main.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
public class Permis_formController implements Initializable {

    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton update;
    @FXML
    private JFXTextField numPermis;
    @FXML
    private JFXComboBox<String> box;
    @FXML
    private JFXDatePicker dateD;
    @FXML
    private JFXDatePicker dateE;
    @FXML
    private AnchorPane rest;
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
         
         String sql = "SELECT * FROM categorie";
         try {
            stat = con.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()){
                 
                box.getItems().add(rs.getString("type"));
                
            }
        } catch (SQLException e) {
        }
         
    }  
    @FXML
 private void reset(ActionEvent e) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/Permis_form.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
     
 }
 
 @FXML
private void enregistrer(ActionEvent event){
     Date dat = null;
     Date tdate = null;
     dat = java.sql.Date.valueOf(dateE.getValue());
     tdate = java.sql.Date.valueOf(dateD.getValue());
     String num = numPermis.getText();
     
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement sta ;
         PreparedStatement st ;
         ResultSet ts ;
         
         String sql = "SELECT * FROM permis where id_client = ?";
         String req = "INSERT INTO permis (id_client,Categorie,Dat_delivrance,Dat_expiration,Num_permis) VALUES (?,?,?,?,?)";
         
      try {
           
          if (num.isEmpty() ){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();
              }
          else if (dat.equals(tdate)){
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur");
                alert.setTitle("Alert");
                alert.setContentText("Vous avez entré la même date!");
                alert.showAndWait();
              
          }
          else{
              
              stat = con.prepareStatement(sql);
              stat.setInt(1, id_user);
              ts = stat.executeQuery();
              if (ts.next()){
                  
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("Erreur");
                  alert.setTitle("Alert");
                  alert.setContentText("Vous avez déja un permis!");
                  alert.showAndWait();
              }
              else  {
                  
           
                  st = con.prepareStatement(req);
                  st.setInt(1, id_user);
                  st.setString(2, box.getValue());
                  st.setDate(3, tdate);
                  st.setDate(4, dat);
                  st.setString(5, num);                  
                  st.executeUpdate();
                  
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("Information");
                  alert.setTitle("Alert");
                  alert.setContentText("Votre permis à été ajouter!");
                  alert.showAndWait();
              }
              }
            
     } catch (SQLException e) {
     }
   
     
 }
 
    
}

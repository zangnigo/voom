/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicule;

import Main.Connexion;
import com.jfoenix.controls.JFXComboBox;
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
public class InfoVehiculeFormController implements Initializable {

    @FXML
    private AnchorPane zoneP;
    @FXML
    private JFXComboBox<String> box;
    public static int id_user;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Connection con = Connexion.getConnection();
         PreparedStatement st ;
         ResultSet rs ;
        
         String req2 = "SELECT * FROM vehicule WHERE id_client = ?";
         
         try {
            
            st = con.prepareStatement(req2);
            st.setInt(1, id_user);
            rs = st.executeQuery();
            while(rs.next()){
                
             box.getItems().add(rs.getString("Matricule"));
                
            }
             
        } catch (SQLException e) {
        }
    }    

    @FXML
    private void voir(ActionEvent event) throws IOException {
         
        String mat = box.getValue();
        
        try {
            
            if(mat.isEmpty()){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();    
                
            }
            
            else {
                
               Info_VehiculeController.matricule = mat ;
               Info_VehiculeController.id_user = id_user;
               Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/Info_Vehicule.fxml"));
               zoneP.getChildren().removeAll();
               zoneP.getChildren().setAll(fxml);
                
            }
            
        } catch (IOException e) {
        }      
}
    
}
    

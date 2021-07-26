/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicule;

import Main.Connexion;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class Info_VehiculeController implements Initializable {

    @FXML
    private AnchorPane pageV;
    @FXML
    private Text marque;
    @FXML
    private Text modele;
    @FXML
    private Text mat;
    @FXML
    private Text couleur;
    @FXML
    private Text num;
    public static int id_user;
    public static String matricule;

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
        
         String req2 = "SELECT * FROM vehicule WHERE id_client = ? AND Matricule = ?";
         
         try {
            
            st = con.prepareStatement(req2);
            st.setInt(1, id_user);
            st.setString(2, matricule);
            rs = st.executeQuery(); 
            
            if(rs.next()){
                
                marque.setText(rs.getString("Marque"));
                modele.setText(rs.getString("Modele"));
                mat.setText(rs.getString("Matricule"));
                couleur.setText(rs.getString("Couleur"));
                num.setText(rs.getString("num_carte_grise"));
                
            }
             
        } catch (SQLException e) {
        }
   }  
    
        
        

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
         Connection con = Connexion.getConnection();
         PreparedStatement st ;
         ResultSet rs ;
        
         String sql = "DELETE FROM vehicule WHERE id_client = ? AND Matricule = ?";
         
         try {
            
            st = con.prepareStatement(sql);
            st.setInt(1, id_user);
            st.setString(2, matricule);
            st.executeUpdate(); 
             
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Information");
                    alert.setTitle("Alert");
                    alert.setContentText("Véhicule supprimé!");
                    alert.showAndWait();
                    
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/InfoVehiculeForm.fxml"));
        pageV.getChildren().removeAll();
        pageV.getChildren().setAll(fxml);        
            
        } catch (SQLException e) {
        }
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Vehicule/InfoVehiculeForm.fxml"));
        pageV.getChildren().removeAll();
        pageV.getChildren().setAll(fxml);   
    }
    
}

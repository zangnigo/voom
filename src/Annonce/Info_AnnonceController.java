/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonce;

import Main.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
public class Info_AnnonceController implements Initializable {

    @FXML
    private AnchorPane pageV;
    @FXML
    private Text dep;
    @FXML
    private Text dest;
    @FXML
    private Text heure;
    @FXML
    private Text date;
    @FXML
    private Text lieu;
    @FXML
    private Text place;
    @FXML
    private Text prix;
    public static int id_user;
    public static String depart;
    public static String destination;
    public static Date dat;

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
         
         String req1 ="SELECT * FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        
         try {
            
             stat = con.prepareStatement(req1);
             stat.setInt(1, id_user);
             stat.setString(2, depart);
             stat.setString(3, destination);
             stat.setDate(4, dat);
             rs = stat.executeQuery();
             
             if(rs.next()){
                 Time time = rs.getTime("Heure_depart");
                 String heur = String.valueOf(time);
                 String dt = String.valueOf(dat);
                 dep.setText(rs.getString("Depart"));
                 dest.setText(rs.getString("Destination"));
                 heure.setText(heur);
                 date.setText(dt);
                 lieu.setText(rs.getString("Lieu_rencontre"));
                 place.setText(rs.getString("nbr_place"));
                 prix.setText(rs.getString("prix"));
                 
             }
            
             
        } catch (SQLException e) {
        }
    }    

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement st ;
         ResultSet rs ;
         
         String req1 ="DELETE FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
         String req ="DELETE FROM trajetmodifier WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
         
         try {
            
//             stat = con.prepareStatement(req1);
//             stat.setInt(1, id_user);
//             stat.setString(2, depart);
//             stat.setString(3, destination);
//             stat.setDate(4, dat);
//             stat.executeUpdate();
             
             st = con.prepareStatement(req);
             st.setInt(1, id_user);
             st.setString(2, depart);
             st.setString(3, destination);
             st.setDate(4, dat);
             st.executeUpdate();
             
             Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Annonce supprimée!");
                    alert.showAndWait(); 
                    
             Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/InfoAnnonceForm.fxml"));
               pageV.getChildren().removeAll();
               pageV.getChildren().setAll(fxml);       
             
        } catch (SQLException e) {
        }
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
               Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/InfoAnnonceForm.fxml"));
               pageV.getChildren().removeAll();
               pageV.getChildren().setAll(fxml);
        
    }

    @FXML
    private void voirVehicule(ActionEvent event) throws IOException {
        
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         ResultSet rs ;
         
         String req1 ="SELECT * FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
         
         try {
            
             stat = con.prepareStatement(req1);
             stat.setInt(1, id_user);
             stat.setString(2, depart);
             stat.setString(3, destination);
             stat.setDate(4, dat);
             rs = stat.executeQuery();
             
             if(rs.next()){
                 
                 VehiculeUtiliserController.id_user = id_user;
                 VehiculeUtiliserController.depart = depart;
                 VehiculeUtiliserController.destination = destination;
                 VehiculeUtiliserController.dat = dat;
                 
                 Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/VehiculeUtiliser.fxml"));
                 pageV.getChildren().removeAll();
                 pageV.getChildren().setAll(fxml);
                 
             }
             
        } catch (SQLException e) {
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import Main.Connexion;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class ReserverController implements Initializable {
    
    double x = 0;
    double y = 0;
    
    @FXML
    private TextField ref;
    @FXML
    private JFXComboBox<String> place;
    private ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5");
    @FXML
    private AnchorPane mur;
    public static int id_user;
    public static int id_annonceur;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         place.setItems(list);
        
    }    

    @FXML
    private void reset(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Reserver.fxml"));
        mur.getChildren().removeAll();
        mur.getChildren().setAll(fxml);
        
    }

    @FXML
    private void closeApp(ActionEvent event) {
        
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
    }
    
    @FXML
    private void pressed(MouseEvent event) {
         
       x = event.getSceneX();
       y = event.getSceneY();
          

        }
    @FXML
     private void dragged(MouseEvent event) {
            
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);  
        }
     
     @FXML
     private void reserver(ActionEvent event){
         
         String br1 = place.getValue();
         String id = ref.getText();
         
         Connection con = Connexion.getConnection();
         Connection conn = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement sta ;
         PreparedStatement st ;
         ResultSet rs ;
         ResultSet ts ;
         
         String req = "SELECT * FROM trajetmodifier WHERE id = ?";
         String sql1 = "INSERT INTO trajetreserver (id_client,id_annonceur,Depart,Destination,Heure_depart,Lieux_rencontre,Date,Place_reserver,Vehicule,Prix) VALUES (?,?,?,?,?,?,?,?,?,?)";
         int id_r = Integer.parseInt(id);
         String sql = " UPDATE trajetmodifier SET nbr_place = ? WHERE id = ? ";
         
         try {
             
             if(id.isEmpty()){
                 
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait(); 
                 
             }
             
             else {
                 
              int id_ref = Integer.parseInt(id);
              stat = con.prepareStatement(req);
              stat.setInt(1, id_ref);  
              rs = stat.executeQuery();
              
              if(rs.next()){
                 
                 int nbr1 = Integer.parseInt(br1);
                 int nbr2 = Integer.parseInt(rs.getString("nbr_place"));
                 
                 if(nbr1 <= nbr2 ){
         
                    int nbr = (nbr2) - (nbr1);
                    String br = String.valueOf(nbr);
                    
                    sta = con.prepareStatement(sql);
                    sta.setString(1, br);
                    sta.setInt(2, id_r);
                    sta.executeUpdate();
                    
                    st = conn.prepareStatement(sql1);
                    st.setInt(1, id_user);
                    st.setInt(2, id_annonceur);
                    st.setString(3, rs.getString("Depart"));
                    st.setString(4, rs.getString("Destination"));
                    st.setTime(5, rs.getTime("Heure_depart"));
                    st.setString(6, rs.getString("Lieu_rencontre"));
                    st.setDate(7, rs.getDate("Date"));
                    st.setString(8, br1);
                    st.setString(9, rs.getString("vehicule"));
                    st.setString(10, rs.getString("prix")); 
                    st.executeUpdate();
                      
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText("Information");
                      alert.setTitle("Alert");
                      alert.setContentText("Vôtre réservation à bien été enregistrer!");
                      alert.showAndWait(); 
                                       
                 }
                 
                 else{
                     
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Manque de place!");
               alert.showAndWait(); 
                     
                 }
                  
              }
              
              else {
                  
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Il n'y a aucune annonce a cette référence!");
               alert.showAndWait(); 
                  
              }
                 
             }
             
         } catch (NumberFormatException | SQLException e) {
         }
     }
    
}

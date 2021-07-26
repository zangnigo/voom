/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InfoAnnonceurController implements Initializable {
    
    double x = 0;
    double y = 0;
    
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text marque;
    @FXML
    private Text modele;
    @FXML
    private Text matricule;
    @FXML
    private Text couleur;
    @FXML
    private TextField ref;
    @FXML
    private AnchorPane mur;
    @FXML
    private Text numero;

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
   private void voir(ActionEvent event){
       
       String id = ref.getText(); 
       
    Connection con = Connexion.getConnection();
    Connection conn = Connexion.getConnection();
    PreparedStatement stat ;
    PreparedStatement sta ;
    PreparedStatement st ;
    ResultSet rs ;
    ResultSet rss ;
    ResultSet rsss ;
    String req1 = "SELECT * FROM trajetmodifier WHERE id = ?";
    String req2 = "SELECT Nom, Prenoms, Contact FROM utilisateur INNER JOIN trajet ON utilisateur.id = ?";
    String req3 = "SELECT Marque, Modele, Matricule, Couleur FROM vehicule INNER JOIN trajet ON vehicule.Matricule = ?";
    
       try {
           if(id.isEmpty()){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait(); 
              
           }
           else{
               
              int id_ref = Integer.parseInt(id);
              stat = con.prepareStatement(req1);
              stat.setInt(1, id_ref);  
              rs = stat.executeQuery();
           
              if(rs.next()){
              int id_client = rs.getInt("id_client");
              sta = con.prepareStatement(req2);
              sta.setInt(1, id_client);  
              rss = sta.executeQuery(); 
              
              String mat = rs.getString("Vehicule");
              st = conn.prepareStatement(req3);
              st.setString(1, mat);
              rsss = st.executeQuery();
              
              if(rss.next() && rsss.next()){
                  
                  nom.setText(rss.getString("Nom"));
                  prenom.setText(rss.getString("Prenoms"));
                  numero.setText(rss.getString("Contact"));
                  
                  marque.setText(rsss.getString("Marque"));
                  modele.setText(rsss.getString("Modele"));
                  matricule.setText(rsss.getString("Matricule"));
                  couleur.setText(rsss.getString("Couleur"));
                  
              }
                  
              }
              
              else {
                  
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Il n'y pas d'annonce correspondante");
               alert.showAndWait(); 
                  
              }
               
           }   
              
       } catch (SQLException e) {
       }
       
      
       
   }
   @FXML
    public void closeApp(ActionEvent e) {
        Node node = (Node) e.getSource();
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
   private void reset(ActionEvent e) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/InfoAnnonceur.fxml"));
        mur.getChildren().removeAll();
        mur.getChildren().setAll(fxml);
     
 }
}

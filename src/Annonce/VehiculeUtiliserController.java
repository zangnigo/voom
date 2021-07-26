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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class VehiculeUtiliserController implements Initializable {

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
         PreparedStatement st ;
         PreparedStatement stat ;
         ResultSet rs ;
         ResultSet ts ;
         
         String req1 ="SELECT * FROM trajet WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
         String req2 = "SELECT * FROM vehicule WHERE id_client = ? AND Matricule = ?";
         
         try {
            
            st = con.prepareStatement(req1);
            st.setInt(1, id_user);
            st.setString(2, depart);
            st.setString(3, destination);
            st.setDate(4, dat);
            rs = st.executeQuery(); 
            
            if(rs.next()){
                
                stat = con.prepareStatement(req2);
                stat.setInt(1, id_user);
                stat.setString(2, rs.getString("Vehicule"));
                ts = stat.executeQuery();
                
                if(ts.next()){
                    
                marque.setText(ts.getString("Marque"));
                modele.setText(ts.getString("Modele"));
                mat.setText(ts.getString("Matricule"));
                couleur.setText(ts.getString("Couleur"));
                num.setText(ts.getString("num_carte_grise"));
                
                }
            }
             
        } catch (SQLException e) {
        }
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
                 Info_AnnonceController.id_user = id_user;
                 Info_AnnonceController.depart = depart;
                 Info_AnnonceController.destination = destination;
                 Info_AnnonceController.dat = dat;
                 
                 Parent fxml = FXMLLoader.load(getClass().getResource("/Annonce/Info_Annonce.fxml"));
                 pageV.getChildren().removeAll();
                 pageV.getChildren().setAll(fxml);
        
    }
    
}

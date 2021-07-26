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
public class Mes_reservationsController implements Initializable {

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
        
        String req1 = "SELECT * FROM trajetreserver WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        
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
                place.setText(rs.getString("Place_reserver"));
                date.setText(dt);
                lieu.setText(rs.getString("Lieux_rencontre"));
                prix.setText(rs.getString("Prix"));
                
            }
            
        } catch (SQLException e) {
        }
        
    }    

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        PreparedStatement st ;
        PreparedStatement sta ;
        PreparedStatement state ;

        ResultSet rs ;
        ResultSet ts ;
        
        String req1 = "DELETE FROM trajetreserver WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        String req2 = "SELECT * FROM trajetmodifier WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        String req3 = "SELECT * FROM trajetreserver WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?";
        String sql = " UPDATE trajetmodifier SET nbr_place = ? WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ? ";
        
        try {
            
            st = con.prepareStatement(req2);
            st.setInt(1, id_user);
            st.setString(2, depart);
            st.setString(3, destination);
            st.setDate(4, dat);
            rs = st.executeQuery();
            
            sta = con.prepareStatement(req3);
            sta.setInt(1, id_user);
            sta.setString(2, depart);
            sta.setString(3, destination);
            sta.setDate(4, dat);
            ts = sta.executeQuery();
            
            if(rs.next() && ts.next()){
                String nbr = rs.getString("nbr_place");
                String nb = ts.getString("Place_reserver");
                int nbr1 = Integer.parseInt(nbr);
                int nbr2 = Integer.parseInt(nb);
                int plac = (nbr1) + (nbr2);
                String siege = String.valueOf(plac);
                
            stat = con.prepareStatement(req1);
            stat.setInt(1, id_user);
            stat.setString(2, depart);
            stat.setString(3, destination);
            stat.setDate(4, dat);
            stat.executeUpdate();
            
            state = con.prepareStatement(sql);
            state.setString(1, siege);
            state.setInt(2, id_user);
            state.setString(3, depart);
            state.setString(4, destination);
            state.setDate(5, dat);
            state.executeUpdate();
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Information");
                    alert.setTitle("Alert");
                    alert.setContentText("Réservation supprimé!");
                    alert.showAndWait();
                    
                    Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/InfoReservationForm.fxml"));
                   pageV.getChildren().removeAll();
                  pageV.getChildren().setAll(fxml);
        
            }
            
            else {
                
               Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Une erreur est survenue la réservation n'a pas été supprimé!");
                    alert.showAndWait(); 
                
            }
        } catch (SQLException e) {
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/InfoReservationForm.fxml"));
        pageV.getChildren().removeAll();
        pageV.getChildren().setAll(fxml);
        
    }
    
    

    @FXML
    private void info(ActionEvent event) throws IOException {
        
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        ResultSet rs ;
        
        String req1 = "SELECT * FROM trajetreserver WHERE id_client = ? AND Depart = ? AND Destination = ? AND Date = ?"; 
        
        try {
            
            stat = con.prepareStatement(req1);
            stat.setInt(1, id_user);
            stat.setString(2, depart);
            stat.setString(3, destination);
            stat.setDate(4, dat);
            rs = stat.executeQuery();
            
            if(rs.next()){
                
                InformationController.id_user = id_user;
                InformationController.id_annonceur = rs.getInt("id_annonceur");
                InformationController.depart = depart;
                InformationController.destination = destination;
                InformationController.dat = dat;
                InformationController.mat = rs.getString("Vehicule");
                
                Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Information.fxml"));
                 pageV.getChildren().removeAll();
                 pageV.getChildren().setAll(fxml);
                
            }
            
        } catch (SQLException e) {
        }
        
    }
    
}

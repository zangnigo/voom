/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import Main.Connexion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class VoirReservationFormController implements Initializable {

    @FXML
    private AnchorPane rest;
    @FXML
    private JFXComboBox<String> dep;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> dest;
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
        
        String req1 = "SELECT * FROM lieux";
        
        try {
            
            stat = con.prepareStatement(req1);
             rs = stat.executeQuery();
             while(rs.next()){
                 
                 dep.getItems().add(rs.getString("nom"));
                 dest.getItems().add(rs.getString("nom"));
                 
                 }
            
        } catch (SQLException e) {
        }
        
    }    

    @FXML
    private void voir(ActionEvent event) throws IOException {
        
         Date dat = null;
         dat = java.sql.Date.valueOf(date.getValue());
         String depart = dep.getValue();
         String destination = dest.getValue();
         String req1 ="SELECT * FROM trajetreserver WHERE id_annonceur = ? AND Depart = ? AND Destination = ? AND Date = ?";
         
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         ResultSet rs ;
         
         try {
            
             stat = con.prepareStatement(req1);
             stat.setInt(1, id_user);
             stat.setString(2, depart);
             stat.setString(3, destination);
             stat.setDate(4, dat);
             rs = stat.executeQuery();
             
             if(rs.next()){
                 
                 Voir_reservationController.id_user = id_user;
                 Voir_reservationController.id_client = rs.getInt("id_client");
                 Voir_reservationController.depart = depart;
                 Voir_reservationController.destination = destination;
                 Voir_reservationController.date = dat;
                 
                   Stage window = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("/Message/Voir_reservation.fxml"));
                   window.initStyle(StageStyle.UNDECORATED);
                   window.initStyle(StageStyle.TRANSPARENT);
                   Scene scene = new Scene(root);
                   window.setScene(scene);
                   window.show();
                   Node node = (Node) event.getSource();
                   Stage stage = (Stage) node.getScene().getWindow();
                   stage.close();
                 
             }
             
             else {
                 
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Cette annonce n'a pas de réservation!");
               alert.showAndWait();   
                 
             }
             
        } catch (SQLException e) {
        }
        
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Message/VoirReservationForm.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
        
    }
    
}

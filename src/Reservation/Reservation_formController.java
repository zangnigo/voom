/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import Main.Connexion;
import Main.Launch;
import Main.MainUIController;
import com.jfoenix.controls.JFXButton;
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
public class Reservation_formController implements Initializable {
    
    @FXML
    private AnchorPane rest;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXComboBox<String> dep;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> dest;
    @FXML
    private JFXButton rechercher;
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
        String req1 = "SELECT * FROM lieux";
        try {
            
            st = con.prepareStatement(req1);
            rs = st.executeQuery();
             while(rs.next()){
                 
                 dep.getItems().add(rs.getString("nom"));
                 dest.getItems().add(rs.getString("nom"));
                 
                 }
            
        } catch (SQLException e) {
        }
        
    }  
    
     @FXML  
 private void reset(ActionEvent e) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Reservation_form.fxml"));
        rest.getChildren().removeAll();
        rest.getChildren().setAll(fxml);
     
 }
 @FXML
 private void recherche(ActionEvent event) throws IOException{
    
    Date dat = null;
    dat = java.sql.Date.valueOf(date.getValue());
    String depart = dep.getValue();
    String destination = dest.getValue();
    
    
    String req ="SELECT * FROM trajetmodifier WHERE Depart = ? AND Destination = ? AND Date = ?";
    Connection con = Connexion.getConnection();
    PreparedStatement stat ;
    ResultSet ts ;
    
     try {
         
              stat = con.prepareStatement(req);
              stat.setString(1, depart);
              stat.setString(2, destination);
              stat.setDate(3, dat);  
              ts = stat.executeQuery();
              
              if(ts.next()){
                  List_reservationController.id_user = id_user;
                  List_reservationController.depart = depart;
                  List_reservationController.destination = destination;
                  List_reservationController.date = dat;
                  List_reservationController.id_annonceur = ts.getInt("id_client");
            
//                  scene.setFill(Color.TRANSPARENT);        
                   Stage window = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("/Reservation/List_reservation.fxml"));
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
                    alert.setHeaderText("Information");
                    alert.setTitle("Alert");
                    alert.setContentText("Il n'y a aucune annonce correspondante à votre demande!");
                    alert.showAndWait();   
                  
              }
         
     } catch (SQLException e) {
     }
    
     
 }
 
// public void start(Stage stage) throws IOException{
//     
//      Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/List_reservation.fxml"));
//      Scene scene = new Scene(fxml);
//      stage.setScene(scene);
//      stage.show();
// }
    
}

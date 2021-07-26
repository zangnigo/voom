/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import Main.Connexion;
import Main.MenuAppController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class List_reservationController implements Initializable {

    double x = 0;
    double y = 0;
    
    @FXML
    private AnchorPane partie;
    @FXML
    private TableView<Tableau> table;
    @FXML
    private TableColumn<Tableau, Integer> ref;
    @FXML
    private TableColumn<Tableau, Time> heure;
    @FXML
    private TableColumn<Tableau, String> prix;
    @FXML
    private TableColumn<Tableau, String> place;
    @FXML
    private TableColumn<Tableau, String> lieu;
    public ObservableList<Tableau> data = FXCollections.observableArrayList();
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton acceuil;
    @FXML
    private JFXButton info;
    public static int id_user;
    public static String depart;
    public static String destination;
    public static Date date;
    public static int id_annonceur;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    String req ="SELECT * FROM trajetmodifier WHERE Depart = ? AND Destination = ? AND Date = ?";
    Connection con = Connexion.getConnection();
    PreparedStatement stat ;
    ResultSet rs ;
    
        try {
            
              stat = con.prepareStatement(req);
              stat.setString(1, depart);
              stat.setString(2, destination);
              stat.setDate(3, date);  
              rs = stat.executeQuery();
              
              while(rs.next()){
                  
                  data.addAll(new Tableau(rs.getInt("id"), rs.getTime("Heure_depart"), rs.getString("prix"), rs.getString("nbr_place"), rs.getString("Lieu_rencontre")));
                  
              }
              
              con.close();
            
        } catch (SQLException e) {
        }
        
        ref.setCellValueFactory(new PropertyValueFactory<Tableau, Integer>("id"));
        heure.setCellValueFactory(new PropertyValueFactory<Tableau, Time>("heure"));
        prix.setCellValueFactory(new PropertyValueFactory<Tableau, String>("prix"));
        place.setCellValueFactory(new PropertyValueFactory<Tableau, String>("place"));
        lieu.setCellValueFactory(new PropertyValueFactory<Tableau, String>("lieux"));
        table.setItems(data);
        
    } 
    
    @FXML
    public void closeApp(ActionEvent e) {
        System.exit(0);
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
     
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/List_reservation.fxml"));
        partie.getChildren().removeAll();
        partie.getChildren().setAll(fxml);
     
 }
 
 @FXML
 private void acceuil(ActionEvent e) throws IOException{
        MenuAppController.ids = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Main/MenuApp.fxml"));
        partie.getChildren().removeAll();
        partie.getChildren().setAll(fxml);
     
 }
 
 @FXML
 private void info(ActionEvent event) throws IOException{
                  
                   Stage window = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("/Reservation/InfoAnnonceur.fxml"));
                   window.initStyle(StageStyle.UNDECORATED);
                   window.initStyle(StageStyle.TRANSPARENT);
                   Scene scene = new Scene(root);
                   window.setScene(scene);
                   window.show();
     
 }
 
 @FXML
 private void reservation(ActionEvent event) throws IOException{
     
                   ReserverController.id_annonceur= id_annonceur;
                   ReserverController.id_user = id_user;
                   Stage window = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("/Reservation/Reserver.fxml"));
                   window.initStyle(StageStyle.UNDECORATED);
                   window.initStyle(StageStyle.TRANSPARENT);
                   Scene scene = new Scene(root);
                   window.setScene(scene);
                   window.show();
     
 }
 
}

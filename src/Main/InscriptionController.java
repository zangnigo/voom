/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InscriptionController implements Initializable {

     private double x,y;      
    private Stage stage;
      
    @FXML
    private JFXButton page_connexion;
    @FXML
    private JFXButton close;
    @FXML
    private AnchorPane zone;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField num;
    @FXML
    private JFXTextField num_paie;
    @FXML
    private JFXTextField textU;
    @FXML
    private JFXTextField txtN;
    @FXML
    private JFXPasswordField textMdp;
    @FXML
    private JFXPasswordField textMDP_conf;
    @FXML
    private JFXTextField txtP;

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
    private void close(ActionEvent event) {
         System.exit(0);
    }
    
     @FXML
    public void pressed(MouseEvent event) {
          
            stage = (Stage) zone.getScene().getWindow();
            
            x = event.getSceneX();
            y = event.getSceneY();
        
        }
    
    @FXML
     public void dragged(MouseEvent event) {
              
            stage.setX(event.getSceneX() - x);
            stage.setY(event.getSceneY() - y);
         
        }
   
    @FXML
    private void open_connexion(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/Main/MainUI.fxml"));
        zone.getChildren().removeAll();
        zone.getChildren().setAll(fxml);
    }
    @FXML
    public void inscrire(ActionEvent event) throws IOException {
        Date tdate = null;
        tdate = java.sql.Date.valueOf(date.getValue());
        
        String nom = txtN.getText();
         String user = textU.getText();
          String prenoms = txtP.getText();
           String mdp = textMdp.getText();
            String cmdp = textMDP_conf.getText();
             String email = mail.getText();
              String cnum = num.getText();
               String cnum_paie = num_paie.getText();
              
            
        Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement st ;
         ResultSet rs ;

          String req = "SELECT * FROM utilisateur WHERE user = ?";
          String sql = "INSERT INTO utilisateur(user, mdp, Nom, Prenoms, Dte_naiss, Email, Contact, Contact_paie) VALUES(?,?,?,?,?,?,?,?)";
        try {
              if(user.equals("") || mdp.equals("") || nom.equals("") || prenoms.equals("") || cmdp.equals("") || email.equals("") 
                  || cnum.equals("") 
                 || cnum_paie.equals(""))
                          {
                              Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setHeaderText("Erreur");
                              alert.setTitle("Alert");
                              alert.setContentText("Champs vide!");
                              alert.showAndWait();
                          }
              else{ 
                  if(cmdp == null ? mdp != null : !cmdp.equals(mdp)){
                              Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setHeaderText("Erreur");
                              alert.setTitle("Alert");
                              alert.setContentText("Mot de passe différent!");
                              alert.showAndWait();
                          } else{
                              st = con.prepareStatement(req);
                               st.setString(1, textU.getText());
                                rs = st.executeQuery();
                         if(rs.next()){
                             
                              Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Information");
               alert.setTitle("Alert");
               alert.setContentText("Utilisateur existe déja!");
               alert.showAndWait();
                             
                         }
                         else {
                          
                stat = con.prepareStatement(sql);
              stat.setString(1, textU.getText());
             stat.setString(2, textMdp.getText());
             stat.setString(3, txtN.getText());
             stat.setString(4, txtP.getText());
             stat.setDate(5,tdate);
             stat.setString(6, mail.getText());
             stat.setString(7, num.getText());
             stat.setString(8, num_paie.getText());
              stat.executeUpdate();
             
              Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Information");
               alert.setTitle("Alert");
               alert.setContentText("Inscription réussi!");
               alert.showAndWait();
                             
                         }
                  }
              }
        } catch (SQLException e) {
        }
    
    }
    
    @FXML
    private void reset (ActionEvent e) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Main/Inscription.fxml"));
        zone.getChildren().removeAll();
        zone.getChildren().setAll(fxml);
        
    }
}

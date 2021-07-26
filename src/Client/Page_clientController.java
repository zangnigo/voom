/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Main.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class Page_clientController implements Initializable {

    @FXML
    private JFXTextField nom_mod;
    @FXML
    private JFXTextField prenom_mod;
    @FXML
    private JFXPasswordField mdp_mod;
    @FXML
    private JFXPasswordField cmdp_mod;
    @FXML
    private JFXButton update;
    @FXML
    private JFXTextField user_mod;
    @FXML
    private JFXButton reset;
    @FXML
    public static int id_user;
    

    
    /**
    *  * Initializes the controller class.
    * @param url
    * @param rb
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void modifier(ActionEvent event){
        
         String nom = nom_mod.getText();
          String prenom = prenom_mod.getText();
           String user = user_mod.getText();
            String mdp = mdp_mod.getText();
             String cmdp = cmdp_mod.getText();
             
             
             
        Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         PreparedStatement st ;
         ResultSet rs;
         String req = "SELECT * FROM utilisateur WHERE user = ?";
         String sql = "UPDATE utilisateur SET user = ? , mdp = ? , nom = ?, prenom = ? WHERE id = ? ";
         
         try {
            
             if(user.equals("") || mdp.equals("") || nom.equals("") || prenom.equals("") || cmdp.equals("") )
             {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setHeaderText("Erreur");
                              alert.setTitle("Alert");
                              alert.setContentText("Champs vide!");
                              alert.showAndWait();
             }
             
             else 
                 if(cmdp == null ? mdp != null : !cmdp.equals(mdp)){
                              Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setHeaderText("Erreur");
                              alert.setTitle("Alert");
                              alert.setContentText("Mot de passe différent!");
                              alert.showAndWait();
                          } else {
                       st = con.prepareStatement(req);
                               st.setString(1, user_mod.getText());
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
                             stat.setString(1, user);
                             stat.setString(2, mdp);
                             stat.setString(3, nom);
                             stat.setString(4, prenom);
                             stat.setInt(5, id_user);
                             stat.executeUpdate();
                             
                              Alert alert = new Alert(Alert.AlertType.ERROR);
                              alert.setHeaderText("Information");
                              alert.setTitle("Alert");
                              alert.setContentText("Utilisateur existe déja!");
                              alert.showAndWait();
                         }
                 } 
             
             
        } catch (SQLException e) {
        }
        
    }
    
 @FXML
 private void reset(ActionEvent e){
     
     user_mod.clear();
     nom_mod.clear();
     prenom_mod.clear();
     mdp_mod.clear();
     cmdp_mod.clear();
     
 }
    
}


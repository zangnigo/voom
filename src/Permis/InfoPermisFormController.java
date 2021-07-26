/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permis;

import Main.Connexion;
import static Permis.Info_PermisController.cat;
import static Permis.Info_PermisController.id_user;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InfoPermisFormController implements Initializable {

    @FXML
    private AnchorPane zoneP;
    @FXML
    private JFXComboBox<String> box;
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
         
         String sql = "SELECT * FROM categorie";
         try {
            stat = con.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()){
                 
                box.getItems().add(rs.getString("type"));
                
            }
        } catch (SQLException e) {
        }
        
    }

    @FXML
    private void voir(ActionEvent event) throws IOException {
        
        String cat = box.getValue();
        
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         ResultSet rs ;
         
        String sql = "SELECT * FROM permis WHERE id_client = ? AND Categorie = ?";
        
        try {
            if(cat.isEmpty()){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait();    
                
            }
            
            else {
             stat = con.prepareStatement(sql);
             stat.setInt(1, id_user);
             stat.setString(2, cat);
             rs = stat.executeQuery();
             if(rs.next()){
                 
                 Info_PermisController.cat = cat;
                 Info_PermisController.id_user = id_user;
                 Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/Info_Permis.fxml"));
                 zoneP.getChildren().removeAll();
                 zoneP.getChildren().setAll(fxml);
                 
             }
             
             else {
                 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Vous n'avez pas de permis de cette catégorie!");
                    alert.showAndWait();
                 
             }
            }
            
        } catch (SQLException e) {
        }
        
        
    }    
    
}

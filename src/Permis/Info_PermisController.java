/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permis;

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
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class Info_PermisController implements Initializable {
    
    
    @FXML
    private Text dateD;
    @FXML
    private Text dateE;
    @FXML
    private Text num;
    @FXML
    private Text categorie;
    public static int id_user;
    public static String cat;
    @FXML
    private AnchorPane pageP;
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
        
         String sql = "SELECT * FROM permis WHERE id_client = ? AND Categorie = ?";
         
         try {
            
             stat = con.prepareStatement(sql);
             stat.setInt(1, id_user);
             stat.setString(2, cat);
             rs = stat.executeQuery();
             
             if(rs.next()){
                
                Date datD = rs.getDate("Dat_delivrance");
                String dtD = String.valueOf(datD);
                Date datE = rs.getDate("Dat_expiration");
                String dtE = String.valueOf(datE);
                categorie.setText(rs.getString("Categorie"));
                dateD.setText(dtD);
                dateE.setText(dtE);
                num.setText(rs.getString("Num_permis"));
                 
             }
             
        } catch (SQLException e) {
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/InfoPermisForm.fxml"));
        pageP.getChildren().removeAll();
        pageP.getChildren().setAll(fxml);
        
    }  
    
    @FXML
    private void supprimer(ActionEvent event) throws IOException{
        
         Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         ResultSet rs ;
        
         String sql = "DELETE FROM permis WHERE id_client = ? AND Categorie = ?";
         
         try {
            
             stat = con.prepareStatement(sql);
             stat.setInt(1, id_user);
             stat.setString(2, cat);
             stat.executeUpdate(); 
             
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Information");
                    alert.setTitle("Alert");
                    alert.setContentText("Permis supprimé!");
                    alert.showAndWait();
             
        Parent fxml = FXMLLoader.load(getClass().getResource("/Permis/InfoPermisForm.fxml"));
        pageP.getChildren().removeAll();
        pageP.getChildren().setAll(fxml);
        
        
        } catch (SQLException e) {
        }
        
        
    }  
    
}

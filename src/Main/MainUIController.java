/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * @author KAYV
 */
public class MainUIController implements Initializable{
    
    double x = 0;
    double y = 0;      
//    public static Stage stage;
    
    public JFXButton btn_close;
    @FXML
    private AnchorPane content_area;
    @FXML
    private JFXButton open_incription;
    @FXML
    private JFXPasswordField mdp_user;
    @FXML
    private JFXTextField nom_user;
    @FXML
    private JFXButton login;
    @FXML
    private AnchorPane zone1;
    @FXML
    private JFXButton btn_reduc;

   
    /**
     *
     * @param e
     */
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
    private void reducApp(ActionEvent e) {
        
        
        
    }
    
    @FXML
    private void open_inscription(ActionEvent event) throws Exception{
        Parent fxml = FXMLLoader.load(getClass().getResource("/Main/Inscription.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
     }
     @FXML
    public void login(ActionEvent event) throws IOException {
        Connection con = Connexion.getConnection();
         PreparedStatement stat ;
         ResultSet rs ;
         
         String sql = "SELECT * FROM utilisateur WHERE user = ? AND mdp = ?";
         try {
             stat = con.prepareStatement(sql);
             stat.setString(1, nom_user.getText());
             stat.setString(2, mdp_user.getText());
             rs = stat.executeQuery();
                     if(rs.next()){
                         MenuAppController.ids = rs.getInt("id");
                          Parent fxml = FXMLLoader.load(getClass().getResource("/Main/MenuApp.fxml"));
                           content_area.getChildren().removeAll();
                           content_area.getChildren().setAll(fxml);
                                  }
                     else{
                        
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Erreur");
                    alert.setTitle("Alert");
                    alert.setContentText("Nom d'utilisateur ou mot de passe sont incorrects!");
                    alert.showAndWait();
                     }
         } catch (SQLException e) {
         }
         
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


  }

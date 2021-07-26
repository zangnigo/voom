/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import Main.Connexion;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InfoClientController implements Initializable {
    
    double x = 0;
    double y = 0;
    
    @FXML
    private TextField ref;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text numero;
    @FXML
    private AnchorPane mur;
    @FXML
    private TextField refC;

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
    private void voir(ActionEvent event) {
        
        String id = ref.getText();
        String id_client = refC.getText();
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        ResultSet rs ;
        
        String req = "SELECT Nom, Prenoms, Contact FROM utilisateur INNER JOIN trajetreserver ON utilisateur.id = ? AND trajetreserver.id = ?";
        
        try {
            
           if(id.isEmpty() || id_client.isEmpty()){
               
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("Champs vide!");
               alert.showAndWait(); 
               
           } 
           
           else {
               
               int id_ref = Integer.parseInt(id);
               int id_C = Integer.parseInt(id_client);
               stat = con.prepareStatement(req);
               stat.setInt(1, id_C);
               stat.setInt(2, id_ref);
               rs = stat.executeQuery();
               if(rs.next()){
                   
                  nom.setText(rs.getString("Nom"));
                  prenom.setText(rs.getString("Prenoms"));
                  numero.setText(rs.getString("Contact"));
                   
               }
               
               else {
                   
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Erreur");
               alert.setTitle("Alert");
               alert.setContentText("On ne retrouve pas ce client!");
               alert.showAndWait();
                   
               }
               
           }
            
        } catch (NumberFormatException | SQLException e) {
        }
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Message/InfoClient.fxml"));
        mur.getChildren().removeAll();
        mur.getChildren().setAll(fxml);
     
        
    }

    @FXML
    private void closeApp(ActionEvent event) {
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
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
    
}

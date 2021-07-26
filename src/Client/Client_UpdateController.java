/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class Client_UpdateController implements Initializable {

    @FXML
    private AnchorPane zoneClient;
    @FXML
    public static int id_user;
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
    private void page_client(ActionEvent event) throws Exception {
        
        Page_clientController.id_user = id_user ;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Client/Page_client.fxml"));
        zoneClient.getChildren().removeAll();
        zoneClient.getChildren().setAll(fxml);
        
    }
    
     @FXML
    private void info_client(ActionEvent event) throws Exception {
        
        InfoClientController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Client/InfoClient.fxml"));
        zoneClient.getChildren().removeAll();
        zoneClient.getChildren().setAll(fxml);
        
    }
    
}

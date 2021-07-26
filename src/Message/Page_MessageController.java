/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.IOException;
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
public class Page_MessageController implements Initializable {

    @FXML
    private AnchorPane zoneMessage;
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
    private void voirReservationForm(ActionEvent event) throws IOException {
        
        VoirReservationFormController.id_user = id_user;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Message/VoirReservationForm.fxml"));
        zoneMessage.getChildren().removeAll();
        zoneMessage.getChildren().setAll(fxml);
        
    }
    
}

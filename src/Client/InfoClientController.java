/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Main.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InfoClientController implements Initializable {

    @FXML
    private Text user;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text date;
    @FXML
    private Text num1;
    @FXML
    private Text num2;
    @FXML
    private Text email;
    public static int id_user;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    String req ="SELECT * FROM utilisateur WHERE id = ?";
    Connection con = Connexion.getConnection();
    PreparedStatement stat ;
    ResultSet rs ;
    
        try {
            
            stat = con.prepareStatement(req);
            stat.setInt(1, id_user);
            rs = stat.executeQuery();
            
            if(rs.next()){
                
                Date dat = rs.getDate("Dte_naiss");
                String dt = String.valueOf(dat);
                user.setText(rs.getString("user"));
                nom.setText(rs.getString("Nom"));
                prenom.setText(rs.getString("Prenoms"));
                date.setText(dt);
                num1.setText(rs.getString("Contact"));
                num2.setText(rs.getString("Contact_paie"));
                email.setText(rs.getString("Email"));
                
            }
            
        } catch (SQLException e) {
        }
        
    }    
    
}

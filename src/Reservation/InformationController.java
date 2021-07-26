/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAYV
 */
public class InformationController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text numero;
    @FXML
    private Text marque;
    @FXML
    private Text modele;
    @FXML
    private Text matricule;
    @FXML
    private Text couleur;
    public static int id_user;
    public static int id_annonceur;
    public static String depart;
    public static String destination;
    public static Date dat;
    public static String mat;
    @FXML
    private AnchorPane mur;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection con = Connexion.getConnection();
        PreparedStatement stat ;
        PreparedStatement st ;
        ResultSet rs ;
        ResultSet ts ;
        
        String sql = "SELECT Nom, Prenoms, Contact FROM utilisateur INNER JOIN trajetreserver ON utilisateur.id = ?";
        String req = "SELECT Marque, Modele, Matricule, Couleur FROM vehicule INNER JOIN trajetreserver ON vehicule.Matricule = ?";
        
        
        try {
            
            stat = con.prepareStatement(sql);
            stat.setInt(1, id_annonceur);
            ts = stat.executeQuery();
            
            st = con.prepareStatement(req);
            st.setString(1, mat);
            rs = st.executeQuery();
            
            if(ts.next() && rs.next()){
                
                  nom.setText(ts.getString("Nom"));
                  prenom.setText(ts.getString("Prenoms"));
                  numero.setText(ts.getString("Contact"));
                  
                  marque.setText(rs.getString("Marque"));
                  modele.setText(rs.getString("Modele"));
                  matricule.setText(rs.getString("Matricule"));
                  couleur.setText(rs.getString("Couleur"));
                
            }
            
        } catch (SQLException e) {
        }
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        Mes_reservationsController.id_user = id_user;
        Mes_reservationsController.depart = depart;
        Mes_reservationsController.destination = destination;
        Mes_reservationsController.dat = dat;
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/Reservation/Mes_reservations.fxml"));
        mur.getChildren().removeAll();
        mur.getChildren().setAll(fxml);
        
    }
    
}

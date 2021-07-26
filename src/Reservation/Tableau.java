/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import java.sql.Time;

/**
 *
 * @author KAYV
 */
public class Tableau {
 
        private int id;
        private Time heure;
        private String prix;
        private String place;
        private String lieux;
        
        public Tableau (){

            super();

        }
        
        public Tableau(int id, Time heure, String prix, String place, String lieux) {
            
            super();
            this.id = id;
            this.heure = heure;
            this.prix = prix;
            this.place = place;
            this.lieux = lieux;
            
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }
    
    
}


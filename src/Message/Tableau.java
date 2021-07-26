/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

/**
 *
 * @author KAYV
 */
public class Tableau {
        
        
        private int id;
        private int id_client;
        private String total;
        private String prix;
        private String place;
        private String lieux;

    
        
        public Tableau (){

            super();

        }
        
        public Tableau(int id, int id_client, String total, String prix, String place, String lieux) {
            
            super();
            this.id = id;
            this.id_client = id_client;
            this.total = total;
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
    
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.Date;
import object.BddObject;

/**
 *
 * @author To Mamiarilaza
 */
public class HistFabLibelle extends BddObject {
/// Attributs
    String dateProduction;
    String produit;
    int quantite;
    String region;
          
/// Encapsulation

    public String getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(String dateProduction) {
        this.dateProduction = dateProduction;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
/// Constructeur
    
    public HistFabLibelle() {
    }

    public HistFabLibelle(String dateProduction, String produit, int quantite, String region) {
        this.dateProduction = dateProduction;
        this.produit = produit;
        this.quantite = quantite;
        this.region = region;
    }
    
}

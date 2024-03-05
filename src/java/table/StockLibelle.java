/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import object.BddObject;

/**
 *
 * @author To Mamiarilaza
 */
public class StockLibelle extends BddObject {
/// Attributs
    String produit;
    int quantite;
    double cump;
    String idRegion;
    
/// Encapsulation
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

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public double getCump() {
        return cump;
    }

    public void setCump(double cump) {
        this.cump = cump;
    }
    
/// Constructeur

    public StockLibelle() {
    }

    public StockLibelle(String produit, int quantite, double cump, String idRegion) {
        this.produit = produit;
        this.quantite = quantite;
        this.cump = cump;
        this.idRegion = idRegion;
    }
    
}

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
public class HistVenteLibelle extends BddObject {
/// Attributs
    String dateVente;
    String produit;
    int quantite;
    String region;

/// Encapsulation
    public String getDateVente() {
        return dateVente;
    }

    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
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

    public HistVenteLibelle() {
    }

    public HistVenteLibelle(String dateVente, String produit, int quantite, String region) {
        this.dateVente = dateVente;
        this.produit = produit;
        this.quantite = quantite;
        this.region = region;
    }
    
    
}

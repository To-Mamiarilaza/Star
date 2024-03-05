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
public class HistoriqueFabrication extends BddObject {
/// Attributs
    String id;
    Date dateProduction;
    String idProduit;
    int quantite;
    String idRegion;
    double prixUnitaire;

/// Encapsulation
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(Date dateProduction) {
        this.dateProduction = dateProduction;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
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

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

/// Constructeur
    
    public HistoriqueFabrication() {
    }

    public HistoriqueFabrication(Date dateProduction, String idProduit, int quantite, String idRegion, double prixUnitaire) {
        setPrefix("HST");
        setNomFonction("getSeqHist");
        this.dateProduction = dateProduction;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.prixUnitaire = prixUnitaire;
    }

    public HistoriqueFabrication(String id, Date dateProduction, String idProduit, int quantite, String idRegion, double prixUnitaire) {
        this.id = id;
        this.dateProduction = dateProduction;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.prixUnitaire = prixUnitaire;
    }
}

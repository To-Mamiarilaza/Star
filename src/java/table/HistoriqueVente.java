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
public class HistoriqueVente extends BddObject {
/// Attributs
    String id;
    Date dateVente;
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

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
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

    public HistoriqueVente() {
    }

    public HistoriqueVente(String id, Date dateVente, String idProduit, int quantite, String idRegion, double prixUnitaire) {
        this.id = id;
        this.dateVente = dateVente;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.prixUnitaire = prixUnitaire;
    }

    public HistoriqueVente(Date dateVente, String idProduit, int quantite, String idRegion, double prixUnitaire) {
        setPrefix("HTV");
        setNomFonction("getSeqVente");
        this.dateVente = dateVente;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.prixUnitaire = prixUnitaire;
    }
    
    
}

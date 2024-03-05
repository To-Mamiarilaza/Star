/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.Vector;
import object.BddObject;

/**
 *
 * @author To Mamiarilaza
 */
public class Stock extends BddObject {
/// Attributs
    String id;
    String idProduit;
    int quantite;
    String idRegion;        // l'endroit ou le stock est place
    double cump;
    
/// Encapsulation

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getCump() {
        return cump;
    }

    public void setCump(double cump) {
        this.cump = cump;
    }
    
/// Constructeur

    public Stock() {
        setPrefix("STK");
        setNomFonction("getSeqStock");
    }

    public Stock(String id, String idProduit, int quantite, String idRegion, double cump) {
        this.id = id;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.cump = cump;
    }

    public Stock(String idProduit, int quantite, String idRegion, double cump) {
        setPrefix("STK");
        setNomFonction("getSeqStock");
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idRegion = idRegion;
        this.cump = cump;
    }

    public Stock(int quantite, String idRegion, double cump) {
        this.idRegion = idRegion;
        this.quantite = quantite;
        this.cump = cump;
    }
    
/// Fonctions de classe
    public static Vector<HistVenteLibelle> getHistoriqueVente() throws Exception {
        //  Prendre la listes  de tous les ventes
        HistVenteLibelle outil = new HistVenteLibelle();
        Vector<Object> listes = outil.getListes(null);
        
        Vector<HistVenteLibelle> resultats = new Vector<>();
        
        for(int i = 0; i < listes.size(); i++) resultats.add((HistVenteLibelle) listes.get(i));
        return resultats;
    }
    
    public static Vector<HistFabLibelle> getHistoriqueFabrication() throws Exception {
        HistFabLibelle outil = new HistFabLibelle();
        Vector<Object> listes = outil.getListes(null);
        
        Vector<HistFabLibelle> resultats = new Vector<>();
        
        for(int i = 0; i < listes.size(); i++) resultats.add((HistFabLibelle) listes.get(i));
        return resultats;
    }
    
    public static Vector<Region> getAllRegion() throws Exception {
        // Prendre tous les regions
        Region outil = new Region();
        Vector<Object> listes = outil.getListes(null);
        Vector<Region> resultats = new Vector<>();
        
        for(int i = 0; i < listes.size(); i++) resultats.add((Region)listes.get(i));
        return resultats;
    }
    
    public static double getStockValue() throws Exception {
        Stock stock = new Stock();
        Vector<Object> listes = stock.getListes(null);
        
        double somme = 0;
        for(int i = 0; i < listes.size(); i++) {
            Stock temp = (Stock) listes.get(i);
            somme += temp.getCump() * temp.getQuantite();
        }
        
        return somme;
    }
    
}

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
public class Region extends BddObject {
/// Attributs
    String id;
    String nom;
    
/// Encapsulation

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
/// Constructeur

    public Region() {
    }

    public Region(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Region(String nom) {
        setPrefix("RGN");
        setNomFonction("getSeqRegion");
        this.nom = nom;
    }
    
/// Fonctions de classe
    public Vector<StockLibelle> getStock() throws Exception {
        StockLibelle stock = new StockLibelle();
        stock.setIdRegion(getId());
        
        Vector<Object> listes = stock.getListes(null);
        Vector<StockLibelle> resultats = new Vector<>();
        
        for(int i = 0; i < listes.size(); i++) resultats.add((StockLibelle)listes.get(i));
        return resultats;
    }
    
    public double getValeurStock(Vector<StockLibelle> listes) {
        double somme = 0;
        for(int i = 0; i < listes.size(); i++) {
            somme += listes.get(i).getQuantite() * listes.get(i).getCump();
        }
        return somme;
    }
}

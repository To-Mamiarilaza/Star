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
public class Composant extends BddObject {
/// Attributs
    String id;
    String nom;
    double pu;
    
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

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }
    
/// Constructeur

    public Composant(String id, String nom, double pu) {
        this.id = id;
        this.nom = nom;
        this.pu = pu;
    }

    public Composant(String nom, double pu) {
        this.setPrefix("PRD");
        this.setNomFonction("getSeqComposant");
        this.nom = nom;
        this.pu = pu;
    }

    public Composant() {
    }
    
}

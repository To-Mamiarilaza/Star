package table;
import object.BddObject;

public class CombinaisonDetail extends BddObject{
/// Attributs
    String id;              // id du composant 
    String nom;
    double pu;
    double quantite; 
    String idResult;        // id du produit semi-fini
    String produit;         // id du produit fini
    int nbComposant;

/// Encapsulation
    public void setId(String id) {this.id = id;}
    public String getId() {return this.id;}
    
    public void setNom(String nom) {this.nom = nom;}
    public String getNom() {return this.nom;}

    public void setPu(double pu) {this.pu = pu;}
    public double getPu() {return this.pu;}

    public void setQuantite(double quantite) {this.quantite = quantite;}
    public double getQuantite() {return this.quantite;}

    public void setIdResult(String idResult) {this.idResult = idResult;}
    public String getIdResult() {return this.idResult;}

    public void setProduit(String produit) {this.produit = produit;}
    public String getProduit() {return this.produit;}

    public void setNbComposant(int nbComposant) {this.nbComposant = nbComposant;}
    public int getNbComposant() {return this.nbComposant;}

/// Constructeur
    public CombinaisonDetail() {}

    public CombinaisonDetail(String id, String nom, double pu, double quantite, String idResult, String produit, int nbComposant) {
        setId(id);
        setNom(nom);
        setPu(pu);
        setQuantite(quantite);
        setIdResult(idResult);
        setProduit(produit);
        setNbComposant(nbComposant);
    }
}

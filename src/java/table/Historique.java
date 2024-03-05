package table;
import java.util.Date;

import object.BddObject;

public class Historique extends BddObject {
/// Attributs
    String id;
    String tabName;
    String action;
    Date dateAction;
    String valeur;

/// Encapsulation
    public void setId(String id) {this.id = id;}
    public String getId() {return this.id;}

    public void setTabName(String tabName) {this.tabName = tabName;}
    public String getTabName() {return this.tabName;}

    public void setAction(String action) {this.action = action;}
    public String getAction() {return this.action;}

    public void setDateAction(Date dateAction) {this.dateAction = dateAction;}
    public Date getDateAction() {return this.dateAction;}

    public void setValeur(String valeur) {this.valeur = valeur;}
    public String getValeur() {return this.valeur;}

/// Constructeur
    public Historique(String tabName, String action, Date dateAction, String valeur) {
        super.setPrefix("HST");
        super.setNomFonction("getSeqHistorique");
        setTabName(tabName);
        setAction(action);
        setDateAction(dateAction);
        setValeur(valeur);
    }
}

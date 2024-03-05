package recette;
import java.util.ArrayList;
import java.util.Vector;

import table.Composant;
import connection.Connex;
import table.CombinaisonDetail;

import java.sql.*;

public class Recette {
/// Attributs

/// Encapsulation

/// Fonction de classes
    public static Composant getProduit(String id) throws Exception {
        Composant outil = new Composant();
        outil.setId(id);
        outil = (Composant)(outil.getListes(null)).get(0); 
        return outil;
    }
    
    public static Vector<Composant> getAllProduit() throws Exception {
        Composant outil = new Composant();
        Vector<Object> listes = outil.getListes(null);
        Vector<Composant> resultats = new Vector<>();
        
        for(int i = 0; i < listes.size(); i++) resultats.add((Composant)listes.get(i));
        return resultats;
    }


}
 
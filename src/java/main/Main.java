package main;

import recette.Recette;
import table.HistVenteLibelle;

public class Main {
    public static void main(String[] args) throws Exception {
        Recette recette = new Recette();
        
        HistVenteLibelle test = new HistVenteLibelle();
        test.getListes(null);
    }
}

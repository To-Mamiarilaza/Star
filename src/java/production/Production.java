/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import table.Composant;
import connection.Connex;
import table.HistoriqueFabrication;
import table.HistoriqueVente;

import java.sql.Connection;
import java.util.Date;
import java.sql.*;
import java.util.Vector;
import table.Stock;

/**
 *
 * @author To Mamiarilaza
 */
public class Production {
    
/// Fonctions de classes
    public static double findCump(Stock stock, Composant produit, int quantite) throws Exception {
        if(quantite < 0) {      // diminution stock ie vente
            if(stock.getQuantite() < -quantite) throw new Exception("La quantite demandee est trop grande !");
            return stock.getCump();     //Lorsque vente cump ne change pas
        }        
        return (stock.getCump() * stock.getQuantite() + produit.getPu() * quantite) / (stock.getQuantite() + quantite);
    }
    
    public static void updateStock(Composant produit, int quantite, String region, Connection connection) throws Exception {
        // Mettre a jour le stock
        Stock outil = new Stock();
        outil.setIdProduit(produit.getId());
        outil.setIdRegion(region);

        
        // Si c'est le premier production
        Vector<Object> stockList = outil.getListes(connection);
        if(stockList.size() == 0) {
            outil.setQuantite(0);
            outil.setCump(0);
            outil.setIdRegion(region);
            outil.save(connection);
            stockList.add(outil);
        }
        
        // Calcule du cump
        Stock stock = (Stock)stockList.get(0);       // stock unique pour chaque produit
        double cump = findCump(stock, produit, quantite);       
        
        //  Pour eviter les where quantite = 0
        Statement statement = connection.createStatement();
        
        String sql = "update stock set quantite = " + (quantite + stock.getQuantite()) + ", cump = " + cump + " where id = '" + stock.getId() + "'";
        System.out.println(sql);
        
        statement.execute(sql);
        statement.close();
    }
    
    public static void produire(Composant produit, int quantite, String region) throws Exception {
        // Connection pour le transaction
        if(quantite <= 0) throw new Exception("La quantite ne doit pas etre negatif ou null!");
        
        Connection connection = (new Connex()).DBconnect();
        
        // Preparation historique de fabrication
        HistoriqueFabrication historique = new HistoriqueFabrication(new Date(), produit.getId(), quantite, region, produit.getPu());
        historique.save(connection);
        
        // Mis a jour du Stock
        updateStock(produit, quantite, region, connection);
        
        connection.commit();
        connection.close();
    }
    
    public static double getPrix(Composant produit, String region, Connection connection) throws Exception {
        // Renvoie le prix du produit dans un stock
        Stock outil = new Stock();
        outil.setIdProduit(produit.getId());
        outil.setIdRegion(region);
        
        Vector<Object> stockList = outil.getListes(connection);
        if(stockList.size() == 0) throw new Exception("Le stock n'existe pas encore !");
        
        return ((Stock) stockList.get(0)).getCump();
    }
    
    public static void vendre(Composant produit, int quantite, String region) throws Exception {
        if(quantite <= 0) throw new Exception("La quantite ne doit pas etre negatif ou null!");

        // Connection pour le transaction
        Connection connection = (new Connex()).DBconnect();
        
        // Preparation historique de fabrication
        HistoriqueVente historique = new HistoriqueVente(new Date(), produit.getId(), quantite, region, getPrix(produit, region, connection));
        historique.save(connection);
        
        // Mis a jour du Stock
        try {
            updateStock(produit, -quantite, region, connection);        // quantite negatif
        } catch(Exception e) {
            connection.rollback();
            throw e;
        }
        
        connection.commit();
        connection.close();
    }
}

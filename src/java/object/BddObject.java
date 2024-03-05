package object;

import java.io.File;
import java.lang.reflect.*;
import java.text.DateFormat;
import outil.*;
import table.Historique;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.text.Utilities;

import connection.Connex;

public class BddObject {
/// Attribut
    String prefix = "PER";
    int sizePK = 7;
    String nomFonction = "getSeqPersonne";

/// Encapsulation 
    public void setPrefix(String prefix) {this.prefix = prefix;}
    public String getPrefix() {return this.prefix;}

    public void setSizePK(int sizePK) {this.sizePK = sizePK;}
    public int getSizePK() {return this.sizePK;}

    public void setNomFonction(String nomFonction) {this.nomFonction = nomFonction;}
    public String getNomFonction() {return this.nomFonction;}
    
/// Fonctions de classe
    public int getBddTypeFieldsNumber() {
        Field[] attributs = this.getClass().getDeclaredFields();
        int number = 0;
        for(int i = 0; i < attributs.length; i++) {
            if (attributs[i].getType().getName().endsWith("int") || attributs[i].getType().getName().endsWith("String") || attributs[i].getType().getName().endsWith("double") || attributs[i].getType().getName().endsWith("Date")) {
                number++;
            }
        }
        return number;
    }

    public Field[] getBddTypeFields() {
        Field[] attributs = this.getClass().getDeclaredFields();
        Field[] listes = new Field[getBddTypeFieldsNumber()];
        int indice = 0;
        for(int i = 0; i < attributs.length; i++) {
            if (attributs[i].getType().getName().endsWith("int") || attributs[i].getType().getName().endsWith("String") || attributs[i].getType().getName().endsWith("double") || attributs[i].getType().getName().endsWith("Date")) {
                listes[indice] = attributs[i];
                indice++;
            }
        }
        return listes;
    }

    public void historiser(Connection connection, String action) throws Exception {
        Vector<Object> objets = getListes(connection);
        for(int i = 0; i < objets.size(); i++) {
            Historique historique = new Historique(this.getClass().getName().substring(6), action, new Date(), getValeurHistoriser(objets.elementAt(i)));
            historique.save(null);
        }
    }

    public Object formatDate(Object objet) {     // adapter en fonction du type   
        if(objet instanceof Date) {
            Date date = (Date) objet;
            objet = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();
        }
        return objet;
    }

    public String getValeurHistoriser(Object objet) throws Exception {       // Preparer la valeur pour l'historique
        String valeur = "";
        Field[] attributs = getBddTypeFields();
        for(int i = 0; i < attributs.length; i++) {
            valeur += attributs[i].getName();
            valeur += ":";
            valeur += formatDate(Utilitaires.get(attributs[i].getName(), objet));
            valeur += ";";
        }
        return valeur;
    }

    public void delete(Connection connection) throws Exception {     // supprimer
        String tabName = this.getClass().getName().substring(6);        // Pour enlever le package
        String sql = "Delete from " + tabName;
        sql = prepareFiltre(sql, getFilter(this));
        mettreAJour(sql, "delete", connection);
    }

    public String prepareModification(String sql, Object modification) throws Exception {     // preparer le sql au niveau du Set
        Vector<Field> filtres = getFilter(modification);
        for(int i = 0; i < filtres.size(); i++) {
            sql += filtres.elementAt(i).getName();
            sql += " = ";
            sql += formatSql(Utilitaires.get(filtres.elementAt(i).getName(), modification));
            if (filtres.size() - 1 > i) sql += ", ";
        }
        sql = prepareFiltre(sql, getFilter(this));
        System.out.println(sql);
        return sql;
    }

    public void update(Object modification, Connection connection) throws Exception {  // l'appelant (pour le where) sera modifie par l' Emp modification
        String tabName = this.getClass().getName().substring(6);        // Pour enlever le package
        String sql = "Update " + tabName + " set ";
        sql = prepareModification(sql, modification);
        mettreAJour(sql, "update", connection);
    }

    public Object[] prepareArgument(ResultSet resultat) throws Exception {
        Class[] parametres = getConstructorParameters();
        Object[] arguments = new Object[parametres.length];     // le resultat
        for(int i = 0; i < parametres.length; i++) {
            if(parametres[i].getName().compareTo("int") == 0) arguments[i] = resultat.getInt(i + 1);
            else if(parametres[i].getName().compareTo("double") == 0) arguments[i] = resultat.getDouble(i + 1);
            else if(parametres[i].getName().endsWith("String")) arguments[i] = resultat.getString(i + 1);
            else if(parametres[i].getName().endsWith("Date")) arguments[i] = resultat.getDate   (i + 1);
        }
        return arguments;
    }

    public Class[] getConstructorParameters() {
        Field[] attributs = getBddTypeFields();
        Class[] parametres = new Class[attributs.length];
        for(int i = 0; i < attributs.length; i++) {
            parametres[i] = attributs[i].getType();
        }
        return parametres;
    }

    public Vector<Object> selection(Connection connection, String sql) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();

            Class[] parametres = getConstructorParameters();
            Constructor constructeur = this.getClass().getDeclaredConstructor(parametres);
            ResultSet resultat = statement.executeQuery(sql);
            Vector<Object> listes = new Vector<Object>();
            while(resultat.next()) {
                Object[] arguments = prepareArgument(resultat);
                listes.add(constructeur.newInstance(arguments));
            }
            statement.close();
            return listes;
        } catch (Exception e) {
            System.out.println(e);
            if (connection != null) {
                connection.close();
            }
            if (statement != null) statement.close();
        }
        return null;
    }

    public boolean checkFilterValidity(Field attribut, Object appelant) throws Exception {        // Verifie si l'attribut est null ou 0
        Object value = Utilitaires.get(attribut.getName(), appelant);
        if(value == null) return false;
        else if(value instanceof Integer) {
            Integer valeur = (Integer) value;
            if (valeur.intValue() == 0) return false;         
        }
        else if(value instanceof Double) {
            Double valeur = (Double) value;
            if (valeur.doubleValue() == 0) return false;         
        }
        return true;
    }

    public Vector<Field> getFilter(Object appelant) throws Exception {          // Savoir et avoir les filtres 'where'
        Field[] attributs = getBddTypeFields();
        Vector<Field> listes = new Vector<Field>();
        for(int i = 0; i < attributs.length; i++) {
            if(checkFilterValidity(attributs[i], appelant)) {
                listes.add(attributs[i]);
            }
        }
        if(listes.size() == 0) return null;
        return listes;
    }

    public Object formatSqlFilter(Object objet) {     // adapter en fonction du type   
        if (objet instanceof String) {
            objet = "'%" + (String) objet + "%'";
        }
        else if (objet instanceof Date) {
            Date date = (Date) objet;
            DateFormat shortDF = DateFormat.getDateInstance(DateFormat.SHORT);
            String prepare = shortDF.format(date);
            String dateValue = prepareDate(prepare);
            objet = "Date " + "'" + dateValue + "'";
        }
        return objet;
    }

    public String prepareFiltre(String sql, Vector<Field> filtres) throws Exception {        // preparation du filtre 'where'
        sql += " where ";
        for(int i = 0; i < filtres.size(); i++) {
            sql += filtres.elementAt(i).getName();
            if (Utilitaires.get(filtres.elementAt(i).getName(), this) instanceof String) sql += " like ";
            else sql += " = ";
            sql +=  formatSqlFilter(Utilitaires.get(filtres.elementAt(i).getName(), this));
            if (i < filtres.size() - 1) sql += " and ";
        }
        return sql;
    }

    public Vector<Object> getListes(Connection connection) throws Exception {
        boolean closeEtat = false; 
        if (connection == null) {
            Connex connex = new Connex();
            connection = connex.DBconnect();
            closeEtat = true;
        }
        Vector<Field> filtres = getFilter(this);
        if (filtres == null) {
            String tabName = this.getClass().getName().substring(6);
            String sql = "select * from " + tabName;
            System.out.println(sql);
            Vector<Object> listes = selection(connection, sql);
            if(closeEtat) connection.close();
            return listes;
        }
        else {
            String tabName = this.getClass().getName().substring(6);
            String sql = "select * from " + tabName;
            sql = prepareFiltre(sql, filtres);      // preparer le filtre pour le sql
            Vector<Object> listes = selection(connection, sql);
            if(closeEtat) connection.close();
            return listes;
        }
    }

    public void mettreAJour(String sql, String action, Connection connection) throws Exception {        // execution du requete insert
        boolean closeEtat = false; 
        if (connection == null) {
            Connex connex = new Connex();
            connection = connex.DBconnect();
            closeEtat = true;
        }
        
        Statement statement = null;
        try {
            statement = connection.createStatement();
            // historiser(connection, action);
            statement.executeUpdate(sql);
            statement.close();
            if(closeEtat) {
                connection.commit();
                connection.close();        
            }
        } catch (Exception e) {
            System.out.println(e);
            if (statement != null) statement.close();
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
        }
    }

    public String prepareDate(String otherFormat) { //    Transforme 12/11/2022 en 2022-11-12
        String[] division = otherFormat.split("/");
        String resultat = division[2] + "-" + division[1] + "-" + division[0];
        return resultat;
    }

    public Object formatSql(Object objet) {     // adapter en fonction du type   
        if (objet instanceof String) {
            objet = "'" + (String) objet + "'";
        }
        else if (objet instanceof Date) {
            Date date = (Date) objet;
            DateFormat shortDF = DateFormat.getDateInstance(DateFormat.SHORT);
            String prepare = shortDF.format(date);
            String dateValue = prepareDate(prepare);
            objet = "Date " + "'" + dateValue + "'";
        }
        return objet;
    }

    public String prepareSql() throws Exception {
        String tabName = this.getClass().getName().substring(6);
        Field[] attributs = getBddTypeFields();
        String sql = "insert into " + tabName + " values (";
        for(int i = 0; i < attributs.length; i++) {
            sql = sql + formatSql(Utilitaires.get(attributs[i].getName(), this));
            if (i == attributs.length - 1)  sql += ")";
            else sql += ", ";
        }
        System.out.println(sql);
        return sql;
    }

    public int getSequence(Connection connection) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery("select " + getNomFonction() + " from dual");
            if(resultat.next()) return resultat.getInt(1);
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
            if (statement != null) statement.close();
        }
        return 0;
    }

    public String completerZero(int seq, int taillePrefix) {
        String sequence = String.valueOf(seq);
        int manquant = getSizePK() - taillePrefix - sequence.length();
        String complet = "";
        for(int i = 0; i < manquant; i++) {
            complet += "0";
        }
        return complet + sequence;
    }
    
    public String construirePK(Connection c) throws Exception {     // Construit en string le cle primaire
        int seq = getSequence(c);
        String cle = getPrefix() + completerZero(seq, getPrefix().length());
        Utilitaires.setPrimaryKey(cle, this);
        return cle;
    }

    public void save(Connection connection) throws Exception {       // Inserer generaliser
        boolean closeEtat = false; 
        if (connection == null) {
            Connex connex = new Connex();
            connection = connex.DBconnect();
            closeEtat = true;
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
            construirePK(connection);
            statement.executeUpdate(prepareSql());
            statement.close();
            if (closeEtat) {
                connection.commit();
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            if (statement != null) statement.close();
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
        }
    }

}
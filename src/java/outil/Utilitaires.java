package outil;

import java.lang.reflect.*;

public class Utilitaires {

    public static String firstUpper(String mot) {   // transforme le premier mot en consonne
        char[] division = mot.toCharArray();
        division[0] = Character.toUpperCase(division[0]);
        String resultat = new String(division);
        return resultat;
    }
    
    public static Object get(String attribut, Object objet) throws Exception {       // Fonction pour getter dans n'importe quel class
        attribut = firstUpper(attribut);
        String fonction = "get" + attribut;
        Method method = objet.getClass().getDeclaredMethod(fonction, new Class[0]);     // trouve le fonction correspondant
        Object resultat = method.invoke(objet, new Object[0]);
        return resultat;
    }   
    
    public static void setPrimaryKey(String valeur, Object objet) throws Exception {       // Fonction pour getter dans n'importe quel class
        String fonction = "setId";
        Class[] argument = new Class[1];
        argument[0] = valeur.getClass();
        Method method = objet.getClass().getDeclaredMethod(fonction, argument);     // trouve le fonction correspondant
        Object[] parametre = new Object[1];
        parametre[0] = valeur;
        Object resultat = method.invoke(objet, parametre);
    }

    public static String[] getAllAttributsName(Object objet) {          // Avoir tous les attributs du classe
        String[] listes = new String[objet.getClass().getDeclaredFields().length];
        Field[] attributs = objet.getClass().getDeclaredFields();
        for(int i = 0; i < listes.length; i++) {
            listes[i] = attributs[i].getName();
        }
        return listes;
    }
}

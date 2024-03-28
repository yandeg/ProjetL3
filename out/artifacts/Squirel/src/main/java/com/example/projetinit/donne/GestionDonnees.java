package com.example.projetinit.donne;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GestionDonnees {
    /** Liste observable des éléments disponibles. */
    private static ObservableList<Element> elements = FXCollections.observableArrayList();
    /** Liste observable des chaînes de production. */
    private static ObservableList<Chaines> chaines = FXCollections.observableArrayList();
    /** Liste observable des prix. */
    private static ObservableList<Prix> prix = FXCollections.observableArrayList();

    /**
     * Constructeur par défaut de la classe.
     * Ce constructeur ne fait rien, il est présent uniquement pour une éventuelle utilisation future.
     */
    public GestionDonnees() {
    }
    /**
     * Retourne la liste observable des éléments disponibles.
     *
     * @return La liste observable des éléments disponibles.
     */
    public static ObservableList<Element> getElements() {
        return elements;
    }
    /**
     * Retourne la liste observable des chaînes de production.
     *
     * @return La liste observable des chaînes de production.
     */
    public static ObservableList<Chaines> getChaineProd() {
        return chaines;
    }
    /**
     * Retourne la liste observable des prix.
     *
     * @return La liste observable des prix.
     */
    public static ObservableList<Prix> getPricingData() {
        return prix;
    }


    /**
     * Ajoute un stock à un élément spécifié.
     *
     * @param codeE Le code de l'élément auquel ajouter le stock.
     * @param n     La quantité à ajouter au stock.
     * @return Un message indiquant le résultat de l'opération.
     */
    public static String ajouterStock(String codeE, double n){
        String msg="";
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                element.ajouterStockE(n);
                msg = "Vous avez ajouté "+ n + " aux "+codeE;
                return msg;
            }
        }
        msg = "le code suivant n'a pas d'élément associé "+codeE;
        return msg;
    }

    /**
     * Vérifie si un code d'élément existe dans la liste des éléments.
     *
     * @param codeC Le code de l'élément à vérifier.
     * @return true si le code d'élément existe, sinon false.
     */
    public static void ajouterNiveauActivation(String codeC){
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.ajouterNiveauActivation();
            }
        });
    }
    /**
     * Ajoute un niveau d'activation à une chaîne de production spécifiée.
     *
     * @param codeC Le code de la chaîne de production à modifier.
     */
    public static void enleverNiveauActivation(String codeC) {
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.enleverNiveauActivation();
            }
        });
    }

    /**
     * Enlève un niveau d'activation à une chaîne de production spécifiée.
     *
     * @param codeC Le code de la chaîne de production à modifier.
     */
    public static int getNiveauActivation(String codeC) {
        for (Chaines chaine : chaines) {
            if (chaine.getCodeC().equals(codeC)) {
                return chaine.getNiveauActivationC();
            }
        }
        return 0;
    }

    /**
     * Définit la liste des éléments disponibles.
     *
     * @param element La liste des éléments à définir.
     */
 public static void setElements (ObservableList<Element> element){
        elements = element;

 }
    /**
     * Définit la liste des chaînes de production.
     *
     * @param chaine La liste des chaînes de production à définir.
     */
   public static void setChaines (ObservableList<Chaines> chaine){
            chaines = chaine;

    }
    /**
     * Définit la liste des prix.
     *
     * @param pri La liste des prix à définir.
     */
    public static void setPrix (ObservableList<Prix> pri){
        prix = pri;

    }

}
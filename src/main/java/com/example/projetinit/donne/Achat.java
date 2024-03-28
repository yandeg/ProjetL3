package com.example.projetinit.donne;

import com.example.projetinit.attributs.Element;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.projetinit.donne.GestionDonnees.*;


public class Achat {
    private static ObservableList<Achat> achats = FXCollections.observableArrayList();
    private SimpleStringProperty codeE;
    private SimpleDoubleProperty qteAchat;

    /**
     * Constructeur de la classe Achat.
     *
     * @param codeE    Le code de l'élément acheté.
     * @param qteAchat La quantité achetée.
     */

    public Achat(String codeE, double qteAchat) {
        this.codeE = new SimpleStringProperty(codeE);
        this.qteAchat = new SimpleDoubleProperty(qteAchat);
    }

    // Méthodes d'accès et de modification des propriétés
    // Méthodes de la première version, les méthodes actuelles sont plus bas

    /**
     * Retourne le code de l'élément acheté.
     *
     * @return Le code de l'élément acheté.
     */

    public String getCodeE() {
        return codeE.get();
    }

    /**
     * Retourne la quantité achetée.
     *
     * @return La quantité achetée.
     */

    public double getQteAchat() {
        return qteAchat.get();
    }

    /**
     * Modifie le code de l'élément acheté.
     *
     * @param codeE Le nouveau code de l'élément acheté.
     */

    public void setCodeE(String codeE) {
        this.codeE.set(codeE);
    }
    /**
     * Modifie la quantité achetée.
     *
     * @param qteAchat La nouvelle quantité achetée.
     */

    public void setQteAchat(double qteAchat) {
        this.qteAchat.set(qteAchat);
    }
    /**
     * Retourne la liste des achats.
     *
     * @return La liste des achats.
     */

    public static ObservableList<Achat> getAchats() {
        return achats;
    }
    // Méthodes utilitaires

    /**
     * Compare deux codes d'élément.
     *
     * @param codeE Le premier code d'élément.
     * @param codeA Le second code d'élément.
     * @return true si les codes d'élément sont égaux, sinon false.
     */
    public static boolean compareCodeA(String codeE,String codeA){
            if (codeA.equals(codeE)) {
                return true;
            }

        return false;

    }
    /**
     * Modifie les informations d'un achat.
     *
     * @param HApre  L'achat avant modification.
     * @param HApost L'achat après modification.
     */

    public static void modifieEnAchat(Achat HApre, Achat HApost) {
        HApre.setCodeE(HApost.getCodeE());
        HApre.setQteAchat(HApost.getQteAchat());

    }
    /**
     * Retourne une représentation textuelle de l'achat.
     *
     * @return Une chaîne de caractères représentant l'achat.
     */

    @Override
    public String toString() {
        return codeE + " : " + qteAchat;
    }

}
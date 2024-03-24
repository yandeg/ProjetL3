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

    public Achat(String codeE, double qteAchat) {
        this.codeE = new SimpleStringProperty(codeE);
        this.qteAchat = new SimpleDoubleProperty(qteAchat);
    }

    public String getCodeE() {
        return codeE.get();
    }

    public double getQteAchat() {
        return qteAchat.get();
    }

    public void setCodeE(String codeE) {
        this.codeE.set(codeE);
    }

    public void setQteAchat(double qteAchat) {
        this.qteAchat.set(qteAchat);
    }

    public static ObservableList<Achat> getAchats() {
        return achats;
    }


    //FICTIF : A remplacer par la récupération des achats saisis
    public static ObservableList<Achat> remplirAchatsFictifs() {

        return achats;
    }


    //Voir si utile
    //Crée une liste qui contient tous les éléments avec 0 comme quantité d'achats
    public static ObservableList<Achat> initialisationAchats() {
        GestionDonnees gd = new GestionDonnees();
        chargerElements(); // Pour le test. Normalement ça sera déja chargé
        for (Element elt : getElements()) {
            achats.add(new Achat(elt.getCodeE(), 0.0));
        }
        return achats;
    }
    public static boolean verifierCodeA(String codeE){
        for (Achat ha : achats) {
            if (ha.getCodeE().equals(codeE)) {
                return true;
            }
        }
        return false;

    }
    public static boolean compareCodeA(String codeE,String codeA){
            if (codeA.equals(codeE)) {
                return true;
            }

        return false;

    }

    public static void modifieEnAchat(Achat HApre, Achat HApost) {
        HApre.setCodeE(HApost.getCodeE());
        HApre.setQteAchat(HApost.getQteAchat());

    }

    @Override
    public String toString() {
        return codeE + " : " + qteAchat;
    }

}
package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        achats.add(new Achat("E001", 10.0));
        achats.add(new Achat("E002", 400.0));
        achats.add(new Achat("E003", 5.0));
        achats.add(new Achat("E004", 60.0));
        achats.add(new Achat("E005", 25.0));
        achats.add(new Achat("E006", 12.5));
        achats.add(new Achat("E007", 50.0));
        return achats;
    }


    //Voir si utile
    /*
    //Crée une liste qui contient tous les éléments avec 0 comme quantité d'achats
    public static ObservableList<Achat> initialisationAchats() {
        GestionDonnées gd = new GestionDonnées();
        gd.chargerElements(); // Pour le test. Normalement ça sera déja chargé
        for (Element elt : gd.getElements()) {
            achats.add(new Achat(elt.getCodeE(), 0.0));
        }
        return achats;
    }
	*/


    @Override
    public String toString() {
        return codeE + " : " + qteAchat;
    }

}
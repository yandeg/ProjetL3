package com.example.projetinit.donne;

import com.example.projetinit.Affichage;
import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.HashMap;
import java.util.List;


public class GestionDonnees {

    private static ObservableList<Element> elements = FXCollections.observableArrayList();
    private static ObservableList<Chaines> chaines = FXCollections.observableArrayList();
    private static ObservableList<Prix> prix = FXCollections.observableArrayList();


    public GestionDonnees() {
    }

    public static ObservableList<Element> getElements() {
        return elements;
    }

    public static ObservableList<Chaines> getChaineProd() {
        return chaines;
    }

    public static ObservableList<Prix> getPricingData() {
        return prix;
    }



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
    public String reduireStock(String codeE,double n){
        String msg="";
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                element.reduireStockE(n);
                msg = "Vous avez ajouté "+ n + " aux "+codeE;
                return msg;
            }
        }
        msg = "le code suivant n'a pas d'élément associé "+codeE;
        return msg;
    }
    public static boolean verifierCodeE(String codeE){
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                return true;
            }
        }
        return false;

    }

    public static void ajouterNiveauActivation(String codeC){
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.ajouterNiveauActivation();
            }
        });
    }
    public static void enleverNiveauActivation(String codeC) {
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.enleverNiveauActivation();
            }
        });
    }
    public static int getNiveauActivation(String codeC) {
        for (Chaines chaine : chaines) {
            if (chaine.getCodeC().equals(codeC)) {
                return chaine.getNiveauActivationC();
            }
        }
        return 0;
    }
 public static void setElements (ObservableList<Element> element){
        elements = element;

 }
    public static void setChaines (ObservableList<Chaines> chaine){
            chaines = chaine;

    }
    public static void setPrix (ObservableList<Prix> pri){
        prix = pri;

    }

}
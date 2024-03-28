package com.example.projetinit.attributs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class Chaines {
    private SimpleStringProperty codeC;
    private SimpleStringProperty nomC;
    private final HashMap<String,Double> hashElementEntree;
    private final HashMap<String, Double> hashElementSortie;

    private SimpleIntegerProperty niveauActivation = new SimpleIntegerProperty(0);



    public Chaines(String code, String nom, HashMap<String, Double> hashElementEntree , HashMap<String,Double> hashElementSortie) {
        this.codeC = new SimpleStringProperty(code);
        this.nomC = new SimpleStringProperty(nom);
        this.hashElementEntree = hashElementEntree;
        this.hashElementSortie = hashElementSortie;

    }

    public String getCodeC() {
        return codeC.get();
    }

    public void setCodeC(String codeC) {
        this.codeC.set(codeC);
    }

    public String getNomC() {
        return nomC.get();
    }

    public void setNomC(String nomC) {
        this.nomC.set(nomC);
    }

    public HashMap<String, Double> getHashElementEntre() {
        return hashElementEntree;
    }

    public HashMap<String,Double> getHashElementSortie() {
        return hashElementSortie;
    }

    public double getQuantiteEntree(String codeE) {
        return hashElementEntree.get(codeE);
    }

    public double getQuantiteSortie(String codeE) {
        return hashElementSortie.get(codeE);
    }

    public  void ajouterNiveauActivation(){
        this.niveauActivation.set(this.niveauActivation.get()+1);
    }
    public  void enleverNiveauActivation() {
        if (this.niveauActivation.get() > 0) {
            this.niveauActivation.set(this.niveauActivation.get() - 1);
        }
    }
    public  int getNiveauActivationC() {
        return this.niveauActivation.get();
    }
    public String getNiveauActivation() {
        return String.valueOf(niveauActivation.get());
    }
}
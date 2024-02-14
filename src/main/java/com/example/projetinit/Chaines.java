package com.example.projetinit;

import java.util.HashMap;

public class Chaines {
    private String codeC;
    private String nomC;
    private final HashMap<String,Double> hashElementEntree;
    private final HashMap<String, Double> hashElementSortie;



    public Chaines(String code, String nom, HashMap<String, Double> hashElementEntree , HashMap<String,Double> hashElementSortie) {
        this.codeC = code;
        this.nomC = nom;
        this.hashElementEntree = hashElementEntree;
        this.hashElementSortie = hashElementSortie;

    }

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
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


}
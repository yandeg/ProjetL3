package com.example.projetinit.attributs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

/**
 * La classe Chaines représente une chaîne de production dans un système.
 * Chaque chaîne possède un code unique, un nom, ainsi que des éléments d'entrée et de sortie.
 * Elle contient également une propriété de niveau d'activation.
 */

public class Chaines {
    private SimpleStringProperty codeC;
    private SimpleStringProperty nomC;
    private final HashMap<String,Double> hashElementEntree;
    private final HashMap<String, Double> hashElementSortie;

    private SimpleIntegerProperty niveauActivation = new SimpleIntegerProperty(0);

    /**
     * Constructeur de la classe Chaines.
     *
     * @param code             Le code unique de la chaîne.
     * @param nom              Le nom de la chaîne.
     * @param hashElementEntree     Les éléments d'entrée de la chaîne.
     * @param hashElementSortie     Les éléments de sortie de la chaîne.
     */

    public Chaines(String code, String nom, HashMap<String, Double> hashElementEntree , HashMap<String,Double> hashElementSortie) {
        this.codeC = new SimpleStringProperty(code);
        this.nomC = new SimpleStringProperty(nom);
        this.hashElementEntree = hashElementEntree;
        this.hashElementSortie = hashElementSortie;

    }

    /**
     * Obtient le code de la chaine.
     * @return Le code de la chaine.
     */
    public String getCodeC() {
        return codeC.get();
    }

    /**
     * Obtient le nom de la chaine.
     * @return Le nom de la chaine.
     */
    public String getNomC() {
        return nomC.get();
    }


    /**
     * Obtient la liste des éléments en entrée.
     * @return La liste des éléments en entrée.
     */
    public HashMap<String, Double> getHashElementEntre() {
        return hashElementEntree;
    }

    /**
     * Obtient la liste des éléments en sortie.
     * @return La liste des éléments en sortie.
     */
    public HashMap<String,Double> getHashElementSortie() {
        return hashElementSortie;
    }

    /**
     * Obtient la quantité des éléments en entrée.
     * @return La quantité des éléments en entrée.
     */
    public double getQuantiteEntree(String codeE) {
        return hashElementEntree.get(codeE);
    }

    /**
     * Obtient la quantité des éléments en sortie.
     * @return La quantité des éléments en sortie.
     */
    public double getQuantiteSortie(String codeE) {
        return hashElementSortie.get(codeE);
    }

    /**
     * Définit le niveau d'activation
     */
    public  void ajouterNiveauActivation(){
        this.niveauActivation.set(this.niveauActivation.get()+1);
    }
    public  void enleverNiveauActivation() {
        if (this.niveauActivation.get() > 0) {
            this.niveauActivation.set(this.niveauActivation.get() - 1);
        }
    }

    /**
     * Obtient Le niveau d'activation.
     * @return Le niveau d'activation.
     */
    public  int getNiveauActivationC() {
        return this.niveauActivation.get();
    }
    public String getNiveauActivation() {
        return String.valueOf(niveauActivation.get());
    }
}
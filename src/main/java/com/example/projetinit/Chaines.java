/**
 * Cette classe représente une chaîne de production.
 * Une chaîne de production est caractérisée par un code, un nom, des éléments d'entrée et des éléments de sortie.
 */
package com.example.projetinit;

import java.util.HashMap;

public class Chaines {
    private String codeC; // Le code de la chaîne
    private String nomC; // Le nom de la chaîne
    private final HashMap<String,Double> hashElementEntree; // Les éléments d'entrée avec leur quantité
    private final HashMap<String, Double> hashElementSortie; // Les éléments de sortie avec leur quantité

    /**
     * Constructeur de la classe Chaines.
     * @param code Le code de la chaîne.
     * @param nom Le nom de la chaîne.
     * @param hashElementEntree La liste des éléments d'entrée avec leur quantité.
     * @param hashElementSortie La liste des éléments de sortie avec leur quantité.
     */
    public Chaines(String code, String nom, HashMap<String, Double> hashElementEntree , HashMap<String,Double> hashElementSortie) {
        this.codeC = code;
        this.nomC = nom;
        this.hashElementEntree = hashElementEntree;
        this.hashElementSortie = hashElementSortie;
    }

    /**
     * Getter pour le code de la chaîne.
     * @return Le code de la chaîne.
     */
    public String getCodeC() {
        return codeC;
    }

    /**
     * Setter pour le code de la chaîne.
     * @param codeC Le code de la chaîne à définir.
     */
    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    /**
     * Getter pour le nom de la chaîne.
     * @return Le nom de la chaîne.
     */
    public String getNomC() {
        return nomC;
    }

    /**
     * Setter pour le nom de la chaîne.
     * @param nomC Le nom de la chaîne à définir.
     */
    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    /**
     * Getter pour les éléments d'entrée de la chaîne avec leur quantité.
     * @return La liste des éléments d'entrée avec leur quantité.
     */
    public HashMap<String, Double> getHashElementEntre() {
        return hashElementEntree;
    }

    /**
     * Getter pour les éléments de sortie de la chaîne avec leur quantité.
     * @return La liste des éléments de sortie avec leur quantité.
     */
    public HashMap<String,Double> getHashElementSortie() {
        return hashElementSortie;
    }

    /**
     * Obtient la quantité d'un élément d'entrée spécifié par son code.
     * @param codeE Le code de l'élément d'entrée.
     * @return La quantité de l'élément d'entrée.
     */
    public double getQuantiteEntree(String codeE) {
        return hashElementEntree.get(codeE);
    }

    /**
     * Obtient la quantité d'un élément de sortie spécifié par son code.
     * @param codeE Le code de l'élément de sortie.
     * @return La quantité de l'élément de sortie.
     */
    public double getQuantiteSortie(String codeE) {
        return hashElementSortie.get(codeE);
    }
}

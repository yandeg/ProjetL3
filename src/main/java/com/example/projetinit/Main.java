package com.example.projetinit;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;

import java.util.List;

import static com.example.projetinit.donne.GestionDonnees.*;

public class Main {

    /**
     * Méthode principale du programme.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {


        Affichage afficher = new Affichage();


        List<Element> elements = getElements();
        List<Chaines> productionChaine = getChaineProd();

        // Affichage des éléments
        System.out.println("Liste des éléments :");
        for (Element element : elements) {
            afficher.affichage(element.getCodeE() + ", " + element.getNomE() + ", " + element.getQuantite() + " " + element.getUnite());

        }
        afficher.affichage(ajouterStock("E008", 15));

        System.out.println("Liste des éléments :");
        for (Element element : elements) {
            afficher.affichage(element.getCodeE() + ", " + element.getNomE() + ", " + element.getQuantite() + " " + element.getUnite());

        }


        // Affichage des chaînes de production
        System.out.println("\nListe des chaînes de production :");
        for (Chaines productionChain : productionChaine) {
            System.out.println(productionChain.getCodeC() + ", " + productionChain.getNomC() + ", " + productionChain.getHashElementEntre()+", "+productionChain.getHashElementSortie());
        }

    }
}
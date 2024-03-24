package com.example.projetinit;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import static com.example.projetinit.donne.GestionDonnees.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        Affichage afficher = new Affichage();
        chargerElements();
        chargerChaineProd();
        chargerPrix();

        List<Element> elements = getElements();
        List<Chaines> productionChaine = getChaineProd();
        List<Prix> pricingData = getPricingData();

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

        // Affichage des données de prix
//        System.out.println("\nListe des données de prix :");
//        for (Prix pricing : pricingData) {
//            afficher.affichage(pricing.getCodeE() + ", " + pricing.getPrixAchat() + ", " + pricing.getPrixVente() + ", " + pricing.getQteCommande());
//	}

        //   afficher.affichage(""+calcul.calculeIndicateurValeur(elements, pricingData));

    }
}
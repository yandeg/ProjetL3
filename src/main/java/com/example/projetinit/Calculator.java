package com.example.projetinit;

import java.util.List;

public class Calculator {

    public double calculeIndicateurValeur( List<Element> elements, List<Prix> prix) {
        double totalPurchaseValue = 0.0;
        double totalSaleValue = 0.0;

        for (Element element : elements) {
            double prixAchat = getPrixAchat(prix, element.getCodeE());
            double prixVente = getPrixVente(prix, element.getCodeE());

            if (prixAchat != -1 ) {
                // Calculer la valeur totale des achats
                double purchaseValue = prixAchat * element.getQuantite();
                totalPurchaseValue += purchaseValue;

            }
            if ( prixVente != -1) {
            // Calculer la valeur totale des ventes
            double saleValue = prixVente * element.getQuantite();
            totalSaleValue += saleValue;
        }
        }
        // Calculer l'indicateur de valeur
        return totalSaleValue - totalPurchaseValue;
    }

    private double getPrixAchat(List<Prix> prix, String elementCode) {
        for (Prix prix1 : prix) {
            if (prix1.getCodeE().equals(elementCode)) {
                return prix1.getPrixAchat();
            }
        }
        return -1; // Retourne -1 si le prix d'achat n'est pas trouvé
    }

    private double getPrixVente(List<Prix> prix, String elementCode) {
        for (Prix prix1 : prix) {
            if (prix1.getCodeE().equals(elementCode)) {
                return prix1.getPrixVente();
            }
        }
        return -1; // Retourne -1 si le prix de vente n'est pas trouvé
    }

    
    
    
    public double calculeIndicateurCommande(List<Prix> prix,List<Element> elements) {
    	
		return 0;
        // Logique de calcul de l'indicateur de commande
    }
}

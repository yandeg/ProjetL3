package com.example.projetinit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calcul {
	
	// INITIALISATION DES DONNEES
	
	// HashMap pour stocker le code des éléments et leur stock fictif
	private HashMap<String,Double> mapStockFictif = new HashMap<>();
	
	// HashMap qui contiendra le code des éléments et leur quantité d'achat saisie
	private HashMap<String, Double> mapAchats = new HashMap<>();
		
	// Crée une instance de GestionDonnées pour utiliser ses méthodes
	// et des listes vide pour les infos d'éléments et prix
	private GestionDonnées gd = new GestionDonnées();
	private List<Element> listeE;
	private List<Prix> listePrix;
		
	//Récupérer le niveau d'activité pour chaque chaine (saisi par l'utilisteur)
	// -> A creer
	
	//Récupérer les achats : quantité et éléments concernés (saisi par l'utilisteur)
	// Voir si la HashMap pose problème (va dépendre de si l'utilisateur peut saisir plusieurs
	// 									 lignes d'achat pour un même produit)
	// Méthode à remplacer par une lecture des achats saisis par l'utilisateur
	// -> Données renseignées pour le test	
	public HashMap<String, Double> recupererAchats() {
		mapAchats.put("E005", 100.0);
		mapAchats.put("E006", 10.0);
		mapAchats.put("E007", 50.0);
		mapAchats.put("E003", 250.0);
		return mapAchats;
	}
		
	// Initialiser la Map mapStockFictif avec les éléments du fichier elements.csv
	public HashMap<String, Double> initialiserMapStockFictif() {
		gd.chargerElements();
		listeE = gd.getElements();
		for (Element element : listeE) {
            mapStockFictif.put(element.getCodeE(), element.getQuantite());
        }
		return mapStockFictif;
	}
	
	// Modifie la valeur dans la Map si le codeE existe comme clé
	public HashMap<String, Double> modifierStockFictif(String code, double stockFictif) {
		if (mapStockFictif.containsKey(code)) {
			mapStockFictif.put(code, stockFictif);
		} else {
			//Mettre une exception ici
			System.out.println("Erreur : code inexistant");
		}
		return mapStockFictif;
	}
		
	// getter
	public HashMap<String, Double> getMapStockFictif() {
		return mapStockFictif;
	}
		
		
	
	//INDICATEUR DE VALEUR 
	
	public double calculerIndicateurValeur() {
		prendreEnCompteAchats();
		prendreEnCompteProduction();
		return mesurerEfficacite();
	}
	
	// Les éléments saisis dans les achats sont ajoutés au stock fictif
	public HashMap<String, Double> prendreEnCompteAchats() {
		mapAchats = recupererAchats();		
		for (String element : mapAchats.keySet()) {
			//Mettre une exception pour quand la clé n'est pas dans mapStockFictif
			modifierStockFictif(element, mapStockFictif.get(element) + mapAchats.get(element));	
		}
		return mapStockFictif;
	}
		
	// Prendre en compte la production : enlever les éléments en entrée et ajouter ceux en sortie
	public HashMap<String, Double> prendreEnCompteProduction() {
		// Utiliser la même logique que pour prendreEnCompteAchats()
		// à partir de chaines de production.csv et du niveau d'activation saisi
		// Multiplier la quantité par le niveau d'activation
		return mapStockFictif;
	}
	
	// Mesurer l'efficacité de la production : comparer les ventes possibles avec les achats
	public double mesurerEfficacite() {
		double sommeValeurVente = 0.0;
		double sommeAchats = 0.0;
		for (String element : mapStockFictif.keySet()) {
			if (mapStockFictif.get(element) < 0.0) {
				// Production impossible
			} else {
				// Ajout valeur vente (si a un prix de vente) -> sommeValeurVente += ...
			}
		}
		// Récupérer les codes et quantités de mapAchats et récupérer les prix d'achat de prix.csv
		return sommeValeurVente - sommeAchats;
	}

	

	
	// INDICATEUR DE COMMANDE
	
	public double calculerIndicateurCommande() {
		return calculerPourcentage(creerMapCommandes());
	}
	
	// Créer une HashMap avec le code comme clé et la qté commandée comme valeur
	// Peut-être pas le plus efficace (on met dans une liste puis dans la HashMap)
	public HashMap<String, Double> creerMapCommandes() {
		gd.chargerPrix();
		listePrix = gd.getPricingData();
		HashMap<String, Double> mapCommandes = new HashMap<>();
		for (Prix element : listePrix) {
            mapCommandes.put(element.getCodeE(), element.getQteCommande());
        }
		return mapCommandes;
	}
		
	
	// Renvoyer la moyenne des pourcentage de commandes satisfaites
	public double calculerPourcentage(HashMap<String, Double> map) {
		// Créer une ArrayList et y ajouter le pourcentage de satisfaction pour chaque élément
	 	ArrayList<Double> percentStockElt = new ArrayList<>();
	 	for (String element : map.keySet()) {
	 		if (map.get(element) > 0.0) { // On prend que ceux commandés
	 			double pourcentage = mapStockFictif.get(element) / map.get(element);
	 			if (pourcentage > 1.0) { // S'il y en a + en stock que commandés -> 100%
	 				percentStockElt.add(1.0);
	 			} else {
	 				percentStockElt.add(pourcentage);
	 			}
	 		}
	 	}
	 	// Faire la moyenne du pourcentage de satisfaction de chaque élément -> pourcentage global
	 	double total = 0.0;
	 	for(int i = 0; i < percentStockElt.size(); i++) {
	 		total += percentStockElt.get(i);
	 	}
	     return total / percentStockElt.size(); // A formater pour afficher en pourcentage   
	}
	
	
}

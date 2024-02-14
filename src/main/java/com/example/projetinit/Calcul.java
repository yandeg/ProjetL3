package com.example.projetinit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calcul {

	private static double indicateurValeur;
	private static double indicateurCommande;

	//private static List<Chaines> chaines;

	// HashMaps pour faciliter les traitements (duplication eléments)
	private static HashMap<String,Double> mapStockFictif = new HashMap<>();

	private static String messageProdImpossible = "";

	public static double getIndicateurValeur() {
		return indicateurValeur;
	}
	public static double getIndicateurCommande() {
		return indicateurCommande;
	}
	public static String getMessageProdImpossible () {
		return messageProdImpossible;
	}

	// Méthode utilisée quand l'utilisateur clique pour faire les calculs
	//IL faut insérer une liste de code de chaine de production suvi à chauqe fois du niveau d'activation du type (C0001,2,C0002,3)
	public static void faireLesCalculs(List<Prix> listePrix, List<Element> listeElements, List<Chaines> chaines, ArrayList<String> k) {
		initialiserMapStockFictif(listeElements);
		indicateurValeur = calculerIndicateurValeur(listePrix, k, chaines);
		indicateurCommande = calculerIndicateurCommande(listePrix);
		//chaines = chaines;
	}


	//Détail :

	public static HashMap<String, Double> initialiserMapStockFictif(List<Element> listeElements) {
		for (Element element : listeElements) {
			mapStockFictif.put(element.getCodeE(), element.getQuantite());
		}
		return mapStockFictif;
	}

	public static double calculerIndicateurValeur(List<Prix> listePrix, ArrayList<String> k, List<Chaines> chaines) {
		prendreEnCompteAchats();
		prendreEnCompteProduction(k, chaines); // A FAIRE
		return mesurerEfficacite(listePrix);
	}

	public static double calculerIndicateurCommande(List<Prix> listePrix) {
		ArrayList<Double> percentStockElt = new ArrayList<>(); //pr le pourcentage
		for (Prix p : listePrix) {
			if (p.getQteCommande() > 0.0) { // On prend que ceux commandés
				double pourcentage = mapStockFictif.get(p.getCodeE()) / p.getQteCommande();
				if (pourcentage > 1.0) { // S'il y en a + en stock que commandés -> 100%
					percentStockElt.add(1.0);
				} else {
					percentStockElt.add(pourcentage);
				}
			}
		}
		double total = 0.0;
		for(int i = 0; i < percentStockElt.size(); i++) {
			total += percentStockElt.get(i);
		}
		return total / percentStockElt.size(); // A formater pour afficher en pourcentage
	}



	// Détail pour le calcul de l'indicateur de valeur :

	public static HashMap<String, Double> prendreEnCompteAchats() {
		System.out.println("mapAchat avant : " + Achat.getAchats()); //test
		System.out.println("mapStock avant : " + mapStockFictif); //test
		for (Achat ha : Achat.getAchats()) {
			if (mapStockFictif.containsKey(ha.getCodeE())) {
				mapStockFictif.put(ha.getCodeE(), mapStockFictif.get(ha.getCodeE()) + ha.getQteAchat());
			} else {
				//Mettre une exception ici
				System.out.println("Erreur : code inexistant");
			}
		}
		System.out.println("mapStock apres : " + mapStockFictif); //test
		return mapStockFictif;
	}


	// A FAIRE
	public static HashMap<String, Double> prendreEnCompteProduction(ArrayList<String> k, List<Chaines> chaines) {
		for (int i=0;i<k.size();i+=2) {
			int activation = Integer.parseInt(k.get(i + 1));
			for (Chaines chaine : chaines) {
				if (chaine.getCodeC().equals(k.get(i))) {
					for (String u : chaine.getHashElementEntre().keySet()) {
						mapStockFictif.put(u, mapStockFictif.get(u) - Double.parseDouble(String.valueOf(activation)) * chaine.getQuantiteEntree(u));
					}
					for (String u : chaine.getHashElementSortie().keySet()) {
						mapStockFictif.put(u, mapStockFictif.get(u) + Double.parseDouble(String.valueOf(activation)) * chaine.getQuantiteSortie(u));
					}
				}
			}
		}
		System.out.println("mapStock apres prod : " + mapStockFictif); //test
		return mapStockFictif;
	}


	public static double mesurerEfficacite(List<Prix> listePrix) {
		double sommeVentes = 0.0;
		double sommeAchats = 0.0;
		// Si stock (fictif) négatif apres la simulation : production impossible
		boolean prodPossible = true;
		List<String> codesPasPossible = new ArrayList<>();
		for (String element : mapStockFictif.keySet()) {
			if (mapStockFictif.get(element) < 0.0) {
				prodPossible = false;
				codesPasPossible.add(element);
				//exception : production impossible
			}
		}
		if (!prodPossible) {
			messageProdImpossible = "\n\nATTENTION ! \nLa production n'est pas "
					+ "possible dans ces conditions, le stock de "
					+ codesPasPossible + " serait négatif.";
			System.out.println(messageProdImpossible);
		}
		// Calcul des sommes
		for (Prix p : listePrix) {
			for (Achat ha : Achat.getAchats()) {
				if (p.getCodeE().equals(ha.getCodeE())) {
					sommeAchats += (p.getPrixAchat() * ha.getQteAchat());
				}
			}

			if (p.getQteCommande() > 0.0) {
				if (p.getQteCommande() <= mapStockFictif.get(p.getCodeE())) {
					sommeVentes += (p.getPrixVente() * p.getQteCommande());
				} else {
					sommeVentes += (p.getPrixVente() * mapStockFictif.get(p.getCodeE()));
				}
			}
		}
		System.out.println("Somme Ventes : " + sommeVentes); //test
		System.out.println("Somme Achats : " + sommeAchats); //test
		return sommeVentes - sommeAchats;
	}


}
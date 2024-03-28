package com.example.projetinit.donne;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.projetinit.donne.Achat.getAchats;
import static com.example.projetinit.donne.GestionDonnees.*;

public class Calcul {
	/** Indicateur de valeur calculé. */
	private static double indicateurValeur;
	/** Indicateur de commande calculé. */
	private static double indicateurCommande;
	/** Carte stock fictif contenant les quantités des éléments. */

	//private static List<Chaines> chaines;

	// HashMaps pour faciliter les traitements (duplication eléments)
	/** Carte stock fictif contenant les quantités des éléments. */
	private static HashMap<String,Double> mapStockFictif = new HashMap<>();
	/** Message indiquant si la production est impossible. */
	private static String messageProdImpossible = "";

	/**
	 * Retourne l'indicateur de valeur calculé.
	 *
	 * @return L'indicateur de valeur calculé.
	 */
	public static double getIndicateurValeur() {
		return indicateurValeur;
	}
	/**
	 * Retourne l'indicateur de commande calculé.
	 *
	 * @return L'indicateur de commande calculé.
	 */
	public static double getIndicateurCommande() {
		return indicateurCommande;
	}
	/**
	 * Retourne le message indiquant si la production est impossible.
	 *
	 * @return Le message indiquant si la production est impossible.
	 */
	public static String getMessageProdImpossible () {
		return messageProdImpossible;
	}

	// Méthode utilisée quand l'utilisateur clique pour faire les calculs
	//IL faut insérer une liste de code de chaine de production suvi à chauqe fois du niveau d'activation du type (C0001,2,C0002,3)
	/**
	 * Effectue les calculs pour les indicateurs de valeur et de commande.
	 * Cette méthode doit être appelée lorsque l'utilisateur déclenche les calculs.
	 * Elle initialise la carte de stock fictif, calcule l'indicateur de valeur et l'indicateur de commande.
	 */
	public static void faireLesCalculs() {
		initialiserMapStockFictif();
		indicateurValeur = calculerIndicateurValeur();
		indicateurCommande = calculerIndicateurCommande();
		//chaines = chaines;
	}


	//Détail :
	/**
	 * Initialise la carte de stock fictif avec les quantités initiales des éléments.
	 *
	 * @return La carte de stock fictif initialisée.
	 */
	public static HashMap<String, Double> initialiserMapStockFictif() {
		for (Element element : getElements()) {
			mapStockFictif.put(element.getCodeE(), element.getQuantite());
		}
		return mapStockFictif;
	}
	/**
	 * Calcule l'indicateur de valeur.
	 *
	 * @return L'indicateur de valeur calculé.
	 */
	public static double calculerIndicateurValeur() {
		prendreEnCompteAchats();
		prendreEnCompteProduction( ); // A FAIRE
		return mesurerEfficacite(getPricingData());
	}

	/**
	 * Calcule l'indicateur de commande.
	 *
	 * @return L'indicateur de commande calculé.
	 */
	public static double calculerIndicateurCommande() {
		ArrayList<Double> percentStockElt = new ArrayList<>(); //pr le pourcentage
		for (Prix p : getPricingData()) {
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



	// Méthodes privées pour les détails de calcul
	/**
	 * Met à jour la carte de stock fictif en prenant en compte les achats.
	 *
	 * @return La carte de stock fictif mise à jour.
	 */

	public static HashMap<String, Double> prendreEnCompteAchats() {

		System.out.println();
		System.out.println("mapAchat avant : " + getAchats()); //test
		System.out.println("mapStock avant : " + mapStockFictif); //test
		for (Achat ha : getAchats()) {
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
	/**
	 * Met à jour la carte de stock fictif en prenant en compte la production.
	 *
	 * @return La carte de stock fictif mise à jour.
	 */
	public static HashMap<String, Double> prendreEnCompteProduction( ) {
			for (Chaines chaine : getChaineProd()) {
					System.out.println("Niveau d'activation : " + chaine.getCodeC()+ chaine.getNiveauActivationC());
					for (String u : chaine.getHashElementEntre().keySet()) {
						mapStockFictif.put(u, mapStockFictif.get(u) - Double.parseDouble(String.valueOf(chaine.getNiveauActivationC())) * chaine.getQuantiteEntree(u));
					}
					for (String u : chaine.getHashElementSortie().keySet()) {
						mapStockFictif.put(u, mapStockFictif.get(u) + Double.parseDouble(String.valueOf(chaine.getNiveauActivationC())) * chaine.getQuantiteSortie(u));
					}
			}
		System.out.println("mapStock apres prod : " + mapStockFictif); //test
		return mapStockFictif;
	}

	/**
	 * Mesure l'efficacité en calculant la différence entre les ventes et les achats.
	 *
	 * @param listePrix La liste des prix utilisée pour les calculs.
	 * @return La différence entre les ventes et les achats.
	 */
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
			for (Achat ha : getAchats()) {
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
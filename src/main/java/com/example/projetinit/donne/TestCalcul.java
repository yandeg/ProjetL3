/**
 * Cette classe est responsable de l'obtention des résultats des calculs effectués sur nos données.
 * Elle charge les données à partir de la classe GestionDonnees, effectue les calculs à l'aide de la classe Calcul,
 * puis enregistre les résultats dans une liste pour les retourner.
 */
package com.example.projetinit.donne;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.projetinit.donne.Achat.*;
import static com.example.projetinit.donne.GestionDonnees.*;

public class TestCalcul {

	public static List<String> infosActivation;

	/**
	 * Méthode permettant d'obtenir les résultats des calculs effectués sur les données.
	 * @return Une liste contenant les résultats des calculs effectués.
	 */
	public List<String> obtenirResultats() {
		// Charger les données
		GestionDonnees gd = new GestionDonnees();
		chargerElements();
		List<Element> listeElements = getElements();

		chargerChaineProd();
		List<Chaines> listeChaines = getChaineProd();

		chargerPrix();
		List<Prix> listePrix = getPricingData();

		getAchats();

		infosActivation = generateRandomInput(listeChaines);

		// Effectuer les calculs
		Calcul.faireLesCalculs(listePrix, listeElements, listeChaines, infosActivation);

		// Enregistrer les différents résultats
		ArrayList<String> resultats = new ArrayList<>();
		resultats.add("Indicateur de valeur : " + Calcul.getIndicateurValeur());
		resultats.add("\nIndicateur de commande : " + Calcul.getIndicateurCommande() + "\n");

		for (Prix p : listePrix) {
			resultats.add("code : " + p.getCodeE() + " prix achat : " + p.getPrixAchat() +
					" prix vente : " + p.getPrixVente() + " qté commandée :" + p.getQteCommande());
		}

		for (Element e : listeElements) {
			resultats.add("code : " + e.getCodeE() + " nom : " + e.getNomE() + " quantité : " +
					e.getQuantite() + " unité :" + e.getUnite());
		}

		for (Chaines c : listeChaines) {
			resultats.add("code : " + c.getCodeC() + " nom : " + c.getNomC() + " entrée : " +
					c.getHashElementEntre() + " sortie :" + c.getHashElementSortie());
		}

		// Retourner tous les résultats enregistrés au fur et à mesure
		return resultats;
	}

	/**
	 * Méthode privée pour générer des données d'entrée aléatoires pour les tests.
	 *
	 * @param listeChaines La liste des chaînes de production pour lesquelles générer des données d'entrée aléatoires.
	 * @return Une liste de données d'entrée aléatoires pour les chaînes de production.
	 */
	private List<String> generateRandomInput(List<Chaines> listeChaines) {
		ArrayList<String> randomInput = new ArrayList<>();
		Random random = new Random();

		for (Chaines c : listeChaines) {
			randomInput.add(c.getCodeC());
			// Exemple: nombre aléatoire entre 0 et 5
			randomInput.add(String.valueOf(random.nextInt(0, 6)));
		}

		return randomInput;
	}
}

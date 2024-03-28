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
import static com.example.projetinit.donne.Calcul.*;
import static com.example.projetinit.donne.GestionDonnees.*;

public class TestCalcul {

	public static List<String> infosActivation;

	/**
	 * Méthode permettant d'obtenir les résultats des calculs effectués sur les données.
	 * @return Une liste contenant les résultats des calculs effectués.
	 */
	public List<String> obtenirResultats() {
		// Charger les données

		List<Element> listeElements = getElements();

		List<Chaines> listeChaines = getChaineProd();


		List<Prix> listePrix = getPricingData();

		getAchats();


		// Effectuer les calculs
		faireLesCalculs();

		// Enregistrer les différents résultats
		ArrayList<String> resultats = new ArrayList<>();
		resultats.add("Indicateur de valeur : " + getIndicateurValeur());
		resultats.add("\nIndicateur de commande : " + getIndicateurCommande() + "\n");

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

}

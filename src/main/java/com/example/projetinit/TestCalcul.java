package com.example.projetinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCalcul {

	public List<String> obtenirResultats() {
		// charger les données
		GestionDonnees gd = new GestionDonnees();
		gd.chargerElements();
		List<Element> listeElements = gd.getElements();

		gd.chargerChaineProd();
		List<Chaines> listeChaines = gd.getChaineProd();

		gd.chargerPrix();
		List<Prix> listePrix = gd.getPricingData();

		Achat.remplirAchatsFictifs();

		// calculs
		Calcul.faireLesCalculs(listePrix, listeElements, listeChaines, generateRandomInput(listeChaines));

		// Enregistrer les différents resultats
		ArrayList<String> resultats = new ArrayList<>();

		resultats.add("Indicateur de valeur : " + Calcul.getIndicateurValeur());
		resultats.add("\nIndicateur de commande : " + Calcul.getIndicateurCommande()+"\n");

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

		// retourner tout les resultats enregistrer au fur à mesure
		return resultats;

	}

	// Generer un random pour le test
	private ArrayList<String> generateRandomInput(List<Chaines> listeChaines) {
		ArrayList<String> randomInput = new ArrayList<>();
		Random random = new Random();

		for (Chaines c : listeChaines) {
			randomInput.add(c.getCodeC());
			// Exemple: nombre Random entre 0 et 6
			randomInput.add(String.valueOf(random.nextInt(0, 6)));
		}

		return randomInput;
	}

}
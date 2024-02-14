package com.example.projetinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCalcul {

	public static void main(String[] args) {

		GestionDonnees gd = new GestionDonnees();

		gd.chargerElements();
		List<Element> listeElements = new ArrayList<>();
		listeElements = gd.getElements();

		gd.chargerChaineProd();
		List<Chaines> listeChaines = new ArrayList<>();
		listeChaines = gd.getChaineProd();

		gd.chargerPrix();
		List<Prix> listePrix = new ArrayList<>();
		listePrix = gd.getPricingData();

		Achat.remplirAchatsFictifs(); // Pour avoir des données au moment du getAchats

		// Activation aléatoire POUR LE TEST
		ArrayList<String> k = new ArrayList<>();
		Random random = new Random();
		for (Chaines c : listeChaines) {
			k.add(c.getCodeC());
			k.add(String.valueOf(random.nextInt(0, 6)));
		}

		Calcul.faireLesCalculs(listePrix, listeElements, listeChaines, k);

		System.out.println("Ind. de valeur : " + Calcul.getIndicateurValeur());
		System.out.println("Ind. de commande : " + Calcul.getIndicateurCommande());

		System.out.println("\nprix :");
		for (Prix p : listePrix) {
			System.out.println("code : " + p.getCodeE() + " prix achat : " + p.getPrixAchat() + " prix vente : " + p.getPrixVente() + " qté commandée :" + p.getQteCommande());
		}

		System.out.println("\nelements");
		for (Element e : listeElements) {
			System.out.println("code : " + e.getCodeE() + " nom : " + e.getNomE() + " quantité : " + e.getQuantite() + " untité :" + e.getUnite());
		}

		System.out.println("\nchaines :");
		for (Chaines c : listeChaines) {
			System.out.println("code : " + c.getCodeC() + " nom : " + c.getNomC() + " entree : " + c.getHashElementEntre() + " sortie :" + c.getHashElementSortie());
		}

		ExportTexte.exporter(k);

		//ExportSimulation.exporterSimulation(); // Export PDF

	}
}

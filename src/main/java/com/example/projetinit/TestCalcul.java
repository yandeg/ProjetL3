package com.example.projetinit;

public class TestCalcul {

	public static void main(String[] args) {
		
		Calcul calcul = new Calcul();
		
		System.out.println(calcul.initialiserMapStockFictif());
		System.out.println(calcul.calculerIndicateurValeur()); //0.0 car méthode incomplète
		System.out.println(calcul.getMapStockFictif());
		System.out.println(calcul.calculerIndicateurCommande());
		
		
   }
}

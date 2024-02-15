/**
 * Cette classe représente les prix associés à un élément.
 * Chaque prix est caractérisé par un code d'élément, un prix d'achat, un prix de vente et une quantité commandée.
 */
package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Prix {
	private SimpleStringProperty codeE; // Le code de l'élément
	private SimpleDoubleProperty prixAchat; // Le prix d'achat de l'élément
	private SimpleDoubleProperty prixVente; // Le prix de vente de l'élément
	private SimpleDoubleProperty qteCommande; // La quantité commandée de l'élément

	/**
	 * Constructeur de la classe Prix.
	 * @param elementCode Le code de l'élément.
	 * @param purchasePrice Le prix d'achat de l'élément.
	 * @param sellingPrice Le prix de vente de l'élément.
	 * @param orderedQuantity La quantité commandée de l'élément.
	 */
	public Prix(String elementCode, double purchasePrice, double sellingPrice, double orderedQuantity) {
		this.codeE = new SimpleStringProperty(elementCode);
		this.prixAchat = new SimpleDoubleProperty(purchasePrice);
		this.prixVente = new SimpleDoubleProperty(sellingPrice);
		this.qteCommande = new SimpleDoubleProperty(orderedQuantity);
	}

	/**
	 * Getter pour le code de l'élément.
	 * @return Le code de l'élément.
	 */
	public String getCodeE() {
		return codeE.get();
	}

	/**
	 * Setter pour le code de l'élément.
	 * @param elementCode Le code de l'élément à définir.
	 */
	public void setCodeE(String elementCode) {
		this.codeE.set(elementCode);
	}

	/**
	 * Getter pour le prix d'achat de l'élément.
	 * @return Le prix d'achat de l'élément.
	 */
	public double getPrixAchat() {
		return prixAchat.get();
	}

	/**
	 * Setter pour le prix d'achat de l'élément.
	 * @param purchasePrice Le prix d'achat de l'élément à définir.
	 */
	public void setPrixAchat(double purchasePrice) {
		this.prixAchat.set(purchasePrice);
	}

	/**
	 * Getter pour le prix de vente de l'élément.
	 * @return Le prix de vente de l'élément.
	 */
	public double getPrixVente() {
		return prixVente.get();
	}

	/**
	 * Setter pour le prix de vente de l'élément.
	 * @param sellingPrice Le prix de vente de l'élément à définir.
	 */
	public void setPrixVente(double sellingPrice) {
		this.prixVente.set(sellingPrice);
	}

	/**
	 * Getter pour la quantité commandée de l'élément.
	 * @return La quantité commandée de l'élément.
	 */
	public double getQteCommande() {
		return qteCommande.get();
	}

	/**
	 * Setter pour la quantité commandée de l'élément.
	 * @param orderedQuantity La quantité commandée de l'élément à définir.
	 */
	public void setQteCommande(int orderedQuantity) {
		this.qteCommande.set(orderedQuantity);
	}
}

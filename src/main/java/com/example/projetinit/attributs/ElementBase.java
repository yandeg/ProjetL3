/**
 * Cette classe est une classe de base pour représenter un élément avec un code et une quantité.
 */
package com.example.projetinit.attributs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ElementBase {
	private SimpleStringProperty codeE;
	private SimpleDoubleProperty quantite;

	/**
	 * Constructeur de la classe ElementBase.
	 * @param code Le code de l'élément.
	 * @param quantity La quantité de l'élément.
	 */
	public ElementBase(String code, double quantity) {
		this.codeE = new SimpleStringProperty(code);
		this.quantite = new SimpleDoubleProperty(quantity);
	}

	/**
	 * Obtient le code de l'élément.
	 * @return Le code de l'élément.
	 */
	public String getCodeE() {
		return codeE.get();
	}

	/**
	 * Définit le code de l'élément.
	 * @param code Le nouveau code de l'élément.
	 */
	public void setCodeE(String code) {
		this.codeE.set(code);
	}

	/**
	 * Obtient la quantité de l'élément.
	 * @return La quantité de l'élément.
	 */
	public double getQuantite() {
		return quantite.get();
	}

	/**
	 * Définit la quantité de l'élément.
	 * @param quantity La nouvelle quantité de l'élément.
	 */
	public void setQuantite(double quantity) {
		this.quantite.set(quantity);
	}

	/**
	 * Ajoute une quantité à la quantité actuelle de l'élément.
	 * @param n La quantité à ajouter.
	 */
	public void ajouterStockE(double n) {
		quantite.set(quantite.get() + n);
	}

	/**
	 * Réduit la quantité actuelle de l'élément.
	 * @param n La quantité à réduire.
	 */
	public void reduireStockE(double n) {
		quantite.set(quantite.get() - n);
	}
}

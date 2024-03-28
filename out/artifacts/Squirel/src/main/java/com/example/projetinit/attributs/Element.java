/**
 * Cette classe représente un élément de la liste des éléments.
 * Elle étend la classe ElementBase.
 */

package com.example.projetinit.attributs;

import javafx.beans.property.SimpleStringProperty;

public class Element extends ElementBase {
    private SimpleStringProperty nomE;
    private SimpleStringProperty unite;

	/**
	 * Constructeur de la classe Element.
	 * @param code Le code de l'élément.
	 * @param name Le nom de l'élément.
	 * @param quantity La quantité de l'élément.
	 * @param unit L'unité de l'élément.
	 */
    public Element(String code, String name, double quantity, String unit){
    	super(code,quantity);
        this.nomE = new SimpleStringProperty(name);
        this.unite = new SimpleStringProperty(unit);
    }

	/**
	 * Obtient le nom de l'élément.
	 * @return Le nom de l'élément.
	 */
	public String getNomE() {
		return nomE.get();
	}

	/**
	 * Obtient l'unité de l'élément.
	 * @return L'unité de l'élément.
	 */
	public String getUnite() {
		return unite.get();
	}

}

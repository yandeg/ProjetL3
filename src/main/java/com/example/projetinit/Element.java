package com.example.projetinit;

import javafx.beans.property.SimpleStringProperty;

public class Element extends ElementBase{
    private SimpleStringProperty nomE;
    private SimpleStringProperty unite;

    public Element(String code, String name, double quantity, String unit){
    	super(code,quantity);
        this.nomE = new SimpleStringProperty(name);
        this.unite = new SimpleStringProperty(unit);
    }

	public String getNomE() {
		return nomE.get();
	}

	public void setNomE(String name) {
		this.nomE.set(name);
	}

	public String getUnite() {
		return unite.get();
	}

	public void setUnite(String unit) {
		this.unite.set(unit);
	}

}

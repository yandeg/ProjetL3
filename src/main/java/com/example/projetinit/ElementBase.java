package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ElementBase {
	    private SimpleStringProperty codeE;
	    private SimpleDoubleProperty quantite;

	    public ElementBase(String code, double quantity) {
	        this.codeE = new SimpleStringProperty(code);
	        this.quantite = new SimpleDoubleProperty(quantity);
	    }

		public String getCodeE() {
			return codeE.get();
		}

		public void setCodeE(String code) {this.codeE.set(code); }

		public double getQuantite() {
			return quantite.get();
		}

		public void setQuantite(double quantity) {
			this.quantite.set(quantity);
		}

	    public void ajouterStockE(double n){
			quantite.set(quantite.get()+n);
	    }
	    public void reduireStockE(double n){
			quantite.set(quantite.get()-n);
	    }


	}



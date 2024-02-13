package com.example.projetinit;

public class ElementBase {
	    private String codeE;
	    private double quantite;

	    public ElementBase(String code, double quantity) {
	        this.codeE = code;
	        this.quantite = quantity;
	    }

		public String getCodeE() {
			return codeE;
		}

		public void setCodeE(String code) {this.codeE = code; }

		public double getQuantite() {
			return quantite;
		}

		public void setQuantite(double quantity) {
			this.quantite = quantity;
		}

	    public void ajouterStockE(double n){
	    	this.quantite+=n;
	    }
	    public void reduireStockE(double n){
	    	this.quantite-=n;
	    }


	}



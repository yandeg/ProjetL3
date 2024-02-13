package com.example.projetinit;

public class Prix {
    private String codeE;
    private double prixAchat;
    private double prixVente;
    private double qteCommande;

    public Prix(String elementCode, double purchasePrice, double sellingPrice, double orderedQuantity) {
        this.codeE = elementCode;
        this.prixAchat = purchasePrice;
        this.prixVente = sellingPrice;
        this.qteCommande = orderedQuantity;
    }

	public String getCodeE() {
		return codeE;
	}

	public void setCodeE(String elementCode) {
		this.codeE = elementCode;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double purchasePrice) {
		this.prixAchat = purchasePrice;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double sellingPrice) {
		this.prixVente = sellingPrice;
	}

	public double getQteCommande() {
		return qteCommande;
	}

	public void setQteCommande(int orderedQuantity) {
		this.qteCommande = orderedQuantity;
	}


}

package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Prix {
    private SimpleStringProperty codeE;
    private SimpleDoubleProperty prixAchat;
    private SimpleDoubleProperty prixVente;
    private SimpleDoubleProperty qteCommande;

    public Prix(String elementCode, double purchasePrice, double sellingPrice, double orderedQuantity) {
        this.codeE = new SimpleStringProperty(elementCode);
        this.prixAchat = new SimpleDoubleProperty(purchasePrice);
        this.prixVente = new SimpleDoubleProperty(sellingPrice);
        this.qteCommande = new SimpleDoubleProperty(orderedQuantity);
    }

	public String getCodeE() {
		return codeE.get();
	}

	public void setCodeE(String elementCode) {
		this.codeE.set(elementCode);
	}

	public double getPrixAchat() {
		return prixAchat.get();
	}

	public void setPrixAchat(double purchasePrice) {
		this.prixAchat.set(purchasePrice);
	}

	public double getPrixVente() {
		return prixVente.get();
	}

	public void setPrixVente(double sellingPrice) {
		this.prixVente.set(sellingPrice);
	}

	public double getQteCommande() {
		return qteCommande.get();
	}

	public void setQteCommande(int orderedQuantity) {
		this.qteCommande.set(orderedQuantity);
	}


}

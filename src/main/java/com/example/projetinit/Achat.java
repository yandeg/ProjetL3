package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Achat {

    private SimpleStringProperty codeE;
    private SimpleDoubleProperty qteAchat;

    public Achat(String codeE, double qteAchat) {
        this.codeE = new SimpleStringProperty(codeE);
        this.qteAchat = new SimpleDoubleProperty(qteAchat);
    }

    public String getCodeE() {
        return codeE.get();
    }

    public double getQteAchat() {
        return qteAchat.get();
    }

    public void setCodeE(String codeE) {
        this.codeE.set(codeE);
    }

    public void setQteAchat(double qteAchat) {
        this.qteAchat.set(qteAchat);
    }
}
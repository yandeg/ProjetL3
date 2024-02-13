package com.example.projetinit;

public class Element extends ElementBase{
    private String nomE;
    private String unite;

    public Element(String code, String name, double quantity, String unit){
    	super(code,quantity);
        this.nomE = name;
        this.unite = unit;
    }

	public String getNomE() {
		return nomE;
	}

	public void setNomE(String name) {
		this.nomE = name;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unit) {
		this.unite = unit;
	}

}

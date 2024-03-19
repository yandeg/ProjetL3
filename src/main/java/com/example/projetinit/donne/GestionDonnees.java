package com.example.projetinit.donne;

import com.example.projetinit.Affichage;
import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class GestionDonnees {
    private List<Element> elements;
    private List<Chaines> chaines;
    private List<Prix> prix;
    Affichage afficher = new Affichage();

    public GestionDonnees() {
        this.elements = new ArrayList<>();
        this.chaines = new ArrayList<>();
        this.prix = new ArrayList<>();

    }

    public void chargerElements() {
        try (InputStream is = getClass().getResourceAsStream("/files/Elements.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Element element = new Element(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                elements.add(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerChaineProd() {
        try (InputStream is = getClass().getResourceAsStream("/files/Chaines.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chaines.add(convertirLigneEnChaine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Chaines convertirLigneEnChaine(String ligne) {
        String[] data = ligne.split(";");
        HashMap<String,Double> listeElementEntre = convertListeElement(data[2]);
        HashMap<String,Double> listeElementSortie = convertListeElement(data[3]);
        Chaines chaines= new Chaines(data[0],data[1], listeElementEntre, listeElementSortie);

        return chaines;
    }

    public static HashMap<String,Double> convertListeElement(String data){
        String[] data1 = data.split("/");
        HashMap<String,Double> map= new HashMap<>();
        for (int i=0;i<data1.length;i++) {

            String[] data2 = data1[i].split(",");
            map.put(data2[0],Double.parseDouble(data2[1]));
        }
        return map;

    }

    public void chargerPrix() {
        try ( InputStream is = getClass().getResourceAsStream("/files/Prix.csv");
              BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(";");
                Prix Prix = new Prix(data[0], Double.parseDouble( data[1]), Double.parseDouble(data[2]), Double.parseDouble (data[3]));
                prix.add(Prix);;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Chaines> getChaineProd() {
        return chaines;
    }

    public List<Prix> getPricingData() {
        return prix;
    }

    public String ajouterStock(String codeE, double n){
        String msg="";
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                element.ajouterStockE(n);
                msg = "Vous avez ajouté "+ n + " aux "+codeE;
                return msg;
            }
        }
        msg = "le code suivant n'a pas d'élément associé "+codeE;
        return msg;
    }
    public String reduireStock(String codeE,double n){
        String msg="";
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                element.reduireStockE(n);
                msg = "Vous avez ajouté "+ n + " aux "+codeE;
                return msg;
            }
        }
        msg = "le code suivant n'a pas d'élément associé "+codeE;
        return msg;
    }

}
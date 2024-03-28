package com.example.projetinit.donne;

import com.example.projetinit.Affichage;
import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.HashMap;
import java.util.List;


public class GestionDonnees {

    private static ObservableList<Element> elements = FXCollections.observableArrayList();
    private static ObservableList<Chaines> chaines = FXCollections.observableArrayList();
    private static ObservableList<Prix> prix = FXCollections.observableArrayList();
    Affichage afficher = new Affichage();

    private static SimpleIntegerProperty niveauActivation = new SimpleIntegerProperty(0);

    public GestionDonnees() {


    }

    public static void chargerElements() {
        try (InputStream is = GestionDonnees.class.getResourceAsStream("/files/Elements.csv");
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

    public static void chargerChaineProd() {
        try (InputStream is = GestionDonnees.class.getResourceAsStream("/files/user/Chaines.csv");
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
        Chaines chaines1= new Chaines(data[0],data[1], listeElementEntre, listeElementSortie);

        return chaines1;
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

    public static void chargerPrix() {
        try (InputStream is = GestionDonnees.class.getResourceAsStream("/files/user/Prix.csv");
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

    public static List<Element> getElements() {
        return elements;
    }

    public static List<Chaines> getChaineProd() {
        return chaines;
    }

    public static List<Prix> getPricingData() {
        return prix;
    }



    public static String ajouterStock(String codeE, double n){
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
    public static boolean verifierCodeE(String codeE){
        for (Element element : elements) {
            if (element.getCodeE().equals(codeE)) {
                return true;
            }
        }
        return false;

    }

    public static void ajouterNiveauActivation(String codeC){
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.ajouterNiveauActivation();
            }
        });
    }
    public static void enleverNiveauActivation(String codeC) {
        chaines.forEach(chaine -> {
            if (chaine.getCodeC().equals(codeC)) {
                chaine.enleverNiveauActivation();
            }
        });
    }
    public static int getNiveauActivation(String codeC) {
        for (Chaines chaine : chaines) {
            if (chaine.getCodeC().equals(codeC)) {
                return chaine.getNiveauActivationC();
            }
        }
        return 0;
    }
 public static void setElements (ObservableList<Element> element){
        elements = element;

 }
    public static void setChaines (ObservableList<Chaines> chaine){
            chaines = chaine;

    }
    public static void setPrix (ObservableList<Prix> pri){
        prix = pri;

    }

}
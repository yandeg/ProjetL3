package com.example.projetinit;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExportTexte {

    // Pour tester directement (a suppr)
    public static void main(String[] args) {
        //exporter();
    }

    public static void exporter(ArrayList<String> k) {
        // CREATE
        try {
            File myObj = new File("Simulation.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            // WRITE
            FileWriter myWriter = new FileWriter("Simulation.txt");
            // titre
            myWriter.write("Simulation faite le " +
                    new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            myWriter.write("\n\n\nDONNEES SAISIES : ");
            // achats
            //Achat.remplirAchatsFictifs(); //A suppr
            myWriter.write("\n\nCodeE | Quantité achat");
            myWriter.write("\n----------------------");
            for (Achat a : Achat.getAchats()) {
                myWriter.write("\n " + a.getCodeE() + " | " + a.getQteAchat());
            }
            // activation
            myWriter.write("\n\nCodeC | Niveau activation");
            myWriter.write("\n-------------------------");
            for (int i=0;i<k.size();i+=2) {
                myWriter.write("\n " + k.get(i) + " | " + k.get(i+1));
            }
            // resultats
            myWriter.write("\n\n\nRESULTATS : ");
            myWriter.write("\nIndicateur de valeur : " + Calcul.getIndicateurValeur() + " €");
            myWriter.write("\nIndicateur de commande : " + Calcul.getIndicateurCommande()*100 + " %");
            myWriter.write(Calcul.getMessageProdImpossible());

            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
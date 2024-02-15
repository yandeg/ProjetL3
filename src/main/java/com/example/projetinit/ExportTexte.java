/**
 * Cette classe fournit une méthode statique pour exporter les données de simulation vers un fichier texte.
 * Les données comprennent les achats saisis, l'activation des chaînes et les résultats de la simulation.
 */
package com.example.projetinit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportTexte {

    /**
     * Exporte les données de simulation vers un fichier texte.
     * @param infosActivation La liste des chaines et leur niveau d'activation.
     * @param exportDirectory Le répertoire d'export.
     */
    public static void exporter(List<String> infosActivation, String exportDirectory) {
        try {
            // Choix de l'emplacement d'exportation du fichier texte
            File myObj = new File(exportDirectory + "/Simulation.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write("Simulation faite le " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            myWriter.write("\n\n\nDONNEES SAISIES : ");

            // Achats
            // Achat.remplirAchatsFictifs(); // A suppr
            myWriter.write("\n\nCodeE | Quantité achat");
            myWriter.write("\n----------------------");
            for (Achat a : Achat.getAchats()) {
                myWriter.write("\n " + a.getCodeE() + " | " + a.getQteAchat());
            }
            // Activation
            myWriter.write("\n\nCodeC | Niveau activation");
            myWriter.write("\n-------------------------");
            for (int i = 0; i + 1 < infosActivation.size(); i += 2) {
                myWriter.write("\n " + infosActivation.get(i) + " | " + infosActivation.get(i + 1));

                System.out.println(infosActivation.get(i) + " | " + infosActivation.get(i + 1));
            }

            // Résultats
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
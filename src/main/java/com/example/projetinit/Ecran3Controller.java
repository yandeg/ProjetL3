/**
 * Cette classe contrôle les fonctionnalités de la troisième fenêtre (Ecran3) de l'application.
 * Elle gère les actions d'événements, telles que le passage à l'écran précédent et l'exportation des données.
 */

package com.example.projetinit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Ecran3Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label labelResultats;

    private List<String> results;


    /**
     * Méthode appelée lorsqu'on souhaite passer à l'écran précédent (Ecran2).
     * Elle charge la vue de l'écran précédent et la présente à l'utilisateur.
     * @param e L'événement de clic sur le bouton pour passer à l'écran précédent.
     * @throws IOException Si une erreur survient lors du chargement de la vue de l'écran précédent.
     */
    public void swicthToEcran2(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran2.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Récupère les résultats des calculs effectués dans la classe "TestCalcul".
     * @return Une liste contenant les résultats des calculs, ou une liste vide si aucun résultat n'est disponible.
     */
    private List<String> calculateResults() {
        TestCalcul testCalcul = new TestCalcul();
        results = testCalcul.obtenirResultats();
        return results != null ? results : List.of(); // Ensure the method doesn't return null
    }


    /**
     * Met à jour les données à afficher à partir des résultats des calculs.
     */
    private void setDataFromTestCalcul() {
        List<String> results = calculateResults();
        if (!results.isEmpty()) {
            // Crée une sublist avec les deux "First Strings"
            //On veux récuperer que les 2 indicateurs : indicateur de valeur et indicateur de commande
            List<String> firstTwoResults = results.subList(0, Math.min(results.size(), 2));

            // M-à-j du labelRésultats avec les 2 indicateurs
            StringBuilder sb = new StringBuilder();
            for (String result : firstTwoResults) {
                sb.append(result).append("\n");
            }
            labelResultats.setText(sb.toString());
        }
    }

    /**
     * Méthode appelée lors de l'initialisation de la fenêtre.
     * Charge les données à afficher à partir des résultats des calculs.
     * @param url L'URL de la ressource racine à charger.
     * @param resourceBundle Le ResourceBundle contenant les ressources spécifiques à la locale.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pour afficher les 2 indicateurs
        setDataFromTestCalcul();
    }
    /**
     * Méthode appelée lorsqu'on souhaite exporter les données vers un fichier texte.
     * Elle permet à l'utilisateur de choisir le dossier de destination pour l'exportation.
     * @param event L'événement déclenché lors du clic sur le bouton d'exportation.
     */
    public void exportToText(ActionEvent event) {
        // Choix du dossier d'exportation
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Export Location");
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Exportation en format texte
            ExportTexte.exporter((ArrayList<String>) results, selectedDirectory.getAbsolutePath());

            System.out.println("Data exported successfully to: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No directory selected.");
        }
    }

    /**
     * Méthode appelée lorsqu'on souhaite exporter les données vers un fichier PDF.
     * Elle permet à l'utilisateur de choisir le dossier de destination pour l'exportation.
     * @param event L'événement déclenché lors du clic sur le bouton d'exportation.
     */
    public void exportToPDF(ActionEvent event) {
        // Choix du dossier d'exportation
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Export Location");
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Exportation en format PDF
            ExportSimulation.exporterSimulation(results, selectedDirectory.getAbsolutePath());

            System.out.println("Data exported successfully to: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No directory selected.");
        }
    }

}


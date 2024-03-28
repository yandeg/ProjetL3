/**
 * Cette classe contrôle les fonctionnalités de la troisième fenêtre (Ecran3) de l'application.
 * Elle gère les actions d'événements, telles que le passage à l'écran précédent et l'exportation des données.
 */

package com.example.projetinit.Ecran;

import com.example.projetinit.export.ExportSimulation;
import com.example.projetinit.export.ExportTexte;
import com.example.projetinit.donne.TestCalcul;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;

public class Ecran3Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label labelResultats;

    private List<String> results;

    private static List<List<String>> historiques = new ArrayList<>();


    /**
     * Méthode appelée lorsqu'on souhaite passer à l'écran précédent (Ecran2).
     * Elle charge la vue de l'écran précédent et la présente à l'utilisateur.
     * @param e L'événement de clic sur le bouton pour passer à l'écran précédent.
     * @throws IOException Si une erreur survient lors du chargement de la vue de l'écran précédent.
     */
    public void switchToEcran2(ActionEvent e) throws IOException {
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
        //list des historiques



    }
    /**
     * Méthode appelée lorsqu'on souhaite exporter les données vers un fichier texte.
     * Elle permet à l'utilisateur de choisir le dossier de destination pour l'exportation.
     * @param event L'événement déclenché lors du clic sur le bouton d'exportation.
     */
    public void exportToText(ActionEvent event) {
        try {
            // Créer un FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier TXT", "*.txt"));
            // Afficher la boîte de dialogue de sélection de fichier pour enregistrer le fichier TXT
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                // Exporter les données au fichier TXT sélectionné
                ExportTexte.exporter(file.getAbsolutePath());
                System.out.println("Fichier exporté avec succès : " + file.getAbsolutePath());
            } else {
                System.out.println("Aucun fichier sélectionné.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'on souhaite exporter les données vers un fichier PDF.
     * Elle permet à l'utilisateur de choisir le dossier de destination pour l'exportation.
     * @param event L'événement déclenché lors du clic sur le bouton d'exportation.
     */
    public void exportToPDF(ActionEvent event) {
        try {
            // Créer un FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier PDF", "*.pdf"));

            // Afficher la boîte de dialogue de sélection de fichier pour enregistrer le fichier PDF
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                // Exporter les données au fichier PDF sélectionné
                ExportSimulation.exporterSimulation( file.getAbsolutePath());
                System.out.println("Fichier exporté avec succès : " + file.getAbsolutePath());
            } else {
                System.out.println("Aucun fichier sélectionné.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addToHistorique(ActionEvent event) {
        if (results != null) {
            historiques.add(new ArrayList<>(results));
            showAlert(Alert.AlertType.INFORMATION, "Ajout à l'historique", "Résultats ajoutés à l'historique.");
        }
    }

    public void voirHistorique(ActionEvent event) {
        if (!historiques.isEmpty()) {
            StringBuilder historiqueContent = new StringBuilder();
            for (int i = 0; i < historiques.size(); i++) {
                historiqueContent.append("Historique ").append(i + 1).append(":\n");
                List<String> historique = historiques.get(i);
                for (String result : historique) {
                    historiqueContent.append(result).append("\n");
                }
                historiqueContent.append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Historique des résultats", historiqueContent.toString());
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Historique des résultats", "Aucun historique disponible.");
        }
    }



    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(new Label(content));

        alert.getDialogPane().setContent(scrollPane);

        alert.showAndWait();
    }



}


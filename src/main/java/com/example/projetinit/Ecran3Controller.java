package com.example.projetinit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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



    public void swicthToEcran2(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran2.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //récuperer les results des calculs fait dans la classe "TestCalcul"
    private List<String> calculateResults() {
        TestCalcul testCalcul = new TestCalcul();
        results = testCalcul.obtenirResultats();
        return results != null ? results : List.of(); // Ensure the method doesn't return null
    }


    private void setDataFromTestCalcul() {
        List<String> results = calculateResults();
        if (!results.isEmpty()) {
            // Crée une sublist avec les deux premiere String
            //On veux recuperer que les 2 indicateurs
            List<String> firstTwoResults = results.subList(0, Math.min(results.size(), 2));

            // Update des labels avec les indicateurs de valeurs
            StringBuilder sb = new StringBuilder();
            for (String result : firstTwoResults) {
                sb.append(result).append("\n");
            }
            labelResultats.setText(sb.toString());
        }
    }



    // Pour afficher les 2 indicateurs
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataFromTestCalcul();
    }

    public void exportData(ActionEvent event) {
        //choisir le dossier d'export
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Export Location");
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Exporter en PDF
            ExportSimulation.exporterSimulation(results, selectedDirectory.getAbsolutePath());

            // Exporter en Text
            ExportTexte.exporter((ArrayList<String>) results, selectedDirectory.getAbsolutePath());

            System.out.println("Data exported successfully to: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No directory selected.");
        }
    }

}


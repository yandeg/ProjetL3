package com.example.projetinit.Ecran;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EcranSessionController {

    @FXML
    private TextField nomDossierTextField;
    private boolean exists;

    private void checkExists() {
        if (nomDossierTextField != null) {
            String nomDossier = nomDossierTextField.getText();
            Path cheminDossier = Paths.get("src\\main\\resources\\files\\", nomDossier);
            exists = Files.exists(cheminDossier) && Files.isDirectory(cheminDossier);
        }

    }

    @FXML
    public void switchToEcran0(ActionEvent e) throws IOException {
        // Vérifier si le TextField est initialisé
        checkExists();
        if (exists) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Le répertoire n'existe pas ou n'est pas un répertoire valide");
            afficherMessageErreur("Erreur", "Le répertoire n'existe pas ou n'est pas un répertoire valide.");
        }
    }

    // Méthode pour afficher un message d'erreur
    private void afficherMessageErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
package com.example.projetinit.Ecran;

import com.example.projetinit.donne.GestionSession;
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

public class EcranSessionController {

    private GestionSession sessionManager; // Assurez-vous d'avoir une instance de SessionManager dans votre contrôleur

    @FXML
    private TextField identificateur;

    // Méthode d'injection de dépendance pour injecter la dépendance SessionManager
    public void setSessionManager(GestionSession sessionManager) {
        this.sessionManager = sessionManager;
    }

    @FXML
    public void switchToEcran0(ActionEvent e) throws IOException {
        String username = identificateur.getText(); // Récupère le nom d'utilisateur saisi

        if (sessionManager.getSession(username) != null) {
            // L'utilisateur est déjà enregistré, permettez-lui d'accéder à l'écran 0
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // L'utilisateur n'est pas enregistré, afficher un message d'erreur ou effectuer une action appropriée
            System.out.println("Utilisateur non enregistré : " + username);
            afficherMessageErreur("Utilisateur non enregistré", "Veuillez vous inscrire d'abord.");
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


package com.example.projetinit.Ecran;

import com.example.projetinit.donne.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EcranSessionController {

    public TextField identificateur;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToEcran0(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private List<Session> sessions;

    public void initialize() {
        try {
            sessions = Session.CSVReader.readSessionsFromCSV("sessions.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connexion(ActionEvent e) {
        String identifiant = identificateur.getText();

        boolean authentification = false;
        for (Session session : sessions) {
            if (session.getIdentifiant().equals(identifiant)) {
                authentification = true;
                break;
            }

        }
        if (authentification) {
            try {
                switchToEcran0(e);
            } catch (IOException ex) {
                ex.printStackTrace();}
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Un tel identifiant n'existe pas");
            alert.showAndWait();
        }

    }
}


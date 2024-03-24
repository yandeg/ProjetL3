package com.example.projetinit.Ecran;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EcranSessionController {

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
}

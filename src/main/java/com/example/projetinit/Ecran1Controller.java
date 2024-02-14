package com.example.projetinit;

import com.example.projetinit.utils.ApplicationUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ecran1Controller implements Initializable {

    private int niveauChaine=0;
    @FXML
    private Label statusLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateStatusLabel(niveauChaine);
    }
    @FXML
    protected void handleAddLvlAction(ActionEvent e) {
        updateStatusLabel(++niveauChaine);
    }

    @FXML
    protected void handleRemoveLvlAction(ActionEvent e) {
        if (niveauChaine>0){
            updateStatusLabel(--niveauChaine);
        }
    }

    private void updateStatusLabel(int niveauChaine) {
        statusLabel.setText("Le niveau d'activation : " + ApplicationUtils.pluralize(niveauChaine,"chaine ", "chaines ") + "de production");
    }

    public void swicthToEcran0(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void swicthToEcran2 (ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran2.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

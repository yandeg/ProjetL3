package com.example.projetinit.Ecran;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.utils.ApplicationUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.projetinit.donne.GestionDonnees.*;

public class Ecran1Controller implements Initializable {

    @FXML
    private Label statusLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ObservableList<Chaines> chaines = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateStatusLabel();
    }
    @FXML
    protected void handleAddLvlAction(ActionEvent e, Chaines chaine) {

        ajouterNiveauActivation("C001");
        updateStatusLabel();
    }

    @FXML
    protected void handleRemoveLvlAction(ActionEvent e) {

        enleverNiveauActivation("C001");
        updateStatusLabel();
    }

    private void updateStatusLabel() {
        statusLabel.setText("Le niveau d'activation : " + ApplicationUtils.pluralize( getNiveauActivation("C001"),"chaine ", "chaines ") + "de production");
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
    public void swicthToEcranpop(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/projetinit/Ecranpop.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Données actualisées");
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }
}


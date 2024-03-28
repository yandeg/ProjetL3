package com.example.projetinit.Ecran;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.utils.ApplicationUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.projetinit.donne.GestionDonnees.*;

public class Ecran1Controller implements Initializable {


    @FXML
    private TableView<Chaines> tableViewC;
    @FXML
    private TableColumn<Chaines, HashMap<String, Double>> sortie;

    @FXML
    private TableColumn<Chaines, HashMap<String, Double>> entree;

    @FXML
    private TableColumn<Chaines, String> nomC;

    @FXML
    private TableColumn<Chaines, String> codeC;
    @FXML
    private TableColumn<Chaines, String> niveauActivation;

    @FXML
    private Label statusLabel;

    //cellule du + et - du niveau de l'activation
    @FXML
    private TableColumn<Chaines, Void> sortie1;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ObservableList<Chaines> chaines;
    Map<String, Integer> comptesChaine = new HashMap<>();

    /**
     * Méthode appelée lors de l'initialisation du contrôleur.
     *
     * @param url Le chemin d'accès au fichier FXML.
     * @param resourceBundle Les ressources utilisées par le contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTableView(); //Reprendre les updates de la table de l'ecran 0

        sortie1.setCellFactory(param -> new ModifCell());
    }
    /**
     * Initialise la TableView avec les données.
     */
    private void initTableView() {
        Ecran0Controller ecran0Controller = Ecran0Controller.getInstance(); // Assuming you have a method to access Ecran0Controller instance
        chaines = (ObservableList<Chaines>) getChaineProd();

        codeC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeC()));
        nomC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomC()));
        entree.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementEntre()));
        sortie.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementSortie()));
         //niveauActivation.setCellFactory(column -> new ModifCell());
       // enleverNiveauActivation("C001");

       // ajouterNiveauActivation("C001");
        tableViewC.setItems(chaines);

    }

    /**
     * Gère l'action de suppression du niveau d'activation.
     *
     * @param e L'événement associé à l'action.
     */
    @FXML
    protected void handleRemoveLvlAction(ActionEvent e) {

        enleverNiveauActivation("C001");
        updateStatusLabel();
    }

    //ça sert à mettre la phrase au plural dans une version ancienne (by Yanis...il insiste meme si c'est pas nécessaire)
    /**
     * Met à jour le label de statut.
     */
    private void updateStatusLabel() {
        statusLabel.setText("Le niveau d'activation : " + ApplicationUtils.pluralize( getNiveauActivation("C001"),"chaine ", "chaines ") + "de production");
    }

    /**
     * Gère l'action de commutation vers l'écran 0.
     *
     * @param e L'événement associé à l'action.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement de l'écran 0.
     */
    public void switchToEcran0(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gère l'action de commutation vers l'écran 2.
     *
     * @param e L'événement associé à l'action.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement de l'écran 2.
     */
    public void switchToEcran2 (ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran2.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gère l'action de commutation vers l'écran pop-up.
     *
     * @param e L'événement associé à l'action.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement de l'écran pop-up.
     */
    public void switchToEcranpop(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/projetinit/Ecranpop.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Données actualisées");
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
    }

    /**
     * Gère l'action d'ajout du niveau d'activation.
     *
     * @param actionEvent L'événement associé à l'action.
     */
    @FXML
    protected void handleAddLvlAction(ActionEvent actionEvent) {
        ajouterNiveauActivation("C001");
        int nouveauCompte = comptesChaine.getOrDefault(codeC, 0) + 1;
        comptesChaine.put(String.valueOf(codeC), nouveauCompte);
        updateStatusLabel();
    }


}



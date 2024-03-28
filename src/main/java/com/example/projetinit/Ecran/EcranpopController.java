package com.example.projetinit.Ecran;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashMap;

import static com.example.projetinit.donne.GestionDonnees.*;

public class EcranpopController {

    @FXML
    private TableColumn<Chaines, HashMap<String, Double>> sortie;

    @FXML
    private TableColumn<Chaines, String> niveauActivation;

    @FXML
    private TableColumn<Chaines, HashMap<String, Double>> entree;

    @FXML
    private TableView<Chaines> tableView2;

    @FXML
    private TableColumn<Chaines, String> nomC;

    @FXML
    private TableColumn<Chaines, String> codeC;

    @FXML
    private TableColumn<Prix, Double> commande;

    @FXML
    private TableColumn<Prix, Double> prixVente;

    @FXML
    private TableColumn<Prix, Double> prixAchat;

    @FXML
    private TableView<Prix> tableView1;

    @FXML
    private TableColumn<Prix, String> codeE1;

    @FXML
    private TableView<Element> tableView;

    @FXML
    private TableColumn<Element, String> codeE;

    @FXML
    private TableColumn<Element, String> nomE;

    @FXML
    private TableColumn<Element, Double> quantite;

    @FXML
    private TableColumn<Element, String> unite;
    private ObservableList<Element> elements = FXCollections.observableArrayList();
    private ObservableList<Prix> prix = FXCollections.observableArrayList();
    private ObservableList<Chaines> chaines = FXCollections.observableArrayList();

    /**
     * Initialise le contrôleur.
     */
    public void initialize() {
        codeE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
        nomE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomE()));
        quantite.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantite()).asObject());
        unite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnite()));
        initialize1();
        initialize2();
        elements = getElements();
        tableView.setItems(elements);
    }


    /**
     * Initialise la première TableView.
     */
    public void initialize1() {
        codeE1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
        prixAchat.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixAchat()).asObject());
        prixVente.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixVente()).asObject());
        commande.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQteCommande()).asObject());
        prix = getPricingData();
        tableView1.setItems(prix);
    }

    /**
     * Initialise la deuxième TableView.
     */
    public void initialize2() {
        codeC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeC()));
        nomC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomC()));
        entree.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementEntre()));
        sortie.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementSortie()));
        niveauActivation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNiveauActivation()));
        chaines = getChaineProd();
        tableView2.setItems(chaines);
    }

    /**
     * Analyse la chaîne de caractères donnée pour créer une HashMap.
     *
     * @param input La chaîne de caractères à analyser.
     * @return Une HashMap créée à partir de la chaîne de caractères.
     */
    private HashMap<String, Double> parseHashMap(String input) {
        HashMap<String, Double> hashMap = new HashMap<>();
        String[] pairs = input.split("/");
        for (String pair : pairs) {
            String[] keyValue = pair.split(",");
            if (keyValue.length == 2) {
                hashMap.put(keyValue[0], Double.parseDouble(keyValue[1]));
            }
        }
        return hashMap;
    }
}

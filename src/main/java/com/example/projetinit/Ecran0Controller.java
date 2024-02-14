package com.example.projetinit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.*;

public class Ecran0Controller {
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void swicthToEcran1(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() {
        codeE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
        nomE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomE()));
        quantite.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantite()).asObject());
        unite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnite()));

        // Charger les données CSV dans la TableView
        try {
            loadCSVData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableView.setItems(elements);
    }
    private void loadCSVData() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/files/Elements.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                elements.add(new Element(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
            }
        }
    }

}


package com.example.projetinit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ecran2Controller implements Initializable {

    @FXML
    private TableView<Achat> tableview;
    @FXML
    private TableColumn<Achat, String> colCodeE;
    @FXML
    private TableColumn<Achat, Double> colQteAchat;
    @FXML
    private TextField textFieldCodeE;
    @FXML
    private TextField textFieldqteAchat;

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

    public void swicthToEcran3(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran3.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addButton(ActionEvent e) {
        Achat achat = new Achat(textFieldCodeE.getText(), Double.parseDouble(textFieldqteAchat.getText()));
        tableview.getItems().add(achat);
    }
    @FXML
    void removeButton(ActionEvent e){
        ObservableList<Achat> allAchat, singleAchat;
        allAchat= tableview.getItems();
        singleAchat= tableview.getSelectionModel().getSelectedItems();
        singleAchat.forEach(allAchat::remove);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCodeE.setCellValueFactory(new PropertyValueFactory<>("codeE"));
        colQteAchat.setCellValueFactory(new PropertyValueFactory<>("qteAchat"));
        ObservableList<Achat> observableList = FXCollections.observableArrayList(
                new Achat("E001", 10.0),
                new Achat("E002", 400.0),
                new Achat("E003", 5.0),
                new Achat("E004", 60.0),
                new Achat("E005", 25.0),
                new Achat("E006", 12.5),
                new Achat("E007", 50.0)
        );
        tableview.setItems(observableList);
    }
}
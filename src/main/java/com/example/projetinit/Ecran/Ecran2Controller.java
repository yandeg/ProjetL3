package com.example.projetinit.Ecran;

import com.example.projetinit.donne.Achat;
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

import static com.example.projetinit.donne.Achat.*;

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
    private static ObservableList<Achat> observableListAchat;
    public void switchToEcran1(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEcran3(ActionEvent e) throws IOException {
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
    void modifyButton(ActionEvent e) {
        ObservableList<Achat> singleAchat;
        Achat achat = new Achat(textFieldCodeE.getText(), Double.parseDouble(textFieldqteAchat.getText()));
        singleAchat= tableview.getSelectionModel().getSelectedItems();
        if (compareCodeA(achat.getCodeE(), singleAchat.getFirst().getCodeE())){
            modifieEnAchat(singleAchat.getFirst(),achat);
        }

        tableview.refresh();


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
        observableListAchat= FXCollections.observableArrayList(
            initialisationAchats()
        );
        tableview.setItems(getAchats());
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textFieldCodeE.setText(newSelection.getCodeE());
                textFieldqteAchat.setText(String.valueOf(newSelection.getQteAchat()));

        };
    });
    }


    public Achat selecteditemToAchat(){
        return tableview.getSelectionModel().getSelectedItem();
    }
}
package com.example.projetinit.Ecran;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.attributs.Element;
import com.example.projetinit.attributs.Prix;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import static com.example.projetinit.donne.GestionDonnees.*;

public class Ecran0Controller {

    @FXML
    private TableColumn<Chaines, HashMap<String, Double>> sortie;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToEcran1(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        chargerElements();
        chargerChaineProd();
        chargerPrix();
        codeE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
        nomE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomE()));
        quantite.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantite()).asObject());
        unite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnite()));

        initialize1();
        initialize2();

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



    public void initialize1() {
        codeE1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
        prixAchat.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixAchat()).asObject());
        prixVente.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixVente()).asObject());
        commande.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQteCommande()).asObject());

        try {
            loadCSVData1();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableView1.setItems(prix);
    }

    private void loadCSVData1() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/files/Prix.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                prix.add(new Prix(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
            }
        }
    }

    public void initialize2() {
        codeC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeC()));
        nomC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomC()));
        entree.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementEntre()));
        sortie.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHashElementSortie()));
        try {
            loadCSVData2();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableView2.setItems(chaines);
    }

    private void loadCSVData2() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/files/Chaines.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                chaines.add(new Chaines(parts[0], parts[1], parseHashMap(parts[2]), parseHashMap(parts[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    //importer les données .CSV
    public void importCSVElements(ActionEvent event) {
        importCSVAndLoadData(elements);
    }

    public void importCSVPrix(ActionEvent event) {
        importCSVAndLoadData(prix);
    }

    public void importCSVChaines(ActionEvent event) {
        importCSVAndLoadData(chaines);
    }

    private void importCSVAndLoadData(ObservableList<?> list) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir un fichier CSV");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers CSV (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                list.clear(); // Efface la liste actuelle avant d'ajouter les nouveaux éléments

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(";");
                        // Ajoutez les éléments dans la liste appropriée
                        if (list==elements){
                            elements.add(new Element(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                        }
                        if (list==prix){
                            prix.add(new Prix(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                        }
                        if (list==chaines){
                            chaines.add(new Chaines(parts[0], parts[1], parseHashMap(parts[2]), parseHashMap(parts[3])));
                        }
                    }

                }
                // Affichage d'un pop-up pour indiquer que l'importation a réussi
                showSuccessAlert();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Affichage d'un pop-up en cas d'erreur
            showErrorAlert();
        }
    }

    //pop-up pour le succés d'importation
    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'importation a été effectuée avec succès !");
        alert.showAndWait();
    }

    //pop-up pour la gestion d'erreur lors de l'importation
    private void showErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'importation du fichier CSV.");
        alert.showAndWait();
    }
}



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
    // Référence statique à l'instance du contrôleur
    /**
     * Obtient l'instance du contrôleur.
     *
     * @return L'instance du contrôleur.
     */
    private static Ecran0Controller instance;
    // Méthode statique pour obtenir l'instance du contrôleur
    /**
     * Définit l'instance du contrôleur.
     */
    public static Ecran0Controller getInstance() {
        return instance;
    }

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

    // Données observables pour les éléments, les prix et les chaînes de production
    /**
     * Obtient la liste des éléments.
     *
     * @return La liste des éléments.
     */
    private ObservableList<Element> elements = FXCollections.observableArrayList();
    /**
     * Obtient la liste des prix.
     *
     * @return La liste des prix.
     */
    private ObservableList<Prix> prix = FXCollections.observableArrayList();
    /**
     * Obtient la liste des chaînes de production.
     *
     * @return La liste des chaînes de production.
     */
    private ObservableList<Chaines> chaines = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Méthode de commutation vers l'écran 1.
     *
     * @param e Événement associé à l'action de commutation.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement de l'écran 1.
     */

    public void switchToEcran1(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode d'initialisation appelée après le chargement de la vue.
     */
    public void initializet() {
    }

    /**
     * Méthode d'initialisation appelée après le chargement de la vue.
     * Initialise les TableView et charge les données CSV.
     */
    public void initialize() {
            instance = this;

            codeE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeE()));
            nomE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomE()));
            quantite.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQuantite()).asObject());
            unite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnite()));



            try {
                loadCSVData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            tableView.setItems(elements);
            initialize1();
            initialize2();
        }

    /**
     * Charge les données des éléments à partir d'un fichier CSV.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV.
     */

    private void loadCSVData() throws IOException {
        String user="user";
        try (InputStream is = getClass().getResourceAsStream("/files/"+user+"/Elements.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                elements.add(new Element(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
            }
            setElements(elements);
        }
    }

    /**
     * Charge les données des prix à partir d'un fichier CSV.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV.
     */
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

    /**
     * Charge les données des chaînes de production à partir d'un fichier CSV.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV.
     */
    private void loadCSVData1() throws IOException {
        String user="user";
        try (InputStream is = getClass().getResourceAsStream("/files/"+user+"/Prix.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                prix.add(new Prix(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
            }
            setPrix(prix);

        }
    }

    /**
     * Charge les données des prix à partir d'un fichier CSV.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV.
     */
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

    /**
     * Charge les données des chaînes de production à partir d'un fichier CSV.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV.
     */
    private void loadCSVData2() throws IOException {
        String user="user";
        try (InputStream is = getClass().getResourceAsStream("/files/"+user+"/Chaines.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                chaines.add(new Chaines(parts[0], parts[1], parseHashMap(parts[2]), parseHashMap(parts[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setChaines(chaines);
    }

    /**
     * Convertit une chaîne de caractères en une map de type HashMap.
     *
     * @param input La chaîne de caractères à convertir.
     * @return La map résultante.
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


    //Importer les données .CSV
    /**
     * Importe des données à partir d'un fichier CSV pour les éléments.
     *
     * @param event L'événement déclenché par l'action d'importation.
     */
    public void importCSVElements(ActionEvent event) {
        importCSVAndLoadData(elements);
    }

    /**
     * Importe des données à partir d'un fichier CSV pour les prix.
     *
     * @param event L'événement déclenché par l'action d'importation.
     */
    public void importCSVPrix(ActionEvent event) {
        importCSVAndLoadData(prix);
    }
    /**
     * Importe des données à partir d'un fichier CSV pour les chaînes de production.
     *
     * @param event L'événement déclenché par l'action d'importation.
     */
    public void importCSVChaines(ActionEvent event) {
        importCSVAndLoadData(chaines);
    }
    /**
     * Importe des données à partir d'un fichier CSV pour la liste spécifiée.
     *
     * @param list La liste dans laquelle importer les données.
     */
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
                            setElements(elements);
                        }
                        if (list==prix){
                            prix.add(new Prix(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                            setPrix(prix);
                        }
                        if (list==chaines){
                            chaines.add(new Chaines(parts[0], parts[1], parseHashMap(parts[2]), parseHashMap(parts[3])));
                            setChaines(chaines);
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

    //Pop-up pour le success d'importation
    /**
     * Affiche un pop-up pour indiquer le succès de l'importation.
     */
    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'importation a été effectuée avec succès !");
        alert.showAndWait();
    }

    //Pop-up pour la gestion d'erreur lors de l'importation
    /**
     * Affiche un pop-up pour gérer les erreurs lors de l'importation.
     */
    private void showErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'importation du fichier CSV.");
        alert.showAndWait();
    }



}



package com.example.projetinit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class MainApplication extends Application {

    /**
     * Méthode principale pour démarrer l'application graphique.
     *
     * @param primaryStage Le stage principal de l'application.
     * @throws Exception En cas d'erreur lors du chargement de l'interface graphique.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

      Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/EcranSession.fxml"));

        primaryStage.setTitle("Squirrel");

        Scene scene = new Scene(root, 700, 500);

        //Icone/logo du projet
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("squirrel.png")));

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Méthode principale pour lancer l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args){
        launch(args);
    }
}

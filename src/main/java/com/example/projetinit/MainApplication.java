package com.example.projetinit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/EcranSession.fxml"));

      //Chemin vers Ecran0 pour le test : a supprimer plus tard !
      // Parent root = FXMLLoader.load(getClass().getResource("/com/example/projetinit/Ecran0.fxml"));

        primaryStage.setTitle("Squirrel");

        Scene scene = new Scene(root, 700, 500);

        //Icone/logo du projet
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("logo.png")));

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

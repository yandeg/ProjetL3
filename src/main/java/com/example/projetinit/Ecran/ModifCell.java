package com.example.projetinit.Ecran;

import com.example.projetinit.attributs.Chaines;

import com.example.projetinit.utils.ApplicationUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ModifCell extends TableCell<Chaines, Void> {
    private final HBox container = new HBox();
    private final Button addButton = new Button("+");
    private final Label counterLabel = new Label("0");
    private final Button minusButton = new Button("-");


    public ModifCell() {
        addButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            count++;
            counterLabel.setText(String.valueOf(count));
            //ChaineAvecNiveauActivation chaine = (ChaineAvecNiveauActivation) getTableRow().getItem();
            //chaine.setNiveauActivation(count);

        });

        minusButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            if (count > 0) {
                count--;
                counterLabel.setText(String.valueOf(count));
                //ChaineAvecNiveauActivation chaine = (ChaineAvecNiveauActivation) getTableRow().getItem();
                //chaine.setNiveauActivation(count);
            }
        });

        container.getChildren().addAll(minusButton, counterLabel,addButton);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(5);

    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(container);
        }
    }
}

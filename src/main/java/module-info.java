module com.example.projetinit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires itextpdf;

    opens com.example.projetinit to javafx.fxml;
    exports com.example.projetinit;
    exports com.example.projetinit.Ecran;
    opens com.example.projetinit.Ecran to javafx.fxml;
    exports com.example.projetinit.attributs;
    opens com.example.projetinit.attributs to javafx.fxml;
    exports com.example.projetinit.export;
    opens com.example.projetinit.export to javafx.fxml;
    exports com.example.projetinit.donne;
    opens com.example.projetinit.donne to javafx.fxml;
}
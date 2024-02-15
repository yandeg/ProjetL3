package com.example.projetinit;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportSimulation {
    public static void exporterSimulation(List<String> k, String exportDirectory) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            //Choisir où exporter le fichier
            PdfWriter.getInstance(document , new FileOutputStream(exportDirectory + "/Simulation.pdf"));
            document.open();
            afficherTitreDocument(document);
            afficherAchats(document);
            afficherActivationChaines(document, k);
            afficherResultats(document);
        } catch(DocumentException de) {
            System.err.println(de.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
    }

    public static void afficherTitreDocument(Document document) throws DocumentException {
        Paragraph titreAvecDate = new Paragraph("Simulation faite le " +
                new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
        titreAvecDate.setAlignment(Element.ALIGN_CENTER);
        titreAvecDate.setSpacingAfter(20.0f);
        document.add(titreAvecDate);
    }

    public static void afficherAchats(Document document) throws DocumentException {
        // TITRE
        Paragraph pAchats = new Paragraph("Achats à effectuer : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pAchats.setSpacingBefore(15.0f);
        pAchats.setSpacingAfter(10.0f);
        document.add(pAchats);
        // TABLEAU
        Achat.remplirAchatsFictifs(); // Liste des achats saisis (FICTIF)
        PdfPTable tabAchats = new PdfPTable(2); // 2 colonnes
        tabAchats.setHorizontalAlignment(Element.ALIGN_LEFT); //Alignement page
        tabAchats.setWidthPercentage(50); //Occupe 50% de la page
        // Header tabAchats
        tabAchats.addCell(new Phrase("CodeE",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tabAchats.addCell(new Phrase("Quantité d'achats",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        // Contenu tabAchats
        for (Achat a : Achat.getAchats()) {
            tabAchats.addCell(a.getCodeE());
            tabAchats.addCell(String.valueOf(a.getQteAchat()));
        }
        document.add(tabAchats);
    }


    public static void afficherActivationChaines(Document document, List<String> k) throws DocumentException {
        // TITRE
        Paragraph pNivActivation = new Paragraph("Niveau d'activation des chaines : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pNivActivation.setSpacingBefore(15.0f);
        pNivActivation.setSpacingAfter(10.0f);
        document.add(pNivActivation);
        // TABLEAU
        PdfPTable tabNivActivation = new PdfPTable(2); // 2 colonnes
        tabNivActivation.setHorizontalAlignment(Element.ALIGN_LEFT); //Alignement page
        tabNivActivation.setWidthPercentage(50); //Occupe 50% de la page
        // Header tabNivActivation
        tabNivActivation.addCell(new Phrase("CodeC",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tabNivActivation.addCell(new Phrase("Niveau d'activation",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        // Contenu tabNivActivation
        /*
        for (Chaines c : //) {
        	tabNivActivation.addCell(c.getCodeC());
        	tabNivActivation.addCell(String.valueOf(c.getNiveauActivation()));
        }
        */
        document.add(tabNivActivation);
    }

    public static void afficherResultats(Document document) throws DocumentException {
        // TITRE
        Paragraph pResultats = new Paragraph("Résultats : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pResultats.setSpacingBefore(20.0f);
        pResultats.setSpacingAfter(10.0f);
        document.add(pResultats);
        // INDICATEUR DE VALEUR
        Paragraph pIndicateurValeur = new Paragraph("Indicateur de valeur : "
                + Calcul.getIndicateurValeur()
                ,FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
        pIndicateurValeur.setSpacingBefore(5.0f);
        pIndicateurValeur.setSpacingAfter(5.0f);
        document.add(pIndicateurValeur);
        // INDICATEUR DE COMMANDE
        Paragraph pIndicateurCommande = new Paragraph("Indicateur de commande : "
                + Calcul.getIndicateurCommande()
                ,FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
        pIndicateurCommande.setSpacingBefore(5.0f);
        pIndicateurCommande.setSpacingAfter(5.0f);
        document.add(pIndicateurCommande);
    }

}
/**
 * Cette classe fournit des méthodes statiques pour exporter des données de simulation vers un fichier PDF.
 * Elle utilise la bibliothèque iText pour générer le document PDF.
 */
package com.example.projetinit.export;

import com.example.projetinit.attributs.Chaines;
import com.example.projetinit.donne.Achat;
import com.example.projetinit.donne.Calcul;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.projetinit.donne.Achat.getAchats;
import static com.example.projetinit.donne.GestionDonnees.*;

public class ExportSimulation {
    /**
     * Exporte les données de simulation vers un fichier PDF.
     * @param exportDirectory Le répertoire d'com.example.projetinit.export.
     */
    public static void exporterSimulation( String exportDirectory) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document , new FileOutputStream(exportDirectory));
            document.open();
            afficherTitreDocument(document);
            afficherAchats(document);
            afficherActivationChaines(document);
            afficherResultats(document);
        } catch(DocumentException de) {
            System.err.println(de.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
    }

    /**
     * Affiche le titre du document PDF avec la date de la simulation.
     * @param document Le document PDF.
     * @throws DocumentException En cas d'erreur lors de l'ajout du titre au document.
     */
    public static void afficherTitreDocument(Document document) throws DocumentException {
        Paragraph titreAvecDate = new Paragraph("Simulation faite le " +
                new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
        titreAvecDate.setAlignment(Element.ALIGN_CENTER);
        titreAvecDate.setSpacingAfter(20.0f);
        document.add(titreAvecDate);
    }

    /**
     * Affiche les achats à effectuer dans le document PDF.
     * @param document Le document PDF.
     * @throws DocumentException En cas d'erreur lors de l'ajout des achats au document.
     */
    public static void afficherAchats(Document document) throws DocumentException {
        // Titre
        Paragraph pAchats = new Paragraph("Achats à effectuer : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pAchats.setSpacingBefore(15.0f);
        pAchats.setSpacingAfter(10.0f);
        document.add(pAchats);
        // Tableau
        PdfPTable tabAchats = new PdfPTable(2); // 2 colonnes
        tabAchats.setHorizontalAlignment(Element.ALIGN_LEFT); // Alignement page
        tabAchats.setWidthPercentage(50); // Occupe 50% de la page
        // Header tabAchats
        tabAchats.addCell(new Phrase("CodeE",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tabAchats.addCell(new Phrase("Quantité d'achats",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        // Contenu tabAchats
        for (Achat a : getAchats()) {
            tabAchats.addCell(a.getCodeE());
            tabAchats.addCell(String.valueOf(a.getQteAchat()));
        }
        document.add(tabAchats);
    }

    /**
     * Affiche le niveau d'activation des chaînes dans le document PDF.
     * @param document Le document PDF.
     * @throws DocumentException En cas d'erreur lors de l'ajout du niveau d'activation au document.
     */
    public static void afficherActivationChaines(Document document) throws DocumentException {
        // Titre
        Paragraph pNivActivation = new Paragraph("Niveau d'activation des chaines : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pNivActivation.setSpacingBefore(15.0f);
        pNivActivation.setSpacingAfter(10.0f);
        document.add(pNivActivation);
        // Tableau
        PdfPTable tabNivActivation = new PdfPTable(2); // 2 colonnes
        tabNivActivation.setHorizontalAlignment(Element.ALIGN_LEFT); // Alignement page
        tabNivActivation.setWidthPercentage(50); // Occupe 50% de la page
        // Header tabNivActivation
        tabNivActivation.addCell(new Phrase("CodeC",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tabNivActivation.addCell(new Phrase("Niveau d'activation",
                FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        // Contenu tabNivActivation
        for (Chaines chaine: getChaineProd()) {
            tabNivActivation.addCell(chaine.getCodeC());
            tabNivActivation.addCell(String.valueOf(chaine.getNiveauActivationC()));
        }
        document.add(tabNivActivation);
    }

    /**
     * Affiche les résultats de la simulation dans le document PDF.
     * @param document Le document PDF.
     * @throws DocumentException En cas d'erreur lors de l'ajout des résultats au document.
     */
    public static void afficherResultats(Document document) throws DocumentException {
        // Titre
        Paragraph pResultats = new Paragraph("Résultats : ",
                FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
        pResultats.setSpacingBefore(20.0f);
        pResultats.setSpacingAfter(10.0f);
        document.add(pResultats);
        // Indicateur de valeur
        Paragraph pIndicateurValeur = new Paragraph("Indicateur de valeur : "
                + Calcul.getIndicateurValeur() + " €"
                ,FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
        pIndicateurValeur.setSpacingBefore(5.0f);
        pIndicateurValeur.setSpacingAfter(5.0f);
        document.add(pIndicateurValeur);
        // Indicateur de commande
        Paragraph pIndicateurCommande = new Paragraph("Indicateur de commande : "
                + Calcul.getIndicateurCommande()*100 + " %"
                ,FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
        pIndicateurCommande.setSpacingBefore(5.0f);
        pIndicateurCommande.setSpacingAfter(5.0f);
        document.add(pIndicateurCommande);
        // Message production impossible
        Paragraph pMessageProdImpossible = new Paragraph(Calcul.getMessageProdImpossible()
                ,FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
        pMessageProdImpossible.setSpacingBefore(15.0f);
        pMessageProdImpossible.setSpacingAfter(5.0f);
        document.add(pMessageProdImpossible);
    }
}
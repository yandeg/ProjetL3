package com.example.projetinit.donne;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private String username;
    private Map<String, String> csvFiles;

    public Session(String username) {
        this.username = username;
        this.csvFiles = new HashMap<>();
    }

    // Méthode pour associer un fichier CSV à la session
    public void addCSVFile(String fileName, String filePath) {
        csvFiles.put(fileName, filePath);
    }

    // Méthode pour obtenir le chemin d'accès du fichier CSV associé à un nom de fichier donné
    public String getCSVFilePath(String fileName) {
        return csvFiles.get(fileName);
    }

    // Méthode pour supprimer un fichier CSV de la session
    public void removeCSVFile(String fileName) {
        csvFiles.remove(fileName);
    }

    // Méthode pour vérifier si un fichier CSV est associé à la session
    public boolean containsCSVFile(String fileName) {
        return csvFiles.containsKey(fileName);
    }

    // Méthode pour obtenir tous les noms de fichiers CSV associés à la session
    public String[] getAllCSVFileNames() {
        return csvFiles.keySet().toArray(new String[0]);
    }
}
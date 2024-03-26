package com.example.projetinit.donne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private String identifiant;

    public Session() {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public class CSVReader {
        public static List<Session> readSessionsFromCSV(String filename) throws IOException {
            List<Session> sessions = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 1) {
                        Session session = new Session();
                        session.setIdentifiant(data[0]);
                        sessions.add(session);
                    }
                }
            }
            return sessions;
        }
    }




}

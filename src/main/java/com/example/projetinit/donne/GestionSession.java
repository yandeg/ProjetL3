package com.example.projetinit.donne;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestionSession {
    private Map<String, Session> sessions;
    private String sessionDirectory;

    public GestionSession(String sessionDirectory) {
        this.sessions = new HashMap<>();
        this.sessionDirectory = sessionDirectory;
        File directory = new File(sessionDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void startSession(String username) {
        if (!sessions.containsKey(username)) {
            sessions.put(username, new Session(username));
            saveSessionToFile(username);
        }
    }

    public Session getSession(String username) {
        return sessions.get(username);
    }

    public void endSession(String username) {
        sessions.remove(username);
        File sessionFile = new File(sessionDirectory, username + ".ser");
        sessionFile.delete(); // Supprime le fichier de session
    }

    private void saveSessionToFile(String username) {
        try (FileOutputStream fileOut = new FileOutputStream(new File(sessionDirectory, username + ".ser"));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(sessions.get(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Session loadSessionFromFile(String username) {
        try (FileInputStream fileIn = new FileInputStream(new File(sessionDirectory, username + ".ser"));
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Session) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
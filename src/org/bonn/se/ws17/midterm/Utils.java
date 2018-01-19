package org.bonn.se.ws17.midterm;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class Utils {
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        double prio;
        double temp;
        prio = mehrwert + strafe;
        temp = aufwand + risiko;
        prio = prio / temp;
        return prio;
    }
    
    public static void store() {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("UserStoryListe.ser");
            oos = new ObjectOutputStream(fos);
            
            try {
                oos.writeObject(Container.getContainer().listausgabe());
                System.out.println(Container.getContainer().anzahl() + " User Stories wurden erfolgreich gespeichert!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void load() {
        ObjectInputStream ois;
        FileInputStream fis;
        try {
            fis = new FileInputStream("UserStoryListe.ser");
            ois = new ObjectInputStream(fis);
            
            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                Container.getContainer().setListe((List<UserStory>) obj);
                
            }
            System.out.println("Es wurden " + Container.getContainer().anzahl() + " User Stories erfolgreich reingeladen!");
        } catch (IOException e) {
            System.out.println("FEHLER: Datei konnte nicht gefunden werden!");
        } catch (ClassNotFoundException e) {
            System.out.println("FEHLER: Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
    }
    
    public void erledigt(UUID id, boolean b) {
        Container.getContainer().get(id).setDone(b);
    }
}

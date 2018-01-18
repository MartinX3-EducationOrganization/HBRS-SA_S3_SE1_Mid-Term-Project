package org.bonn.se.ws17.midterm;

import java.io.*;
import java.util.List;

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
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
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
        } finally {
            if (oos != null) {try { oos.close(); } catch (IOException e) {}}
            if (fos != null) {try { fos.close(); } catch (IOException e) {}}
        }
        
    }
    
    public static void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
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
        } finally {
            if (ois != null) {try { ois.close(); } catch (IOException e) {}}
            if (fis != null) {try { fis.close(); } catch (IOException e) {}}
        }
    }
}

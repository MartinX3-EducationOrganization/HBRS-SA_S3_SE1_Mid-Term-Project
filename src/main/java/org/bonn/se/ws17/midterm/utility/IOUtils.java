package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;

import java.io.*;
import java.util.List;

public class IOUtils {
    
    public static void store() {
        try (FileOutputStream fos = new FileOutputStream("UserStoryListe.ser")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(Container.getContainer().getUserStories(false));
                System.out.println(String.format("%s User Stories wurden erfolgreich gespeichert!", Container.getContainer().size()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void load() {
        Container.getContainer().clearUS();
    
        List<UserStory> userStories = null;
        try (FileInputStream in = new FileInputStream("UserStoryListe.ser")) {
            try (ObjectInputStream ois = new ObjectInputStream(in)) {
                userStories = (List<UserStory>) (ois.readObject());
            } catch (EOFException eofe) {
                //Fileend reached.
            } catch (ClassNotFoundException e) {
                System.out.println("Beim laden der Userstories wurde eine unbekannte Klasse gefunden.");
            }
        } catch (IOException e) {
            System.out.println("Fehler beim laden der Userstories.");
            return;
        }
        
        if (userStories != null) {
            for (UserStory us : userStories) {
                try {
                    Container.getContainer().addUS(us);
                } catch (ContainerException e) {
                    System.out.println("Fehler beim laden der Userstories.");
                    return;
                }
            }
            System.out.println(String.format("%s UserStories geladen.", Container.getContainer().size()));
        }
    }
}

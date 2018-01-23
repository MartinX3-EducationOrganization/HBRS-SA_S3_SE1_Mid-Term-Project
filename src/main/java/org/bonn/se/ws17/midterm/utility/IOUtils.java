package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;

import java.io.*;
import java.util.List;

public class IOUtils {
    
    public static void store() {
        try {
            try (FileOutputStream fos = new FileOutputStream("UserStoryListe.ser")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(Container.getContainer().getUserStories());
                    System.out.println(Container.getContainer().size() + " User Stories wurden erfolgreich gespeichert!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void load() {
        Container container = Container.getContainer();
        container.clear();
    
        List<UserStory> userStories = null;
        try {
            try (FileInputStream in = new FileInputStream("UserStoryListe.ser")) {
                try (ObjectInputStream ois = new ObjectInputStream(in)) {
                    userStories = (List<UserStory>) (ois.readObject());
                } catch (EOFException eofe) {
                    //Fileend reached.
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler beim laden der Userstories.");
            return;
        }
        
        if (userStories != null) {
            for (UserStory us : userStories) {
                try {
                    container.add(us);
                } catch (ContainerException e) {
                    System.out.println("Fehler beim laden der Userstories.");
                    return;
                }
            }
            System.out.println("UserStoires (" + Container.getContainer().size() + ") geladen.");
        }
    }
}

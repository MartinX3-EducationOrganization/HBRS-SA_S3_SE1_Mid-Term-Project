package org.bonn.se.ws17.midterm;

import java.io.*;
import java.util.List;

public class IOUtils {
    
    public static void store() {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("UserStoryListe.ser");
            oos = new ObjectOutputStream(fos);
            
            try {
                oos.writeObject(Container.getContainer().getList());
                System.out.println(Container.getContainer().size() + " User Stories wurden erfolgreich gespeichert!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static void load() {
        Container container = Container.getContainer();
        container.clear();
    
        List<UserStory> userStories;
        try {
            try (FileInputStream in = new FileInputStream("UserStoryListe.ser")) {
                try (ObjectInputStream ois = new ObjectInputStream(in)) {
                    userStories = (List<UserStory>) (ois.readObject());
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
        }
    }
}

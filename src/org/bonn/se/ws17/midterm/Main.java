package org.bonn.se.ws17.midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        System.out.println("Willkommen im UserStory-Programm:");
        System.out.println("Hier können UserStories eingegeben werden, " + "\n" + "UserStories-Listen gespeichert, geladen und angezeigt werden.");
        System.out.println("Für Hilfe der Kommandos, geben Sie bitte " + "'help'" + " ein.");
        String strInput = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                strInput = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] strings = strInput.split(" ");
            if (strings[0].equals("help")) {
                System.out.print("Folgende Befehle stehen zur Verfuegung:" + "\n" +
                        "'enter' > Eingabe der UserStory" + "\n" +
                        "'store' > Speichert die UserStories auf den Datenträger." + "\n" +
                        "'load' > Ladet die UserStories vom Datenträger." + "\n" +
                        "'dump' > Sortiert die vorhanden UserStories und gibt diese aus." + "\n" +
                        "'help' > Zeigt alle Commandos an." + "\n");
            }
            
            if (strings[0].equals("dump")) {
                Container container = Container.getContainer();
                System.out.println("Userstories:");
                List<UserStory> list = container.getList();
                Collections.sort(list);
                for (UserStory us : list) {
                    System.out.println(us.toString());
                    System.out.println("---------------------------------------------------------");
                }
            }
            
            if (strings[0].equals("load")) {
                IOUtils.load();
            }
            
            if (strings[0].equals("store")) {
                IOUtils.store();
            }
            
            if (strings[0].equals("enter")) {
                try {
                    InputUtils.eingabe();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        
            }
        }
    }
}

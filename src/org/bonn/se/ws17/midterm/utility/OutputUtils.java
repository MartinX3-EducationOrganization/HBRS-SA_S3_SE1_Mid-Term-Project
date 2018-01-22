package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

import java.util.List;
import java.util.UUID;

public class OutputUtils {
    private static final String[] parameterList = new String[]{"all", "details", "hints"};
    
    public static void welcome() {
        System.out.println("Willkommen im UserStory-Programm:");
        System.out.println("Hier können UserStories eingegeben werden, " + "\n" + "UserStories-Listen gespeichert, geladen und angezeigt werden.");
        System.out.println("Für Hilfe der Kommandos, geben Sie bitte " + "'help'" + " ein.");
    }
    
    public static void help() {
        System.out.print("Folgende Befehle stehen zur Verfuegung:" + "\n" +
                "'enter' > Eingabe der UserStory" + "\n" +
                "'store' > Speichert die UserStories auf den Datenträger." + "\n" +
                "'load' > Ladet die UserStories vom Datenträger." + "\n" +
                "'dump' > Sortiert die vorhanden UserStories und gibt diese aus." + "\n" +
                "'dumpNotDone' > Zeigt alle Userstories an die nocht nicht abgeschlossen sind." + "\n" +
                "'help' > Zeigt alle Commandos an." + "\n");
    }
    
    public static void analyze(String s) {
        if (s.matches("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}")) {
            UUID usid = UUID.fromString(s);
            if (Container.getContainer().contains(usid)) {
                int punkte = CalcUtils.bewertung(Container.getContainer().get(usid));
                System.out.println(String.format("Die Userstory mit der ID [%s] hat folgende Qualität:", s));
                //System.out.println(String.format("%d%s (%s)", punkte, "%", CalcUtils.note(punkte)));
            } else {
                System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", s));
            }
        } else {
            System.out.println(String.format("Die eingegebene ID [%s] entspricht nicht dem UUID-Standard", s));
            System.out.println("Geben sie 'analyze' 'UUID' erneut ein.");
        }
    }
    
    public static void analyzeAll() {
        int bewertung = 0;
        List<UserStory> list = Container.getContainer().getList();
        for (int i = 0; i < list.size(); i++) {
            bewertung += CalcUtils.bewertung(list.get(i));
        }
        System.out.println(String.format("Ihre %d User Stories haben durchschnittlich folgende Qualität:"));
        System.out.println(String.format("&d%s (%s)", bewertung / list.size(), "%", CalcUtils.note(bewertung / list.size())));
    }
    
    // all details hints
    public static void parameter(String string) {
        switch (string) {
            
            case "details":
                //TODO
                break;
            case "hints":
                //TODO
                break;
            default:
                System.out.println(String.format("Der Parameter - %s ist kein gültiger. Geben Sie 'help' ein für Hilfe.", string));
        }
    }
}
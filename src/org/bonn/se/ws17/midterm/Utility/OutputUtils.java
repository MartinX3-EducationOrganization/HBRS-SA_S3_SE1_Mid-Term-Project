package org.bonn.se.ws17.midterm.Utility;

public class OutputUtils {
    
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
}

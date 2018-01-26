package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.model.Container;

public class OutputUtils {
    public static void welcome() {
        System.out.println("Willkommen im UserStory-Programm:");
        System.out.println("Hier können UserStories eingegeben werden, " + "\n" + "UserStories-Listen gespeichert, geladen und angezeigt werden.");
        System.out.println("Für Hilfe der Kommandos, geben Sie bitte " + "'help'" + " ein.");
    }
    
    public static void listActors() {
        System.out.println("Folgende Akteure sind eingetragen:");
        
        for (String actor : Container.getContainer().getActors()) {
            System.out.println(actor);
        }
    }
}
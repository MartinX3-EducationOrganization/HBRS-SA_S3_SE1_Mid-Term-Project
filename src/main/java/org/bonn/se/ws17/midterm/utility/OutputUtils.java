package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

import java.util.List;

import static org.bonn.se.ws17.midterm.analyze.Analyze.countWords;

public class OutputUtils {
    
    public static void welcome() {
        System.out.println("Willkommen im UserStory-Programm:");
        System.out.println("Hier können UserStories eingegeben werden, " + "\n" + "UserStories-Listen gespeichert, geladen und angezeigt werden.");
        System.out.println("Für Hilfe der Kommandos, geben Sie bitte " + "'help'" + " ein.");
    }
    
    public static void analyze(String usid) {
        if (OutputUtils.isUUID(usid)) {
            if (Container.getContainer().contains(usid)) {
                int punkte = CalcUtils.bewertung(Container.getContainer().get(usid));
                System.out.println(String.format("Die Userstory mit der ID [%s] hat folgende Qualität:", usid));
                System.out.println(String.format("%d%s (%s)", punkte, "%", CalcUtils.note(punkte)));
            } else {
                System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", usid));
            }
        }
    }
    
    public static boolean isUUID(String usid) {
        if (usid.matches("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}")) {
            return true;
        }
        System.out.println(String.format("Die eingegebene ID [%s] entspricht nicht dem UUID-Standard", usid));
        System.out.println("Geben sie 'analyze' 'UUID' erneut ein.");
        return false;
    }
    
    public static void analyzeAll() {
        int bewertung = 0;
        List<UserStory> list = Container.getContainer().getUserStories();
        for (int i = 0; i < list.size(); i++) {
            bewertung += CalcUtils.bewertung(list.get(i));
        }
        System.out.println(String.format("Ihr(e) %d User-Stories haben durchschnittlich folgende Qualität:", Container.getContainer().size()));
        System.out.println(String.format("%d%s (%s)", bewertung / list.size(), "%", CalcUtils.note(bewertung / list.size())));
    }
    
    public static void details(String uuid) {
        UserStory us = Container.getContainer().get(uuid);
        System.out.println();
        System.out.println("Details:");
    
        boolean fehlerfrei = true;
    
        if (us.getTitel().equals("")) {
            System.out.println("Es fehlt ein Titel in ihrer Userstory. ");
            fehlerfrei = false;
        } else if (countWords(us.getTitel()) > 3) {
            System.out.println("Ihr Titel besteht aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getBeschreibung().equals("")) {
            System.out.println("Es fehlt eine Beschreibung ihrer Userstory.");
            fehlerfrei = false;
        } else if (countWords(us.getBeschreibung()) > 50) {
            System.out.println("Ihre Beschreibung besteht aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getDetails().equals("")) {
            System.out.println("Es fehlen Details zu ihrer Userstory.");
            fehlerfrei = false;
        } else if (countWords(us.getDetails()) > 30) {
            System.out.println("Ihre Details bestehen aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getAkzeptanz().equals("")) {
            System.out.println("Es fehlen Akzeptanzkriterien in ihrer Userstory.");
            fehlerfrei = false;
        } else if (countWords(us.getAkzeptanz()) > 30) {
            System.out.println("Ihre Beschreibung besteht aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getMehrwert().equals("")) {
            System.out.println("Es fehlt eine schriftliche Mehrwertbeschreibung ihrer Userstory.");
            fehlerfrei = false;
        } else if (countWords(us.getMehrwert()) > 30) {
            System.out.println("Ihre Mehrwertbeschreibung besteht aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getEpic().equals("")) {
            System.out.println("Es fehlt die Epik ihrer Userstory.");
            fehlerfrei = false;
        } else if (countWords(us.getEpic()) > 3) {
            System.out.println("Ihre Epik besteht aus zu vielen Wörtern.");
            fehlerfrei = false;
        }
    
        if (us.getActor().equals("")) {
            System.out.println("Es fehllt ein Akteur in ihrer Userstory.");
            fehlerfrei = false;
        } else if (!Container.getContainer().getActors().contains(us.getActor())) {
            System.out.println(String.format("Der Akteuer (%s) ist nicht registriert.", us.getActor()));
            fehlerfrei = false;
        }
    
        if (fehlerfrei) {
            System.out.println("Alles ok!");
        }
    }
    
    public static void hints(String uuid) {
        UserStory us = Container.getContainer().get(uuid);
    
        System.out.println();
        System.out.println("Hints:");
        
        if (us.getTitel().equals("")) {
            System.out.println("Geben sie ihrer Userstory einen Titel !");
        } else if (countWords(us.getTitel()) > 3) {
            System.out.println("Beschränken sie ihren Titel auf weniger als 3 Wörter!");
        }
    
        if (us.getBeschreibung().equals("")) {
            System.out.println("Fügen sie in ihrer Userstory eine Beschreibung ein!");
        } else if (countWords(us.getBeschreibung()) > 50) {
            System.out.println("Formulieren sie ihre Beschreibung kurz und pregnant!");
        }
    
        if (us.getDetails().equals("")) {
            System.out.println("Fügen sie in ihrer Userstory Details ein!");
        } else if (countWords(us.getDetails()) > 30) {
            System.out.println("Beschränken sie ihre Details auf das Wesentliche!");
        }
    
        if (us.getAkzeptanz().equals("")) {
            System.out.println("Fügen sie in ihre Userstory Akzeptanzkriterien ein!");
        } else if (countWords(us.getAkzeptanz()) > 30) {
            System.out.println("Beschränken sie ihre Akzeptanzkriterien auf das Wesentliche!");
        }
    
        if (us.getMehrwert().equals("")) {
            System.out.println("Fügen sie in ihre Userstory einen Mehrwertbeschreibung ein!");
        } else if (countWords(us.getMehrwert()) > 30) {
            System.out.println("Formulieren sie ihre Mehrwertbeschreibung in weniger Wörtern!");
        }
    
        if (us.getEpic().equals("")) {
            System.out.println("Fügen sie in ihre Userstory eine Epik ein!");
        } else if (countWords(us.getEpic()) > 3) {
            System.out.println("Formulieren sie ihre Epik in eins bis zwei Wörtern!");
        }
    
        if (us.getActor().equals("")) {
            System.out.println("Fügen sie in ihrer Userstory einen Akteur ein!");
        } else if (!Container.getContainer().getActors().contains(us.getActor())) {
            System.out.println(String.format("Fügen sie den %s in die Akteurenliste ein!" + "\n" + "Oder ändern sie ihren Akteuer('help' für Infos)", us.getActor()));
        }
    }
}
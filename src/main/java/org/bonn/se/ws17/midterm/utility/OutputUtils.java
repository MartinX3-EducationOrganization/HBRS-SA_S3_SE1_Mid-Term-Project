package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

import java.util.List;
import java.util.UUID;

import static org.bonn.se.ws17.midterm.analyze.Analyze.countWords;

public class OutputUtils {
    
    public static void welcome() {
        System.out.println("Willkommen im UserStory-Programm:");
        System.out.println("Hier können UserStories eingegeben werden, " + "\n" + "UserStories-Listen gespeichert, geladen und angezeigt werden.");
        System.out.println("Für Hilfe der Kommandos, geben Sie bitte " + "'help'" + " ein.");
    }
    
    public static void analyze(String usid) {
        if (Container.getContainer().contains(usid)) {
            int punkte = CalcUtils.bewertung(Container.getContainer().get(usid));
            System.out.println(String.format("Die Userstory mit der ID [%s] hat folgende Qualität:", usid));
            System.out.println(String.format("%d%s (%s)", punkte, "%", CalcUtils.note(punkte)));
        } else {
            System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", usid));
        }
    }
    
    public static boolean isUUID(String usid) {
        try {
            UUID.fromString(usid);
            return true;
        } catch (IllegalArgumentException iae) {
            System.out.println(String.format("Die eingegebene ID [%s] entspricht nicht dem UUID-Standard", usid));
            System.out.println("Geben sie 'analyze' 'UUID' erneut ein.");
            return false;
        }
    }
    
    private static void analyzeAll() {
        int bewertung;
        List<UserStory> list = Container.getContainer().getUserStories(false);
        
        bewertung = list.stream().mapToInt(CalcUtils::bewertung).sum();
        
        System.out.println(String.format("Ihr(e) %d User-Stories haben durchschnittlich folgende Qualität:", Container.getContainer().size()));
        System.out.println(String.format("%d%s (%s)", bewertung / list.size(), "%", CalcUtils.note(bewertung / list.size())));
    }
    
    private static void detailsAnalyze(String uuid) {
        UserStory us = Container.getContainer().get(uuid);
        System.out.println();
        System.out.println("Details:");
        
        if (OutputUtils.analyzeUSText(us.getTitel(), "Es fehlt ein Titel in ihrer Userstory.(-5%)", 3, "Ihr Titel besteht aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSText(us.getBeschreibung(), "Es fehlt eine Beschreibung ihrer Userstory.(-10%)", 50, "Ihre Beschreibung besteht aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSText(us.getDetails(), "Es fehlen Details zu ihrer Userstory.(-15%)", 30, "Ihre Details bestehen aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSText(us.getAkzeptanz(), "Es fehlen Akzeptanzkriterien in ihrer Userstory.(-15%)", 30, "Ihre Beschreibung besteht aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSText(us.getMehrwert(), "Es fehlt eine schriftliche Mehrwertbeschreibung ihrer Userstory.(-15%)", 30, "Ihre Mehrwertbeschreibung besteht aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSText(us.getEpic(), "Es fehlt die Epic ihrer Userstory.(-5%)", 3, "Ihre Epic besteht aus zu vielen Wörtern.(-5%)")
                && OutputUtils.analyzeUSActor(us.getActor(), "Es fehlt ein Akteur in ihrer Userstory.(-10%)", "Der Akteur: %s  ist nicht registriert.(-10%%)")) {
            System.out.println("Alles in Ordnung!");
        }
    }
    
    private static void hintsAnalyze(String uuid) {
        UserStory us = Container.getContainer().get(uuid);
    
        System.out.println();
        System.out.println("Hints:");
        
        OutputUtils.analyzeUSText(us.getTitel(), "Geben sie ihrer Userstory einen Titel !", 3, "Beschränken sie ihren Titel auf weniger als 3 Wörter!");
        OutputUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihrer Userstory eine Beschreibung ein!", 50, "Formulieren sie ihre Beschreibung kurz und pregnant!");
        OutputUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihrer Userstory Details ein!", 30, "Beschränken sie ihre Details auf das Wesentliche!");
        OutputUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory Akzeptanzkriterien ein!", 30, "Beschränken sie ihre Akzeptanzkriterien auf das Wesentliche!");
        OutputUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory einen Mehrwertbeschreibung ein!", 30, "Formulieren sie ihre Mehrwertbeschreibung in weniger Wörtern!");
        OutputUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory eine Epic ein!", 3, "Formulieren sie ihre Epic in eins bis zwei Wörtern!");
        OutputUtils.analyzeUSActor(us.getActor(), "Fügen sie in ihrer Userstory einen Akteur ein!", "Fügen sie den %s in die Akteurenliste ein!" + "\n" + "Oder ändern sie ihren Akteuer('help' für Infos)");
    }
    
    private static boolean analyzeUSText(String titel, String emptyWarning, int maxWordCount, String wordCountWarning) {
        if (titel.equals("")) {
            System.out.println(emptyWarning);
            
            return false;
        } else if (countWords(titel) > maxWordCount) {
            System.out.println(wordCountWarning);
            
            return false;
        }
        
        return true;
    }
    
    private static boolean analyzeUSActor(String actor, String emptyWarning, String actorWarning) {
        if (actor.equals("")) {
            System.out.println(emptyWarning);
            return false;
        } else if (!Container.getContainer().getActors().contains(actor)) {
            System.out.println(String.format(actorWarning, actor));
            return false;
        }
        
        return true;
    }
    
    public static void listActors() {
        System.out.println("Folgende Akteure sind eingetragen:");
        
        for (String actor : Container.getContainer().getActors()) {
            System.out.println(actor);
        }
    }
    
    public static void analyze(String[] params) {
        if (params == null) {
            params = new String[0];
        }
        
        if (params.length > 0) {
            if (params.length != 2) {
                if (!OutputUtils.isUUID(params[0])) {
                    ErrorUtils.cmdNotFound(String.join(" ", params));
                    return;
                }
            }
            
            if (params.length != 1 && params.length != 2) {
                if (params[1].equals("-")) {
                    ErrorUtils.cmdNotFound(String.join(" ", params));
                    return;
                }
            }
        }
        
        switch (params.length) {
            case 1: {
                // Wenn nur id
                OutputUtils.analyze(params[0]);
                break;
            }
            case 2: {
                // Wenn - all
                if (params[0].equals("-") && params[1].equals("all")) {
                    OutputUtils.analyzeAll();
                    return;
                }
                break;
            }
            case 3: {
                // Wenn id - details
                OutputUtils.analyze(params[0]);
                if (params[2].equals("details")) {
                    OutputUtils.detailsAnalyze(params[0]);
                    return;
                }
                break;
            }
            case 4: {
                // Wenn id - details
                OutputUtils.analyze(params[0]);
                if (params[2].equals("details")) {
                    OutputUtils.detailsAnalyze(params[0]);
                    return;
                }
                break;
            }
            case 5: {
                // Wenn id - details - hints
                OutputUtils.analyze(params[0]);
                if (params[2].equals("details")) {
                    OutputUtils.detailsAnalyze(params[0]);
                    
                    if (params[3].equals("-") && params[4].equals("hints")) {
                        OutputUtils.hintsAnalyze(params[0]);
                    }
                    return;
                }
                break;
            }
        }
        
        ErrorUtils.cmdNotFound(String.join(" ", params));
    }
}
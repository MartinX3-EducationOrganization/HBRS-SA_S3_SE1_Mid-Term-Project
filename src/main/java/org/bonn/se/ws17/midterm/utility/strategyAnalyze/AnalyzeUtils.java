package org.bonn.se.ws17.midterm.utility.strategyAnalyze;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.ErrorUtils;

import java.util.List;
import java.util.UUID;

public class AnalyzeUtils implements AnalyzeInterface {
    private static int bewertung(UserStory us) {
        return 100 - AnalyzeUtils.malus(us);
    }
    
    private static String note(int punkte) {
        if (punkte == 100) {
            return "Sehr gut";
        }
        if (punkte >= 50) {
            return "Gut";
        }
        if (punkte >= 25) {
            return "Nicht gut";
        }
        return "Schlecht";
    }
    
    private static int malus(UserStory us) {
        return AnalyzeUtils.missing(us) + AnalyzeUtils.bandwurmMalus(us);
    }
    
    /**
     * Punkteabzug für fehlende Inhalte oder zu lange Texte.
     */
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("") || AnalyzeUtils.countWords(us.getTitel()) > 3) {
            malus += 5;
        }
        
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        } else if (AnalyzeUtils.countWords(us.getBeschreibung()) > 50) {
            malus += 5;
        }
        
        if (us.getDetails().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getDetails()) > 30) {
            malus += 5;
        }
        
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getAkzeptanz()) > 30) {
            malus += 5;
        }
        
        if (us.getMehrwert().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getMehrwert()) > 30) {
            malus += 5;
        }
    
        if (us.getEpic().equals("") || AnalyzeUtils.countWords(us.getEpic()) > 3) {
            malus += 5;
        }
    
        if (us.getActor().equals("") || Container.getContainer().containsActor(us.getActor())) {
            malus += 10;
        }
        
        return malus;
    }
    
    /**
     * Wenn in der US doppelt soviele Kommas "," vorkommen als Punkte gibt es Abzüge.
     * One Liner wie Epic oder Titel sind ausser acht zu lassen.
     */
    private static int bandwurmMalus(UserStory us) {
        int malus = 0; // max -15
    
        if (us.getBeschreibung() != null) {
    
            if (us.getBeschreibung().length() < 1 || AnalyzeUtils.bandcounter(us.getBeschreibung(), ',') > (2 * AnalyzeUtils.bandcounter(us.getBeschreibung(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getDetails() != null) {
            if (us.getDetails().length() < 1 || AnalyzeUtils.bandcounter(us.getDetails(), ',') > (2 * AnalyzeUtils.bandcounter(us.getDetails(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getMehrwert() != null) {
            if (us.getMehrwert().length() < 1 || AnalyzeUtils.bandcounter(us.getMehrwert(), ',') > (2 * AnalyzeUtils.bandcounter(us.getMehrwert(), '.'))) {
                malus += 5;
            }
        }
    
        return malus;
    }
    
    private static int bandcounter(String s, char sign) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sign) {
                count++;
            }
        }
        return count;
    }
    
    private static int countWords(String s) {
        return s.split(" ").length;
    }
    
    private static void analyze(String usid) {
        if (Container.getContainer().containsUS(usid)) {
            int punkte = AnalyzeUtils.bewertung(Container.getContainer().getUS(usid));
            System.out.println(String.format("Die Userstory mit der ID [%s] hat folgende Qualität:", usid));
            System.out.println(String.format("%d%s (%s)", punkte, "%", AnalyzeUtils.note(punkte)));
        } else {
            System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", usid));
        }
    }
    
    private static void analyzeAll() {
        int bewertung;
        List<UserStory> list = Container.getContainer().getUserStories(false);
        
        bewertung = list.stream().mapToInt(AnalyzeUtils::bewertung).sum();
        
        System.out.println(String.format("Ihr(e) %d User-Stories haben durchschnittlich folgende Qualität:", Container.getContainer().size()));
        System.out.println(String.format("%d%s (%s)", bewertung / list.size(), "%", AnalyzeUtils.note(bewertung / list.size())));
    }
    
    private static void detailsAnalyze(String uuid) {
        UserStory us = Container.getContainer().getUS(uuid);
        System.out.println();
        System.out.println("Details:");
        
        if (AnalyzeUtils.analyzeUSText(us.getTitel(), "Es fehlt ein Titel in ihrer Userstory.(-5%)", 3, "Ihr Titel besteht aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSText(us.getBeschreibung(), "Es fehlt eine Beschreibung ihrer Userstory.(-10%)", 50, "Ihre Beschreibung besteht aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSText(us.getDetails(), "Es fehlen Details zu ihrer Userstory.(-15%)", 30, "Ihre Details bestehen aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSText(us.getAkzeptanz(), "Es fehlen Akzeptanzkriterien in ihrer Userstory.(-15%)", 30, "Ihre Beschreibung besteht aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSText(us.getMehrwert(), "Es fehlt eine schriftliche Mehrwertbeschreibung ihrer Userstory.(-15%)", 30, "Ihre Mehrwertbeschreibung besteht aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSText(us.getEpic(), "Es fehlt die Epic ihrer Userstory.(-5%)", 3, "Ihre Epic besteht aus zu vielen Wörtern.(-5%)")
                && AnalyzeUtils.analyzeUSActor(us.getActor(), "Es fehlt ein Akteur in ihrer Userstory.(-10%)", "Der Akteur: %s  ist nicht registriert.(-10%%)")) {
            System.out.println("Alles in Ordnung!");
        }
    }
    
    private static void hintsAnalyze(String uuid) {
        UserStory us = Container.getContainer().getUS(uuid);
        
        System.out.println();
        System.out.println("Hints:");
        
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Geben sie ihrer Userstory einen Titel !", 3, "Beschränken sie ihren Titel auf weniger als 3 Wörter!");
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihrer Userstory eine Beschreibung ein!", 50, "Formulieren sie ihre Beschreibung kurz und pregnant!");
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihrer Userstory Details ein!", 30, "Beschränken sie ihre Details auf das Wesentliche!");
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory Akzeptanzkriterien ein!", 30, "Beschränken sie ihre Akzeptanzkriterien auf das Wesentliche!");
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory einen Mehrwertbeschreibung ein!", 30, "Formulieren sie ihre Mehrwertbeschreibung in weniger Wörtern!");
        AnalyzeUtils.analyzeUSText(us.getTitel(), "Fügen sie in ihre Userstory eine Epic ein!", 3, "Formulieren sie ihre Epic in eins bis zwei Wörtern!");
        AnalyzeUtils.analyzeUSActor(us.getActor(), "Fügen sie in ihrer Userstory einen Akteur ein!", "Fügen sie den %s in die Akteurenliste ein!" + "\n" + "Oder ändern sie ihren Akteuer('help' für Infos)");
    }
    
    private static boolean analyzeUSText(String titel, String emptyWarning, int maxWordCount, String wordCountWarning) {
        if (titel.equals("")) {
            System.out.println(emptyWarning);
            
            return false;
        } else if (titel.split(" ").length > maxWordCount) {
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
    
    public static boolean isNotUUID(String usid) {
        try {
            UUID.fromString(usid);
            return false;
        } catch (IllegalArgumentException iae) {
            System.out.println(String.format("Die eingegebene ID [%s] entspricht nicht dem UUID-Standard", usid));
            System.out.println("Geben sie 'analyze' 'UUID' erneut ein.");
            return true;
        }
    }
    
    @Override
    public void analyze(String[] params) {
        if (params == null) {
            params = new String[0];
        }
        
        if (params.length > 0) {
            if (params.length != 2) {
                if (AnalyzeUtils.isNotUUID(params[0])) {
                    ErrorUtils.cmdNotFound(String.join(" ", params));
                    return;
                }
            }
    
            if (params.length != 1 && params.length != 3 && params.length != 5) {
                if (params[1].equals("-")) {
                    ErrorUtils.cmdNotFound(String.join(" ", params));
                    return;
                }
            }
        }
        
        switch (params.length) {
            case 1: {
                // Wenn nur id
                AnalyzeUtils.analyze(params[0]);
                return;
            }
            case 2: {
                // Wenn - all
                if (params[0].equals("-") && params[1].equals("all")) {
                    AnalyzeUtils.analyzeAll();
                    return;
                }
                break;
            }
            case 3: {
                // Wenn id - details
                AnalyzeUtils.analyze(params[0]);
                if (params[2].equals("details")) {
                    AnalyzeUtils.detailsAnalyze(params[0]);
                    return;
                }
                break;
            }
            case 5: {
                // Wenn id - details - hints
                AnalyzeUtils.analyze(params[0]);
                if (params[2].equals("details")) {
                    AnalyzeUtils.detailsAnalyze(params[0]);
    
                    if (params[3].equals("-") && params[4].equals("hints")) {
                        AnalyzeUtils.hintsAnalyze(params[0]);
                    }
                    return;
                }
                break;
            }
        }
        
        ErrorUtils.cmdNotFound(String.join(" ", params));
    }
}
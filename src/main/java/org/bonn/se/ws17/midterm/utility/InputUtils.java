package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.view.Terminal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InputUtils {
    public static String eingabe() throws Exception {
        UserStory us = new UserStory();
    
        try (Terminal t = new Terminal()) {
            us.setTitel(readText(t, "Geben Sie zunächst einen Titel für ihrer Userstory ein:"));
        
            us.setActor(readText(t, "Aus welcher Sicht ist die Userstory verfasst? (Akteur)"));
        
            us.setBeschreibung(readText(t, "Geben Sie eine kurze Beschreibung ihrer Userstory ein:"));
        
            us.setDetails(readText(t, "Geben Sie jetzt ihre Details zur Userstory ein:"));
        
            us.setAkzeptanz(readText(t, "Es folgen die Akzeptanzkriterien ihrer Userstory:"));
        
            us.setEpic(readText(t, "Unter welcher Epic lässt sich ihre Userstory einordnen?"));
        
            us.setMehrwert(readText(t, "Wie schätzen sie den Mehrwert ein? (schriftlich)"));
        
            us.setMwert(readInteger(t, "Welchen Mehrwert schätzen Sie ? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
        
            us.setStrafe(readInteger(t, "Welchen Wert hat die Strafe? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
        
            us.setRisiko(readInteger(t, "Wie hoch schätzen Sie das Risiko ein? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
        
            us.setAufwand(readInteger(t, "Wie hoch dürfte der Aufwand sein? (%s)", CalcUtils.getFibonacciZahlen()));
        }
        
        try {
            Container.getContainer().addUS(us);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        
        return us.getId();
    }
    
    private static int readInteger(Terminal t, String msg, List<Integer> ints) {
        int entryInt = 0;
        System.out.println(String.format(msg, String.join(", ", ints.toArray(new String[0]))));
        
        String entry = t.readLine();
        
        try {
            entryInt = Integer.parseInt(entry);
            if (!ints.contains(entryInt)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Bitte geben sie eine gültige Zahl ein!");
            readInteger(t, msg, ints);
        }
        
        return entryInt;
    }
    
    private static String readText(Terminal t, String msg) {
        System.out.println(msg);
        return t.readLine();
    }
    
    public static void againUSEnter() throws IOException {
        System.out.println("Möchten sie noch eine Userstory eingeben? [Y|N]");
        
        try (Terminal t = new Terminal()) {
            switch (t.readLine().toLowerCase()) {
                case "y": {
                    Container.getContainer().getCommand("enter").execute(null);
                    break;
                }
                case "n": {
                    System.out.println("Sie befinden sich wieder im Menü. Für Hilfe zu den Befehlen geben sie bitte " + "\"" + "help" + "\"" + " ein");
                    break;
                }
                default: {
                    System.out.println("Bitte geben sie " + "\"" + "y" + "\"" + " für Ja, oder " + "\"" + "n" + "\"" + " für Nein ein.");
                    break;
                }
            }
        }
    }
    
    public static String addActor(String[] params) {
        if (params.length == 3) {
            if (params[0].equals("-") && params[1].equals("actor")) {
                if (Container.getContainer().containsActor(params[2])) {
                    System.out.println(String.format("Der Akteur %s wurde im System registriert!", params[2]));
                    return Container.getContainer().addActor(params[2]);
                }
            }
        }
        return null;
    }
}

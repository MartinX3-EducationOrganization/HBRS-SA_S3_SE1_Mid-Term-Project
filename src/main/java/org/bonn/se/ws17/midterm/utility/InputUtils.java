package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.view.Terminal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtils {
    public static String eingabe() {
        UserStory us = new UserStory();
    
        us.setTitel(InputUtils.readText("Geben Sie zunächst einen Titel für ihrer Userstory ein:"));
    
        us.setActor(InputUtils.readText("Aus welcher Sicht ist die Userstory verfasst? (Akteur)"));
    
        us.setBeschreibung(InputUtils.readText("Geben Sie eine kurze Beschreibung ihrer Userstory ein:"));
    
        us.setDetails(InputUtils.readText("Geben Sie jetzt ihre Details zur Userstory ein:"));
    
        us.setAkzeptanz(InputUtils.readText("Es folgen die Akzeptanzkriterien ihrer Userstory:"));
    
        us.setEpic(InputUtils.readText("Unter welcher Epic lässt sich ihre Userstory einordnen?"));
    
        us.setMehrwert(InputUtils.readText("Wie schätzen sie den Mehrwert ein? (schriftlich)"));
    
        us.setMwert(InputUtils.readInteger("Welchen Mehrwert schätzen Sie ? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
    
        us.setStrafe(InputUtils.readInteger("Welchen Wert hat die Strafe? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
    
        us.setRisiko(InputUtils.readInteger("Wie hoch schätzen Sie das Risiko ein? (%s)", Arrays.asList(1, 2, 3, 4, 5)));
    
        us.setAufwand(InputUtils.readInteger("Wie hoch dürfte der Aufwand sein? (%s)", CalcUtils.getFibonacciZahlen()));
        
        try {
            Container.getContainer().addUS(us);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        
        return us.getId();
    }
    
    private static int readInteger(String msg, List<Integer> ints) {
        int entryInt = 0;
        System.out.println(String.format(msg, String.join(", ", ints.stream().map(Object::toString).collect(Collectors.toList()))));
        
        String entry = Terminal.readLine();
        
        try {
            entryInt = Integer.parseInt(entry);
            if (!ints.contains(entryInt)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Bitte geben sie eine gültige Zahl ein!");
            InputUtils.readInteger(msg, ints);
        }
        
        return entryInt;
    }
    
    private static String readText(String msg) {
        System.out.println(msg);
        return Terminal.readLine();
    }
    
    public static void againUSEnter() {
        System.out.println("Möchten sie noch eine Userstory eingeben? [Y|N]");
    
        switch (Terminal.readLine().toLowerCase()) {
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
                InputUtils.againUSEnter();
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

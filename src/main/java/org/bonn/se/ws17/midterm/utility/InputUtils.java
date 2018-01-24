package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;

import java.util.Scanner;

public class InputUtils {
    private static String titel;
    private static String beschreibung;
    private static String mehrwert;
    private static String actor;
    private static String details;
    private static String acceptCrit;
    private static String epic;
    private static int mwert;
    private static int strafe;
    private static int risiko;
    private static int aufwand;
    
    public static String eingabe() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben Sie zunächst einen Titel für ihrer Userstory ein:");
        InputUtils.titel = sc.nextLine();
        System.out.println("Aus welcher Sicht ist die Userstory verfasst? (Akteur)");
        InputUtils.actor = sc.nextLine();
        System.out.println("Geben Sie eine kurze Beschreibung ihrer Userstory ein:");
        InputUtils.beschreibung = sc.nextLine();
        System.out.println("Geben Sie jetzt ihre Details zur Userstory ein:");
        InputUtils.details = sc.nextLine();
        System.out.println("Es folgen die Akzeptanzkriterien ihrer Userstory:");
        InputUtils.acceptCrit = sc.nextLine();
        System.out.println("Unter welcher Epic lässt sich ihre Userstory einordnen?");
        InputUtils.epic = sc.nextLine();
        System.out.println("Wie schätzen sie den Mehrwert ein? (schriftlich)");
        InputUtils.mehrwert = sc.nextLine();
        System.out.println("Welchen Mehrwert schätzen Sie ? (value 1-5)");
        InputUtils.mwert = InputUtils.checker(sc, "Mehrwert");
        System.out.println("Welchen Wert hat die Strafe? (1-5)");
        InputUtils.strafe = InputUtils.checker(sc, "Strafe");
        System.out.println("Wie hoch schätzen Sie das Risiko ein? (1-5)");
        InputUtils.risiko = InputUtils.checker(sc, "Risiko");
        System.out.println("Wie hoch dürfte der Aufwand sein?");
        InputUtils.aufwand = InputUtils.checker(sc, "Aufwand");
    
        UserStory us = new UserStory(InputUtils.titel, InputUtils.beschreibung, InputUtils.details, InputUtils.acceptCrit, InputUtils.mehrwert, InputUtils.epic, InputUtils.mwert, InputUtils.strafe, InputUtils.risiko, InputUtils.aufwand, InputUtils.actor);
        try {
            Container.getContainer().addUS(us);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        
        return us.getId();
    }
    
    private static int checker(Scanner sc, String s) {
        while (!sc.hasNextInt()) {
            System.out.println("Bitte geben sie keine Zeichen ein!");
            System.out.println("Geben sie einen Zahlenwert für " + s + " ein.");
            sc.next();
        }
    
        int a = sc.nextInt();
    
        if (s.equals("Aufwand") && CalcUtils.checkFibonacci(a)) {
            return a;
        } else if (s.equals("Aufwand")) {
            System.out.println("Die Zahl " + a + " ist keine Fibonacci-Zahl." + "\n" + "Bitte korrigieren sie ihre Eingabe.");
            return (InputUtils.checker(sc, s));
        } else if (a > 5 || a < 1) {
            System.out.println("Bitte korriegieren Sie ihre Eingabe zu: " + a + " (Nur Werte von 1-5)");
            return InputUtils.checker(sc, s);
        } else {
            return a;
        }
    }
    
    public static void again() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Möchten sie noch eine Userstory eingeben?");
        while (sc.hasNextLine()) {
            if (sc.nextLine().toUpperCase().equals("Y")) {
                System.out.println("ja ANKLICKT");
                //TODO
            } else if (sc.nextLine().toUpperCase().equals("N")) {
                System.out.println("NEIN ANKLICKT");
                break;
            } else {
                System.out.println("Bitte geben sie " + "\"" + "y" + "\"" + " für Ja, oder " + "\"" + "n" + "\"" + " für Nein ein.");
            }
        }
    }
}

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
        titel = sc.nextLine();
        System.out.println("Aus welcher Sicht ist die Userstory verfasst? (Akteur)");
        actor = sc.nextLine();
        System.out.println("Geben Sie eine kurze Beschreibung ihrer Userstory ein:");
        beschreibung = sc.nextLine();
        System.out.println("Geben Sie jetzt ihre Details zur Userstory ein:");
        details = sc.nextLine();
        System.out.println("Es folgen die Akzeptanzkriterien ihrer Userstory:");
        acceptCrit = sc.nextLine();
        System.out.println("Unter welcher Epic lässt sich ihre Userstory einordnen?");
        epic = sc.nextLine();
        System.out.println("Wie schätzen sie den Mehrwert ein? (schriftlich)");
        mehrwert = sc.nextLine();
        System.out.println("Welchen Mehrwert schätzen Sie ? (value 1-5)");
        mwert = checker(sc, "Mehrwert");
        System.out.println("Welchen Wert hat die Strafe? (1-5)");
        strafe = checker(sc, "Strafe");
        System.out.println("Wie hoch schätzen Sie das Risiko ein? (1-5)");
        risiko = checker(sc, "Risiko");
        System.out.println("Wie hoch dürfte der Aufwand sein?");
        aufwand = checker(sc, "Aufwand");
    
        UserStory us = new UserStory(titel, beschreibung, details, acceptCrit, mehrwert, epic, mwert, strafe, risiko, aufwand, actor);
        try {
            Container.getContainer().add(us);
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
            return (checker(sc, s));
        } else if (a > 5 || a < 1) {
            System.out.println("Bitte korriegieren Sie ihre Eingabe zu: " + a + " (Nur Werte von 1-5)");
            return checker(sc, s);
        } else {
            return a;
        }
    }
}

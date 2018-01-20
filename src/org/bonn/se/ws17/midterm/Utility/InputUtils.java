package org.bonn.se.ws17.midterm.Utility;

import org.bonn.se.ws17.midterm.Entity.UserStory;
import org.bonn.se.ws17.midterm.Exception.ContainerException;
import org.bonn.se.ws17.midterm.Model.Container;

import java.util.Scanner;

public class InputUtils {
    private static String beschreibung;
    private static String details;
    private static String acceptCrit;
    private static int mehrwert;
    private static int strafe;
    private static int risiko;
    private static int aufwand;
    private static String epic;
    private static String titel;
    
    public static void eingabe() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben Sie zunächst einen Titel für ihrer Userstory ein:");
        titel = sc.nextLine();
        System.out.println("Geben Sie eine kurze Beschreibung ihrer Userstory ein:");
        beschreibung = sc.nextLine();
        System.out.println("Geben Sie jetzt ihre Details zur Userstory ein:");
        details = sc.nextLine();
        System.out.println("Es folgen die Akzeptanzkriterien ihrer Userstory:");
        acceptCrit = sc.nextLine();
        System.out.println("Unter welcher Epic lässt sich ihre Userstory einordnen?");
        epic = sc.nextLine();
        System.out.println("Welchen Mehrwert erwarten Sie? (1-5)");
        mehrwert = checker(sc, "Mehrwert");
        System.out.println("Welchen Wert hat die Strafe? (1-5)");
        strafe = checker(sc, "Strafe");
        System.out.println("Wie hoch schätzen Sie das Risiko ein? (1-5)");
        risiko = checker(sc, "Risiko");
        System.out.println("Wie hoch dürfte der Aufwand sein?");
        aufwand = checker(sc, "Aufwand");
        UserStory us = new UserStory(titel, beschreibung, details, acceptCrit, epic, mehrwert, strafe, risiko, aufwand);
        try {
            Container.getContainer().add(us);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        System.out.println("Möchten sie noch eine User-Story eingeben?(y/n)");
        while (sc.hasNext()) {
            String a = sc.next();
            if (a.toUpperCase().equals("N")) {
                break;
            } else if (a.toUpperCase().equals("Y")) {
                eingabe();
                break;
            }
        }
    }
    
    private static int checker(Scanner sc, String s) {
        
        while (!sc.hasNextInt()) {
            System.out.println("Bitte geben sie keine Zeichen ein!");
            System.out.println("Geben sie einen Zahlenwert für " + s + " ein.");
            sc.next();
        }
        int a = sc.nextInt();
        if (s.equals("Aufwand") && checkFibonacci(a)) {
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
    
    public static boolean checkFibonacci(int aufwandInt) {
        int[] fibonacciZahlen = new int[]{1, 2, 3, 5, 8, 13, 20, 35, 50};
        for (int i = 0; i < fibonacciZahlen.length; i++) {
            if (aufwandInt == fibonacciZahlen[i]) {
                return true;
            }
        }
        return false;
    }
}

package org.bonn.se.ws17.midterm;

import java.util.Scanner;

public class EingabeUtil {
    private static String beschreibung;
    private static String details;
    private static String acceptCrit;
    private static int mehrwert;
    private static int strafe;
    private static int risiko;
    private static int aufwand;
    private static String epic;
    
    public static UserStory eingabe() {
        Scanner sc = new Scanner(System.in);
        System.out.print("[]Willkommen in der USER-Story Eingabe[]\nUm eine User-Story einzugeben, folgen sie den Anweisungen.\n" +
                "Geben Sie zunächste eine kurze Beschreibung ihrer Userstory ein:\n");
        beschreibung = sc.nextLine();
        System.out.println("Geben Sie jetzt bitte ihre Details zur Userstory ein:");
        details = sc.nextLine();
        System.out.println("Es folgen die Akzeptanzkriterien ihrer Userstory:");
        acceptCrit = sc.nextLine();
        System.out.println("Unter welcher Epic lässt sich ihre Userstory einordnen?");
        epic = sc.nextLine();
        System.out.println("Welchen Mehrwert erwarten Sie? (1-5)");
        mehrwert = sc.nextInt();
        System.out.println("Welchen Wert hat die Strafe? (1-5)");
        strafe = sc.nextInt();
        System.out.println("Wie hoch schätzen Sie das Risiko ein? (1-5)");
        risiko = sc.nextInt();
        System.out.println("Zu guter letzt wie hoch dürfte der Aufwand sein? (1-5)");
        aufwand = sc.nextInt();
        return new UserStory(beschreibung, details, acceptCrit, mehrwert, strafe, risiko, aufwand, epic);
    }
}

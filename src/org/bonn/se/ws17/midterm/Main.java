package org.bonn.se.ws17.midterm;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean input = true;
        List<UserStory> UserStories = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("[]Willkommen in der USER-Story Eingabe[]\nUm eine User-Story einzugeben, folgen sie bitte den Anweisungen.");
        while (input) {
            try {
                UserStories.add(EingabeUtil.eingabe());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Möchten sie noch eine User-Story eingeben?(y/n)");
            while (sc.hasNext()) {
                String a = sc.next();
                if (a.equals("n") || a.equals("N")) {
                    input = false;
                    break;
                } else if (a.equals("y") || a.equals("Y")) {
                    input = true;
                    break;
                }
            }
        }
        sc.close();
        System.out.println("Alle User-Stories:" + "\n");
        for (int i = 0; i < UserStories.toArray().length; i++) {
            System.out.println(UserStories.get(i));
            if (i < (UserStories.toArray().length - 1)) {
                System.out.println("---------------------------------------------------------");
            }
        }
    
    }
}

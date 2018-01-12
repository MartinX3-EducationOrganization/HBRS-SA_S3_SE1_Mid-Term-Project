package org.bonn.se.ws17.midterm;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean input = true;
        List<UserStory> UserStories = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (input) {
            UserStories.add(EingabeUtil.eingabe());
            System.out.println("Möchten sie noch eine User-Story eingeben?(y/n)");
            String a = sc.next();
            if (a.equals("n") || a.equals("N")) {
                input = false;
            } else if (a.equals("y") || a.equals("Y")) {
            }
        
        }
        sc.close();
        System.out.println("Alle User-Stories:");
        System.out.println(UserStories.toString());
    }
}

package org.bonn.se.ws17.midterm.analyze;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

public class Analyze {
    
    public static int malus(UserStory us) {
        return missing(us) + bandwurmMalus(us) + bekannterActor(us);
    }
    
    /**
     * Punkteabzug für fehlende Inhalte bzw. wenn die diese zu befüllt sind-(zu viel Text Abzug 5)
     */
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("")) {
            malus += 5;
        } else if (countWords(us.getTitel()) > 3) {malus += 5;}
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        } else if (countWords(us.getBeschreibung()) > 50) {malus += 5;}
        if (us.getDetails().equals("")) {
            malus += 15;
        } else if (countWords(us.getDetails()) > 30) {malus += 5;}
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        } else if (countWords(us.getAkzeptanz()) > 30) {malus += 5;}
        if (us.getMehrwert().equals("")) {
            malus += 15;
        } else if (countWords(us.getMehrwert()) > 30) {malus += 5;}
        if (us.getEpic().equals("")) {
            malus += 5;
        } else if (countWords(us.getEpic()) > 3) {malus += 5;}
        if (us.getActor().equals("")) {
            malus += 10;
        } else if (countWords(us.getActor()) > 2) {malus += 5;}
        return malus;
    }
    
    /**
     * Wenn in doppelt soviele Kommas "," vorkommen als Punkte gibt es Abzüge. One Liner wie Epic oder Titel sind ausser
     * acht zu lassen.
     */
    private static int bandwurmMalus(UserStory us) {
        int malus = 0; // max -15
        if (bandcounter(us.getBeschreibung(), ',') > 2 * (bandcounter(us.getBeschreibung(), '.'))) {
            malus += 5;
        }
        if (bandcounter(us.getDetails(), ',') > 2 * (bandcounter(us.getDetails(), '.'))) {
            malus += 5;
        }
        if (bandcounter(us.getMehrwert(), ',') > 2 * (bandcounter(us.getMehrwert(), '.'))) {
            malus += 5;
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
    
    private static int bekannterActor(UserStory us) {
        if (Container.getContainer().getActors().contains(us.getActor())) {
            return 0;
        } else {
            return 10;
        }
    }
    
    public static int countWords(String s) {
        String[] count_words = s.split(" ");
        return count_words.length;
    }
}

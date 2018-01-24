package org.bonn.se.ws17.midterm.analyze;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

public class Analyze {
    
    public static int malus(UserStory us) {
        return Analyze.missing(us) + Analyze.bandwurmMalus(us);
    }
    
    /**
     * Punkteabzug für fehlende Inhalte oder zu lange Texte.
     */
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("") || Analyze.countWords(us.getTitel()) > 3) {
            malus += 5;
        }
        
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        } else if (Analyze.countWords(us.getBeschreibung()) > 50) {
            malus += 5;
        }
        
        if (us.getDetails().equals("")) {
            malus += 15;
        } else if (Analyze.countWords(us.getDetails()) > 30) {
            malus += 5;
        }
        
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        } else if (Analyze.countWords(us.getAkzeptanz()) > 30) {
            malus += 5;
        }
        
        if (us.getMehrwert().equals("")) {
            malus += 15;
        } else if (Analyze.countWords(us.getMehrwert()) > 30) {
            malus += 5;
        }
    
        if (us.getEpic().equals("") || Analyze.countWords(us.getEpic()) > 3) {
            malus += 5;
        }
    
        if (us.getActor().equals("") || !Container.getContainer().containsActor(us.getActor())) {
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
    
            if (us.getBeschreibung().length() < 1 || Analyze.bandcounter(us.getBeschreibung(), ',') > (2 * Analyze.bandcounter(us.getBeschreibung(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getDetails() != null) {
            if (us.getDetails().length() < 1 || Analyze.bandcounter(us.getDetails(), ',') > (2 * Analyze.bandcounter(us.getDetails(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getMehrwert() != null) {
            if (us.getMehrwert().length() < 1 || Analyze.bandcounter(us.getMehrwert(), ',') > (2 * Analyze.bandcounter(us.getMehrwert(), '.'))) {
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
    
    public static int countWords(String s) {
        return s.split(" ").length;
    }
}
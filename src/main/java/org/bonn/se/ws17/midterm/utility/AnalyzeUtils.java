package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

public class AnalyzeUtils {
    
    public static int malus(UserStory us) {
        return AnalyzeUtils.missing(us) + AnalyzeUtils.bandwurmMalus(us);
    }
    
    /**
     * Punkteabzug für fehlende Inhalte oder zu lange Texte.
     */
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("") || AnalyzeUtils.countWords(us.getTitel()) > 3) {
            malus += 5;
        }
        
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        } else if (AnalyzeUtils.countWords(us.getBeschreibung()) > 50) {
            malus += 5;
        }
        
        if (us.getDetails().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getDetails()) > 30) {
            malus += 5;
        }
        
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getAkzeptanz()) > 30) {
            malus += 5;
        }
        
        if (us.getMehrwert().equals("")) {
            malus += 15;
        } else if (AnalyzeUtils.countWords(us.getMehrwert()) > 30) {
            malus += 5;
        }
    
        if (us.getEpic().equals("") || AnalyzeUtils.countWords(us.getEpic()) > 3) {
            malus += 5;
        }
    
        if (us.getActor().equals("") || Container.getContainer().containsActor(us.getActor())) {
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
    
            if (us.getBeschreibung().length() < 1 || AnalyzeUtils.bandcounter(us.getBeschreibung(), ',') > (2 * AnalyzeUtils.bandcounter(us.getBeschreibung(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getDetails() != null) {
            if (us.getDetails().length() < 1 || AnalyzeUtils.bandcounter(us.getDetails(), ',') > (2 * AnalyzeUtils.bandcounter(us.getDetails(), '.'))) {
                malus += 5;
            }
        }
    
        if (us.getMehrwert() != null) {
            if (us.getMehrwert().length() < 1 || AnalyzeUtils.bandcounter(us.getMehrwert(), ',') > (2 * AnalyzeUtils.bandcounter(us.getMehrwert(), '.'))) {
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
    
    private static int countWords(String s) {
        return s.split(" ").length;
    }
}
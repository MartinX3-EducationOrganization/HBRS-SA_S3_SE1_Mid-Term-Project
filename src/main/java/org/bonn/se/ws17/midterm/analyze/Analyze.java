package org.bonn.se.ws17.midterm.analyze;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

public class Analyze {
    
    public static int malus(UserStory us) {
        return missing(us) + bandwurmMalus(us) + bekannterActor(us);
    }
    
    /**
     * Punkteabzug für fehlende Inhalte oder zu lange Texte. (zu viel Text Abzug 5)
     */
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("") || countWords(us.getTitel()) > 3) {
            malus += 5;
        }
        
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        } else if (countWords(us.getBeschreibung()) > 50) {
            malus += 5;
        }
        
        if (us.getDetails().equals("")) {
            malus += 15;
        } else if (countWords(us.getDetails()) > 30) {
            malus += 5;
        }
        
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        } else if (countWords(us.getAkzeptanz()) > 30) {
            malus += 5;
        }
        
        if (us.getMehrwert().equals("")) {
            malus += 15;
        } else if (countWords(us.getMehrwert()) > 30) {
            malus += 5;
        }
    
        if (us.getEpic().equals("") || countWords(us.getEpic()) > 3) {
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
            if (us.getBeschreibung().length() < 1 || bandcounter(us.getBeschreibung(), ",") > (2 * bandcounter(us.getBeschreibung(), "."))) {
                malus += 5;
            }
        }
    
        if (us.getDetails() != null) {
            if (us.getDetails().length() < 1 || bandcounter(us.getDetails(), ",") > (2 * bandcounter(us.getDetails(), "."))) {
                malus += 5;
            }
        }
    
        if (us.getMehrwert() != null) {
            if (us.getMehrwert().length() < 1 || bandcounter(us.getMehrwert(), ",") > (2 * bandcounter(us.getMehrwert(), "."))) {
                malus += 5;
            }
        }
    
        return malus;
    }
    
    private static int bandcounter(String s, String sign) {
        return s.split(sign).length - 1;
    }
    
    private static int bekannterActor(UserStory us) {
        return Container.getContainer().containsActor(us.getActor()) ? 0 : 10;
    }
    
    public static int countWords(String s) {
        return s.split(" ").length;
    }
}

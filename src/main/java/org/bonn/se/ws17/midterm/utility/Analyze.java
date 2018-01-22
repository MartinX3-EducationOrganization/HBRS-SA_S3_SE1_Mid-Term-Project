package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;

public class Analyze {
    
    public static int malus(UserStory us) {
        return missing(us) + bandwurmMalus(us) + bekannterActor(us);
    }
    
    private static int missing(UserStory us) {
        int malus = 0; // max -75pkt
        if (us.getTitel().equals("")) {
            malus += 5;
        }
        if (us.getBeschreibung().equals("")) {
            malus += 10;
        }
        if (us.getDetails().equals("")) {
            malus += 15;
        }
        if (us.getAkzeptanz().equals("")) {
            malus += 15;
        }
        if (us.getMehrwert().equals("")) {
            malus += 15;
        }
        if (us.getEpic().equals("")) {
            malus += 5;
        }
        if (us.getActor().equals("")) {
            malus += 10;
        }
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
        if (Container.getContainer().getActorList().isEmpty()) {
            System.out.println("Liste Leer");
        }
        if (Container.getContainer().getActorList().contains(us.getActor())) {
            return 0;
        } else {
            return 10;
        }
    }
}

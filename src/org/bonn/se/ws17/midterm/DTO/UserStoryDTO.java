package org.bonn.se.ws17.midterm.DTO;

import org.bonn.se.ws17.midterm.Entity.UserStory;
import org.bonn.se.ws17.midterm.Utility.CalcUtils;

import java.util.UUID;

public class UserStoryDTO implements Comparable<UserStoryDTO> {
    private final UUID id;
    private final String titel;
    private final double prioritaet;
    
    public UserStoryDTO(UserStory us) {
        id = us.getId();
        titel = us.getTitel();
        prioritaet = CalcUtils.calcPrio(us.getMehrwert(), us.getStrafe(), us.getRisiko(), us.getAufwand());
    }
    
    public UUID getId() { return id; }
    
    public String getTitel() { return titel; }
    
    public double getPrioritaet() { return prioritaet; }
    
    @Override
    public int compareTo(UserStoryDTO o) {
        if (prioritaet == o.prioritaet) {
            return 0;
        } else if (prioritaet < o.prioritaet) {
            return 1;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return "UserStoryID: " + id + "\n" + "Titel: " + titel + "\n" + "Priorität: " + prioritaet;
    }
}

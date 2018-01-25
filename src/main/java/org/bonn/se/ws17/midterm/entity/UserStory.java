package org.bonn.se.ws17.midterm.entity;

import org.bonn.se.ws17.midterm.utility.CalcUtils;

import java.io.Serializable;
import java.util.UUID;

public class UserStory implements Serializable, Comparable<UserStory> {
    private final String id = UUID.randomUUID().toString();
    private String titel, beschreibung, details, akzeptanz, epic, actor, mehrwert;
    private int mwert, strafe, risiko; // 1, 2, 3, 4, 5
    private int aufwand;    // 1, 2, 3, 5, 8, 13, 20, 35, 50
    private boolean done = false;
    
    public UserStory() {
    }
    
    public String getMehrwert() { return mehrwert; }
    
    public void setMehrwert(String mehrwert) { this.mehrwert = mehrwert; }
    
    public String getActor() { return actor; }
    
    public void setActor(String actor) { this.actor = actor; }
    
    public String getBeschreibung() { return beschreibung; }
    
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    
    public String getId() { return id; }
    
    public String getTitel() { return titel; }
    
    public void setTitel(String titel) { this.titel = titel; }
    
    public String getDetails() { return details; }
    
    public void setDetails(String details) { this.details = details; }
    
    public String getAkzeptanz() {
        return akzeptanz;
    }
    
    public void setAkzeptanz(String akzeptanz) { this.akzeptanz = akzeptanz; }
    
    public String getEpic() { return epic; }
    
    public void setEpic(String e) { epic = e; }
    
    public int getMwert() { return mwert; }
    
    public void setMwert(int m) throws Exception {
        mwert = m;
    }
    
    public boolean isDone() { return done; }
    
    public void setDone(boolean w) { done = w; }
    
    public int getStrafe() { return strafe; }
    
    public void setStrafe(int s) {
        strafe = s;
    }
    
    public int getRisiko() { return risiko; }
    
    public void setRisiko(int r) {
        risiko = r;
    }
    
    public int getAufwand() { return aufwand; }
    
    public void setAufwand(int a) { aufwand = a; }
    
    public double getPrioritaet() { return CalcUtils.calcPrio(mwert, strafe, risiko, aufwand); }
    
    @Override
    public int compareTo(UserStory o) {
        if (getPrioritaet() == o.getPrioritaet()) {
            return 0;
        } else if (getPrioritaet() < o.getPrioritaet()) {
            return 1;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return "UserStoryID: " + id + "\n" + "Titel: " + titel + "\n" + "Priorität: " + getPrioritaet();
    }
}

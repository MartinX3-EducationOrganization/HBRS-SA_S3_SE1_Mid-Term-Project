package org.bonn.se.ws17.midterm;

import java.io.Serializable;
import java.util.UUID;

public class UserStory implements Serializable, Comparable<UserStory> {
    
    private final UUID id;
    private final double prioritaet;
    private String description;
    private String details;
    private String akzeptanz;
    private int mehrwert, strafe, risiko, aufwand;    // 1-5
    private String epic;
    private boolean done;
    
    UserStory(String description, String details, String akzeptanz, String epic, int mehrwert, int strafe, int risiko, int aufwand) throws Exception {
        setDescription(description);
        setDetails(details);
        setAkzeptanz(akzeptanz);
        setEpic(epic);
        setMehrwert(mehrwert);
        setStrafe(strafe);
        setRisiko(risiko);
        setAufwand(aufwand);
        id = UUID.randomUUID();
        prioritaet = getPrio();
        done = false;
    }
    
    public UUID getId() { return id; }
    public String getDescription() { return description; }
    private void setDescription(String description) { this.description = description; }
    public String getDetails() { return details; }
    private void setDetails(String details) { this.details = details; }
    public String getAkzeptanz() {
        return akzeptanz;
    }
    private void setAkzeptanz(String akzeptanz) { this.akzeptanz = akzeptanz; }
    public String getEpic() { return epic; }
    private void setEpic(String e) { epic = e; }
    public int getMehrwert() { return mehrwert; }
    private void setMehrwert(int m) throws Exception {
        if (m < 1 || m > 5) {
            throw new Exception("Der Mehrwert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        mehrwert = m;
    }
    
    public boolean isDone() { return done; }
    public void setDone(boolean w) { done = w; }
    public int getStrafe() { return strafe; }
    private void setStrafe(int s) throws Exception {
        if (s < 1 || s > 5) {
            throw new Exception("Der Strafe-Wert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        strafe = s;
    }

    public int getRisiko() { return risiko; }
    private void setRisiko(int r) throws Exception {
        if (r < 1 || r > 5) {
            throw new Exception("Das Risiko liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        risiko = r;
    }
    
    public int getAufwand() { return aufwand; }
    private void setAufwand(int a) { aufwand = a; }
    private double getPrio() { return CalcUtils.calcPrio(mehrwert, strafe, risiko, aufwand); }

    @Override
    public String toString() {
        return "UserStoryID: " + id + "\n" + "Priorität: " + prioritaet + "\n" + "Beschreibung: " + description + "\n" + "Details: " + details + "\n" + "Akzeptanzkriterien: " + akzeptanz + "\n" + "Epic: " + epic + "\n" + "Mehrwert: " + mehrwert + "\n" + "Strafe: "
                + strafe + "\n" + "Risiko: " + risiko + "\n" + "Aufwand: " + aufwand;
    }
    
    @Override
    public int compareTo(UserStory o) {
        if (prioritaet == o.prioritaet) {
            return 0;
        } else if (prioritaet < o.prioritaet) {
            return 1;
        }
        return -1;
    }
}

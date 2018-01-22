package org.bonn.se.ws17.midterm.entity;

import org.bonn.se.ws17.midterm.utility.CalcUtils;

import java.io.Serializable;
import java.util.UUID;

public class UserStory implements Serializable, Comparable<UserStory> {
    
    private final double prioritaet;
    private final UUID id;
    private String titel, beschreibung, details, akzeptanz, epic, actor, mehrwert;
    private int mwert, strafe, risiko, aufwand;    // 1-5
    private boolean done;
    
    public UserStory(String titel, String beschreibung, String details, String akzeptanz, String mehrwert, String epic, int mwert, int strafe, int risiko, int aufwand, String actor) throws Exception {
        setTitel(titel);
        setBeschreibung(beschreibung);
        setDetails(details);
        setAkzeptanz(akzeptanz);
        setMehrwert(mehrwert);
        setEpic(epic);
        setMwert(mwert);
        setStrafe(strafe);
        setRisiko(risiko);
        setAufwand(aufwand);
        setActor(actor);
        id = UUID.randomUUID();
        prioritaet = getPrio();
        done = false;
    }

    public String getMehrwert() { return mehrwert; }
    
    private void setMehrwert(String mehrwert) { this.mehrwert = mehrwert; }
    
    public String getActor() { return actor; }
    
    private void setActor(String actor) { this.actor = actor; }
    
    public String getBeschreibung() { return beschreibung; }
    
    private void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    
    public UUID getId() { return id; }
    
    
    public String getTitel() { return titel; }
    
    private void setTitel(String titel) { this.titel = titel; }
    
    public String getDetails() { return details; }
    
    private void setDetails(String details) { this.details = details; }
    
    public String getAkzeptanz() {
        return akzeptanz;
    }
    
    private void setAkzeptanz(String akzeptanz) { this.akzeptanz = akzeptanz; }
    
    public String getEpic() { return epic; }
    
    private void setEpic(String e) { epic = e; }
    
    public int getMwert() { return mwert; }
    
    private void setMwert(int m) throws Exception {
        if (m < 1 || m > 5) {
            throw new Exception("Der Mehrwert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        mwert = m;
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
    
    private double getPrio() { return CalcUtils.calcPrio(mwert, strafe, risiko, aufwand); }
    
    @Override
    public int compareTo(UserStory o) {
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

package org.bonn.se.ws17.midterm.dto;


public class UserStoryDTOInput {
    private String id;
    private double prioritaet;
    private String titel;
    private String beschreibung;
    private String details;
    private String akzeptanz;
    private String epic;
    private String actor;
    private String mehrwert;
    private int mwert;
    private int strafe;
    private int risiko;
    private int aufwand;
    private boolean done;
    
    public UserStoryDTOInput(String id, String titel, String beschreibung, String details, String akzeptanz, String mehrwert, String epic, int mwert, int strafe, int risiko, int aufwand, String actor, double prioritaet, boolean done) {
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
        setId(id);
        setPrioritaet(prioritaet);
        setDone(done);
    }
    
    public double getPrioritaet() {
        return prioritaet;
    }
    
    public void setPrioritaet(double prioritaet) {
        this.prioritaet = prioritaet;
    }
    
    public String getId() { return id; }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitel() { return titel; }
    
    public void setTitel(String titel) { this.titel = titel; }
    
    public String getBeschreibung() { return beschreibung; }
    
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    
    public String getDetails() { return details; }
    
    public void setDetails(String details) { this.details = details; }
    
    public String getAkzeptanz() { return akzeptanz; }
    
    public void setAkzeptanz(String akzeptanz) { this.akzeptanz = akzeptanz; }
    
    public String getEpic() { return epic; }
    
    public void setEpic(String epic) { this.epic = epic; }
    
    public String getActor() { return actor; }
    
    public void setActor(String actor) { this.actor = actor; }
    
    public String getMehrwert() { return mehrwert; }
    
    public void setMehrwert(String mehrwert) { this.mehrwert = mehrwert; }
    
    public int getMwert() { return mwert; }
    
    public void setMwert(int mwert) { this.mwert = mwert; }
    
    public int getStrafe() { return strafe; }
    
    public void setStrafe(int strafe) { this.strafe = strafe; }
    
    public int getRisiko() { return risiko; }
    
    public void setRisiko(int risiko) { this.risiko = risiko; }
    
    public int getAufwand() { return aufwand; }
    
    public void setAufwand(int aufwand) { this.aufwand = aufwand; }
    
    public boolean isDone() { return done; }
    
    public void setDone(boolean done) { this.done = done; }
    
    private double getPrio() { return prioritaet; }
}

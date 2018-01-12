package org.bonn.se.ws17.midterm;

public class UserStory {

    private String description;
    private String details;
    private String akzeptanz;
    private int mehrwert;   // 1-5
    private int strafe;     // 1-5
    private int risiko;     // 1-5
    private int aufwand;
    private String epic;

    public UserStory(String description, String details, String akzeptanz, int mehrwert, int strafe, int risiko, int aufwand, String epic) {
        this.description = description;
        this.details = details;
        this.akzeptanz = akzeptanz;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        this.aufwand = aufwand;
        this.epic = epic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAkzeptanz() {
        return akzeptanz;
    }

    public void setAkzeptanz(String akzeptanz) {
        this.akzeptanz = akzeptanz;
    }

    public int getMehrwert() {
        return mehrwert;
    }
    
    public void setMehrwert(int m) throws Exception {
        if (m < 1 || m > 5) {
            throw new Exception("Der Mehrwert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        mehrwert = m;
    }

    public int getRisiko() {
        return risiko;
    }
    
    public void setRisiko(int r) throws Exception {
        if (r < 1 || r > 5) {
            throw new Exception("Das Risiko liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        risiko = r;
    }

    public int getStrafe() {
        return strafe;
    }
    
    public void setStrafe(int s) throws Exception {
        if (s < 1 || s > 5) {
            throw new Exception("Der Strafe-Wert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        strafe = s;
    }

    public int getAufwand() {
        return aufwand;
    }
    
    public void setAufwand(int a) {
        aufwand = a;
    }
    
    
    public double getPrio() {
        return Utils.calcPrio(mehrwert, strafe, risiko, aufwand);
    }

    public String getEpic() {
        return epic;
    }
    
    public void setEpic(String e) {
        epic = e;
    }


}

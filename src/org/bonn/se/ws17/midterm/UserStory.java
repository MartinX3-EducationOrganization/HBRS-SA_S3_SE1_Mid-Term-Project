/**
 * @author rjourd2s
 */
package org.bonn.se.ws17.midterm;

public class UserStory {

    private String description;
    private String details;
    private String akzeptanz;
    private int mehrwert;   // 1-5
    private int strafe;     // 1-5
    private int risiko;     // 1-5
    private int aufwand;
    private double prio;
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

    public void setMehrwert(int mehrwert) throws Exception {
        if (mehrwert < 0 || mehrwert > 5) {
            throw new Exception("Der Mehrwert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        this.mehrwert = mehrwert;
    }

    public int getRisiko() {
        return risiko;
    }

    public void setRisiko(int risiko) throws Exception {
        if (risiko < 0 || risiko > 5) {
            throw new Exception("Das Risiko liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        this.risiko = risiko;
    }

    public int getStrafe() {
        return strafe;
    }

    public void setStrafe(int strafe) throws Exception {
        if (strafe < 0 || strafe > 5) {
            throw new Exception("Der Strafe-Wert liegt im Bereich 1-5, bitte korrigieren sie ihre Eingabe.");
        }
        this.strafe = strafe;
    }

    public int getAufwand() {
        return aufwand;
    }

    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }

    /* Fragwürdig.
    public double getPrio() {
        return prio;
    }*/

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }


}

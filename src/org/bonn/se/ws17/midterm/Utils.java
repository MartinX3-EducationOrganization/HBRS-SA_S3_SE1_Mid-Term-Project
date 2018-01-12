package org.bonn.se.ws17.midterm;

public class Utils {
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        double prio;
        double temp;
        prio = mehrwert + strafe;
        temp = aufwand + risiko;
        prio = prio / temp;
        return prio;
    }
}

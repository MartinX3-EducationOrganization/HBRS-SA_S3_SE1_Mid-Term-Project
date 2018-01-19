package org.bonn.se.ws17.midterm;

public class CalcUtils {
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        return ((double) (mehrwert + strafe)) / ((double) (aufwand + risiko));
    }
}

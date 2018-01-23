package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.analyze.Analyze;
import org.bonn.se.ws17.midterm.entity.UserStory;

public class CalcUtils {
    
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        return ((double) (mehrwert + strafe)) / ((double) (aufwand + risiko));
    }
    
    public static boolean checkFibonacci(int aufwandInt) {
        int[] fibonacciZahlen = new int[]{1, 2, 3, 5, 8, 13, 20, 35, 50, 100};
        for (int i = 0; i < fibonacciZahlen.length; i++) {
            if (aufwandInt == fibonacciZahlen[i]) {
                return true;
            }
        }
        return false;
    }
    
    public static int bewertung(UserStory us) {
        return 100 - Analyze.malus(us);
    }
    
    public static String note(int punkte) {
        if (punkte == 100) {
            return "Sehr gut";
        }
        if (punkte >= 50) {
            return "Gut";
        }
        if (punkte >= 25 && punkte < 50) {
            return "Nicht gut";
        }
        return "Schlecht";
    }
}

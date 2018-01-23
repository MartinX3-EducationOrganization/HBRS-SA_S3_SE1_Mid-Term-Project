package org.bonn.se.ws17.midterm.utility;

import org.bonn.se.ws17.midterm.analyze.Analyze;
import org.bonn.se.ws17.midterm.entity.UserStory;

import java.util.Arrays;
import java.util.List;

public class CalcUtils {
    private static final List<Integer> fibonacciZahlen = Arrays.asList(1, 2, 3, 5, 8, 13, 20, 35, 50, 100);
    
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        return ((double) (mehrwert + strafe)) / ((double) (aufwand + risiko));
    }
    
    public static boolean checkFibonacci(int aufwandInt) {
        return fibonacciZahlen.contains(aufwandInt);
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
        if (punkte >= 25) {
            return "Nicht gut";
        }
        return "Schlecht";
    }
}

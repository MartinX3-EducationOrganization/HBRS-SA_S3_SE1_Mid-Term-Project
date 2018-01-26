package org.bonn.se.ws17.midterm.utility;

import java.util.Arrays;
import java.util.List;

public class CalcUtils {
    private static final List<Integer> fibonacciZahlen = Arrays.asList(1, 2, 3, 5, 8, 13, 20, 35, 50);
    
    public static List<Integer> getFibonacciZahlen() {
        return CalcUtils.fibonacciZahlen;
    }
    
    public static double calcPrio(int mehrwert, int strafe, int risiko, int aufwand) {
        return ((double) (mehrwert + strafe)) / ((double) (aufwand + risiko));
    }
}

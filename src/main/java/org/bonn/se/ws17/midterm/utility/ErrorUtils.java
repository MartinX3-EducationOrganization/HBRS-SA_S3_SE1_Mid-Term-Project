package org.bonn.se.ws17.midterm.utility;

public class ErrorUtils {
    public static void cmdNotFound(String cmdKey) {
        System.out.println(String.format("Der Befehl: " + "\"" + "%s" + "\" wird nicht unterstützt!", cmdKey));
    }
}

package org.bonn.se.ws17.midterm.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {
    private static BufferedReader bufInput = null;
    private static InputStreamReader input = null;
    
    private static void init() {
        if (Terminal.bufInput == null && Terminal.input == null) {
            Terminal.input = new InputStreamReader(System.in);
            Terminal.bufInput = new BufferedReader(Terminal.input);
        }
    }
    
    public static String readLine() {
        Terminal.init();
        
        System.out.print("> ");
    
        try {
            return Terminal.bufInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return null;
    }
}

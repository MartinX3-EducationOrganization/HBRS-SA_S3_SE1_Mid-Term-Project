package org.bonn.se.ws17.midterm.view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {
    private BufferedReader input = null;
    
    public Terminal() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }
    
    protected BufferedReader getReader() {
        return input;
    }
    
    public String readLine(String prompt) {
        String strInput = null;
        
        System.out.print(prompt);
        try {
            
            strInput = input.readLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strInput;
    }
}

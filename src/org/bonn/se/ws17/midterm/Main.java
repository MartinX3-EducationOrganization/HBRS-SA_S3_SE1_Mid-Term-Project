package org.bonn.se.ws17.midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        OutputUtils.welcome();
        String strInput = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                strInput = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            String[] strings = strInput.split(" ");
            if (strings[0].equals("help")) {
                OutputUtils.help();
            }
            
            if (strings[0].equals("dump")) {
                OutputUtils.dump(true);
            }
    
            if (strings[0].equals("dumpNotDone")) {
                OutputUtils.dump(false);
    
            }
            if (strings[0].equals("load")) {
                IOUtils.load();
    
            }
            
            if (strings[0].equals("store")) {
                IOUtils.store();
            }
            
            if (strings[0].equals("enter")) {
                try {
                    InputUtils.eingabe();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        
            }
        }
    }
}

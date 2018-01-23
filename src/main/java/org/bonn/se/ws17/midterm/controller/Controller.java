package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.command.Command;
import org.bonn.se.ws17.midterm.command.DumpCommand;
import org.bonn.se.ws17.midterm.command.ExitCommand;
import org.bonn.se.ws17.midterm.utility.IOUtils;
import org.bonn.se.ws17.midterm.utility.InputUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {
    private HashMap<String, Command> commands = null;
    
    public Controller() {
        setupCommands();
    }
    
    private void setupCommands() {
        // alle Commands werden in einer HashMap abgespeichert.
        commands = new HashMap<String,Command>();
        
        // fuenf Commands werden gespeichert (ohne help)
        // Optimierung: Auslesen der Befehle aus den Command-Klasse
        // (hier ausgelassen zur besseren Illustration)
        commands.put("exit", new ExitCommand() );
        commands.put("enter", new EnterUserStoryCommand() );
        commands.put("dump", new DumpCommand() );
        commands.put("store", new StoreCommand() );
        commands.put("load", new LoadCommand() );
    }
    
    public void anfang() {
        String[] commands = {"analyze", "addElement", "help", "dump", "dumpNotDone", "load", "store", "exit", "enter"};
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
            if (strings[0].equals("analyze") && strings[1].equals("-") && strings[2].equals("all")) {
                OutputUtils.analyzeAll();
            }
    
            if (strings[0].equals("analyze") && !strings[1].equals("") && strings[2].equals("-") && !strings[3].isEmpty()) {
                OutputUtils.parameter(strings[1], strings[3]);
            }
    
            if (strings[0].equals("analyze") && !strings[1].equals("")) {
                OutputUtils.analyze(strings[2]);
            }
            if (strings[0].equals("addElement") && strings[1].equals("-") && !strings[2].equals("")) {
                OutputUtils.addActor(strings[2]);
            }
    
    
            if (strings[0].equals("help")) {
                OutputUtils.help();
            }
    
    
            if (strings[0].equals("dump")) {
                dump(true);
            }
            
            if (strings[0].equals("dumpNotDone")) {
                dump(false);
                
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
            if (strings[0].equals("exit")) {
                break;
            }
            if (!Arrays.asList(commands).contains(strings[0])) {
                OutputUtils.wrongInput(strings[0]);
            }
        }
    }
}

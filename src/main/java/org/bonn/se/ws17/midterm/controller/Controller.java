package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.command.*;
import org.bonn.se.ws17.midterm.utility.IOUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Controller {
    private HashMap<String, Command> commands = null;
    
    public Controller() {
        setupCommands();
    }
    
    private void setupCommands() {
        commands = new HashMap<>();
        commands.put("exit", new ExitCommand() );
        commands.put("enter", new EnterCommand());
        commands.put("dump", new DumpCommand() );
        commands.put("dumpNotDone", new DumpNotDoneCommand());
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
    
            if (strings[0].equals("help")) {
                OutputUtils.help();
            }
    
            if (strings[0].equals("load")) {
                IOUtils.load();
                
            }
            
            if (strings[0].equals("store")) {
                IOUtils.store();
            }
    
        }
    
    
    }
}

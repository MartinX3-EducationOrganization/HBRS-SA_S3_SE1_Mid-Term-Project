package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.command.*;
import org.bonn.se.ws17.midterm.utility.OutputUtils;
import org.bonn.se.ws17.midterm.view.Terminal;

import java.util.Arrays;
import java.util.HashMap;



public class Controller {
    private final HashMap<String, Command> commands = null;
    
    public Controller() {
        setupCommands();
    }
    
    private void setupCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("exit", new Exit());
        commands.put("enter", new EnterUS());
        commands.put("dump", new Dump());
        commands.put("store", new Store());
        commands.put("load", new Load());
    }
    
    public void anfang() {
        OutputUtils.welcome();
        String strInput = null;
        Terminal cli = new Terminal();
        while (true) {
            try {
                strInput = cli.readLine("> ");
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            String[] strings = strInput.split(" ");
            if (strings[0].equals("help")) {
                OutputUtils.help();
            } else {
                Command command = commands.get(strings[0]);
                if ((command == null)) {
                    System.out.println(String.format("Der Befehlt nicht unterstuetzt!", strings[0]));
                } else {
                    command.execute(Arrays.copyOfRange(strings, 1, strings.length));
                }
            }
    
    
        }
    }
}
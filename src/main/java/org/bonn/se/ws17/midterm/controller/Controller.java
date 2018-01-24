package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.command.*;
import org.bonn.se.ws17.midterm.utility.OutputUtils;
import org.bonn.se.ws17.midterm.view.Terminal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Controller {
    private HashMap<String, Command> commands = null;
    
    public Controller() {
        setupCommands();
    }
    
    private void setupCommands() {
        commands = new HashMap<>();
        commands.put("exit", new Exit());
        commands.put("enter", new EnterUS());
        commands.put("dump", new Dump());
        commands.put("store", new Store());
        commands.put("load", new Load());
        commands.put("analyze", new Analyze());
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
                System.out.println("Folgende Befehle stehen zur Verfuegung:");
                Set<String> kommandos = commands.keySet();
                Iterator<String> it = kommandos.iterator();
    
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            } else {
                Command command = commands.get(strings[0]);
                if ((command == null)) {
                    System.out.println(String.format("Der Befehl: " + "\"" + "%s" + "\" wird nicht unterstuetzt!", strings[0]));
                } else {
                    command.execute(Arrays.copyOfRange(strings, 1, strings.length));
                }
            }
        }
    }
}
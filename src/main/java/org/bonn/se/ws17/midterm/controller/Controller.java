package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.command.*;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.OutputUtils;
import org.bonn.se.ws17.midterm.view.Terminal;

import java.util.Arrays;


public class Controller {
    public Controller() {
        setupCommands();
    }
    
    private void setupCommands() {
        Container.getContainer().addCommand("exit", new Exit());
        Container.getContainer().addCommand("enter", new EnterUS());
        Container.getContainer().addCommand("dump", new Dump());
        Container.getContainer().addCommand("dumpUndone", new DumpNotDone());
        Container.getContainer().addCommand("store", new Store());
        Container.getContainer().addCommand("load", new Load());
        Container.getContainer().addCommand("analyze", new Analyze());
        Container.getContainer().addCommand("undo", new Undo());
        Container.getContainer().addCommand("help", new Help());
        Container.getContainer().addCommand("addElement", new AddElement());
        Container.getContainer().addCommand("actors", new Actor());
    }
    
    public void anfang() {
        OutputUtils.welcome();
    
        String strInput = null;
    
        Terminal cli = new Terminal();
    
        while (true) {
            try {
                strInput = cli.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            String[] strings;
            if (strInput != null) {
                strings = strInput.split(" ");
    
                if (strings.length > 0) {
                    Container.getContainer().getCommand(strings[0]).execute(Arrays.copyOfRange(strings, 1, strings.length));
                }
            }
        }
    }
}
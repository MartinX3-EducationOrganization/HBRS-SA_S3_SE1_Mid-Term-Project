package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;

import java.util.Set;

public class Help implements Command {
    @Override
    public void execute(String[] params) {
        System.out.println("Folgende Befehle stehen zur Verfuegung:");
        Set<String> commandKeys = Container.getContainer().getCommands();
        
        for (String cmdKey : commandKeys) {
            System.out.println(cmdKey);
        }
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

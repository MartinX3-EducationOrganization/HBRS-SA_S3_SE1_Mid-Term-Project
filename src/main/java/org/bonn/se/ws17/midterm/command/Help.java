package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;

public class Help implements Command {
    @Override
    public void execute(String[] params) {
        System.out.println("Folgende Befehle stehen zur Verfügung:");
    
        for (String cmdKey : Container.getContainer().getCommands()) {
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

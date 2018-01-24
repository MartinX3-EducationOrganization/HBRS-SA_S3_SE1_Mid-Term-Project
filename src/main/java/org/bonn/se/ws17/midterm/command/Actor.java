package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;

import java.util.List;

public class Actor implements Command {
    @Override
    public void execute(String[] params) {
        System.out.println("Folgende Akteure sind eingetragen:");
        List<String> actorList = Container.getContainer().getActors();
        
        for (String actor : actorList) {
            System.out.println(actor);
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

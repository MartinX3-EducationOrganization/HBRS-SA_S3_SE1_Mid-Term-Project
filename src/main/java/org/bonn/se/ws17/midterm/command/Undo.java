package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;

public class Undo implements Command {
    @Override
    public void execute(String[] params) {
        Container.getContainer().undoHistory();
        if (Container.getContainer().undoHistoryEmpty()) {
            System.out.println("nothing to undo");
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

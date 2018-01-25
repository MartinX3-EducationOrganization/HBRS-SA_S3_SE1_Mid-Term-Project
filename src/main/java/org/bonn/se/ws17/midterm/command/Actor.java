package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.OutputUtils;

public class Actor implements Command {
    @Override
    public void execute(String[] params) {
        OutputUtils.listActors();
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

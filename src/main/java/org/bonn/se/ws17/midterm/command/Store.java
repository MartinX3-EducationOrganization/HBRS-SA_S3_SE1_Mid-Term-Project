package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.IOUtils;

public class Store implements Command {
    @Override
    public void execute(String[] params) {
        IOUtils.store();
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

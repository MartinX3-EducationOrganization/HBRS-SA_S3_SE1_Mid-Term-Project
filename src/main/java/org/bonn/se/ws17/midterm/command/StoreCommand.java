package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.IOUtils;

public class StoreCommand implements Command {
    @Override
    public void execute() {
        IOUtils.store();
    }
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public Command clone() throws CloneNotSupportedException {
        return null;
    }
}

package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.IOUtils;

public class Load implements Command {
    @Override
    public void execute(String[] params) {
        IOUtils.load();
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

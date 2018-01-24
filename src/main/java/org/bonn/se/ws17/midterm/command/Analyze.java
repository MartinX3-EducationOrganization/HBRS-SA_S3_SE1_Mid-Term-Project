package org.bonn.se.ws17.midterm.command;

public class Analyze implements Command {
    
    @Override
    public void execute(String[] params) {
        System.out.println(params.toString());
        
    }
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

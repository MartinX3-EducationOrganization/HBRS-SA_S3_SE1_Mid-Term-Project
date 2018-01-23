package org.bonn.se.ws17.midterm.command;


public class ExitCommand implements Command {
    
    @Override
    public void execute() {
        System.out.println("Aufwiedersehen und bis zum naechsten Mal!");
        System.exit(0);
        
    }
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public Command clone() throws CloneNotSupportedException {
        return null;
    }
}

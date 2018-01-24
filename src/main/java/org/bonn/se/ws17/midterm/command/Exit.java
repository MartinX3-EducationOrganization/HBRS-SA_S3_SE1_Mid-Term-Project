package org.bonn.se.ws17.midterm.command;


public class Exit implements Command {
    
    @Override
    public void execute(String[] params) {
        System.out.println("Aufwiedersehen und bis zum naechsten Mal!");
        System.exit(0);
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

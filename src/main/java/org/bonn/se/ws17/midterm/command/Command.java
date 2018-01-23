package org.bonn.se.ws17.midterm.command;

public interface Command {
    void execute(String[] params);
    void undo();
    
    Command clone();
}


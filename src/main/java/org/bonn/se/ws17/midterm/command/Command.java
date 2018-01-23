package org.bonn.se.ws17.midterm.command;

public interface Command {
    void execute();
    void undo();
    
    Command clone() throws CloneNotSupportedException;
}


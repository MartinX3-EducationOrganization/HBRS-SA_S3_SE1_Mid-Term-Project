package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command, Cloneable {
    
    private final List<Commando> reports = new ArrayList();
    
    private Commando command;
    
    @Override
    public void execute() {
    
    }
    
    @Override
    public void undo() {
        Container store = Container.getContainer();
        store.delete(command);
        
    }
    
    @Override
    public Command clone() throws CloneNotSupportedException {
        return null;
    }
    
    @Override
    public void execute(String[] strings) {
        
        int len = strings.length;
        
        String[] inputStrings = new String[len - 1];
        
        for (int i = 0; i < strings.length - 1; i++) {
            inputStrings[i] = strings[i + 1];
        }
        
        Commando command = new Commando();
        
        command.setContent(inputStrings);
        
        Container.getContainer().add(command);
        
        remember(command);
        
    }
    
    public void remember(Commando c) {
        command = c;
    }
}

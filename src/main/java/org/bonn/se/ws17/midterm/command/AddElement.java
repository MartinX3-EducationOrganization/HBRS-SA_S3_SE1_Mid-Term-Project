package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.ErrorUtils;
import org.bonn.se.ws17.midterm.utility.InputUtils;

public class AddElement implements Command {
    private String name;
    
    private void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void execute(String[] params) {
        setName(InputUtils.addActor(params));
    
        if (name != null) {
            Container.getContainer().addHistory(clone());
        } else {
            ErrorUtils.cmdNotFound(String.join(" ", params));
        }
    }
    
    @Override
    public void undo() {
        Container.getContainer().removeActor(name);
    }
    
    @Override
    public Command clone() {
        AddElement cmd = new AddElement();
        cmd.setName(name);
        return cmd;
        
    }
}

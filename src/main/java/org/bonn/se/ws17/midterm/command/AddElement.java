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
        int paralength = params.length;
        if (paralength == 3) {
            if (params[0].equals("-") && params[1].equals("actor")) {
                setName(InputUtils.addActor(params[2]));
                if (name != null) {
                    Container.getContainer().addHistory(clone());
                }
            }
        } else {
            String str = String.join(" ", params);
            ErrorUtils.cmdNotFound(str);
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

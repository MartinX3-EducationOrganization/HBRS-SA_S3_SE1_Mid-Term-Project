package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.ErrorUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;

public class Done implements Command {
    @Override
    public void execute(String[] params) {
        if (!OutputUtils.isUUID(params[0])) {
            ErrorUtils.cmdNotFound(String.join(" ", params));
            return;
        }
        if (Container.getContainer().contains(params[0])) {
            Container.getContainer().get(params[0]).setDone(true);
        } else {
            System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", params[0]));
        }
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

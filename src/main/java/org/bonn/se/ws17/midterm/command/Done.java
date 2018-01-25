package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.ErrorUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;

public class Done implements Command {
    private boolean oldDoneValue;
    private String uuid;
    
    @Override
    public void execute(String[] params) {
        if (!OutputUtils.isUUID(params[0])) {
            ErrorUtils.cmdNotFound(String.join(" ", params));
            return;
        }
        if (Container.getContainer().contains(params[0])) {
            oldDoneValue = Container.getContainer().get(params[0]).getDone();
            uuid = params[0];
            Container.getContainer().get(params[0]).setDone(true);
        } else {
            System.out.println(String.format("Die Userstory mit der ID [%s] wurde nicht gefunden", params[0]));
        }
    }
    
    private void setOldDoneValue(boolean oldDoneValue) {
        this.oldDoneValue = oldDoneValue;
    }
    
    private void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    @Override
    public void undo() {
        if (Container.getContainer().contains(uuid)) {
            Container.getContainer().get(uuid).setDone(oldDoneValue);
        }
    }
    
    @Override
    public Command clone() {
        Done done = new Done();
        done.setUuid(uuid);
        done.setOldDoneValue(oldDoneValue);
        return done;
    }
}

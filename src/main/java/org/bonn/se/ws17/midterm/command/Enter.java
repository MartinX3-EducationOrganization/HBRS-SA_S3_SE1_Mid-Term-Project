package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.InputUtils;

public class Enter implements Command, Cloneable {
    String usid = "";
    
    @Override
    public void undo() {
        Container.getContainer().removeUS(usid);
    }
    
    @Override
    public void execute(String[] strings) {
        try {
            usid = InputUtils.eingabe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

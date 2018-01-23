package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.InputUtils;

public class EnterCommand implements Command, Cloneable {
    private String usid = "";
    
    public void execute(String[] strings) {
        try {
            usid = InputUtils.eingabe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void execute() {}
    
    @Override
    public void undo() {
        Container.getContainer().removeUS(usid);
    }
    
    @Override
    public Command clone() {
        EnterCommand cmd = new EnterCommand();
        cmd.setUsid(usid);
        return cmd;
    }
    
    public void setUsid(String usid) {
        this.usid = usid;
    }
    
}

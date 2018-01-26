package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.AnalyzeUtils;

public class Analyze implements Command {
    @Override
    public void execute(String[] params) {
        AnalyzeUtils.analyze(params);
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

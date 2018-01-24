package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.ErrorUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;

public class Analyze implements Command {
    
    @Override
    public void execute(String[] params) {
        int words = params.length;
        // Wenn nur id
        if (words == 1) {
            if (OutputUtils.isUUID(params[0])) {OutputUtils.analyze(params[0]);}
        }
        // Wenn - all
        if (words == 2) {
            if (params[0].equals("-") && params[1].equals("all")) {OutputUtils.analyzeAll();} else {
                ErrorUtils.cmdNotFound(params[0] + params[1]);
            }
        }
        // Wenn id - details
        if (words == 3) {
            if (OutputUtils.isUUID(params[0])) {
                OutputUtils.analyze(params[0]);
                if (params[1].equals("-") && params[2].equals("details")) {
                    OutputUtils.details(params[0]);
                } else {
                    ErrorUtils.cmdNotFound(params[1] + " " + params[2]);
                }
            }
        }
        // Wenn id - details - hints
        if (words == 5) {
            if (OutputUtils.isUUID(params[0])) {
                OutputUtils.analyze(params[0]);
            
                if (params[1].equals("-") && params[2].equals("details")) {
                    OutputUtils.details(params[0]);
                
                    if (params[3].equals("-") && params[4].equals("hints")) {
                        OutputUtils.hints(params[0]);
                    }
                
                } else {
                    ErrorUtils.cmdNotFound(params[1] + " " + params[2] + " " + params[3] + " " + params[4]);
                }
            }
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

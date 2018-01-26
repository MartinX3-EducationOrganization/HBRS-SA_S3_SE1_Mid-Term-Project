package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.utility.strategyAnalyze.AnalyzeUtils;
import org.bonn.se.ws17.midterm.utility.strategyAnalyze.Strategy;

public class Analyze implements Command {
    @Override
    public void execute(String[] params) {
        Strategy strategie = new Strategy();
        strategie.setStrategy(new AnalyzeUtils());
        strategie.analyze(params);
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}

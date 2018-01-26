package org.bonn.se.ws17.midterm.utility.strategyAnalyze;


public class Strategy {
    private AnalyzeInterface strategy = null;
    private String[] params;
    
    public void setStrategy(AnalyzeInterface STRATEGIE) {
        strategy = STRATEGIE;
    }
    
    public void setParams(String[] params) {
        this.params = params;
    }
    
    public void analyze(String[] params) {
        if (strategy != null) {
            strategy.analyze(params);
        }
    }
}


package strategyAnalysis;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;

import java.util.ArrayList;

/**
 * Implements the Strategy Design Pattern's Context Class Class that is called
 * by the Client/UI to set and execute the strategy
 * 
 * @author Abdul, Jason
 * 
 */
public class AnalysisContext {
    private AnalysisStrategy strategy;

//    private ParsedData data;
    /**
     * 
     * @param strategy to work with
     */
    public AnalysisContext(AnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 
     * @return Processed data
     */
    public ArrayList<StoredData> getData() {
        return DataAcquisition.getDataStorage();
    }

    /**
     * 
     * @param strategy to set to
     */
    public void setStrategy(AnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * process data
     */
    public void execute() {
        strategy.performAnalysis(this);
    }

    public ArrayList<StoredData> getAnalysis() {
        return strategy.getAnalysis();
    }

}
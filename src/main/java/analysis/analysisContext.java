package analysis;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;

import java.util.ArrayList;

/**
 * Implements the Strategy Design Pattern's Context Class
 * Class that is called by the Client/UI to set and execute the strategy
 */
public class analysisContext {
    private analysisStrategy strategy;
    private StoredData data;

    public analysisContext(analysisStrategy strategy){
        this.strategy = strategy;
    }

    // Retrieves the data from the specified metadata
    public ArrayList<StoredData> getData(){
        System.out.println(DataAcquisition.getDataStorage() + " This is from the analysis context class");
        return DataAcquisition.getDataStorage();
    }

    // Sets the strategy
    public void setStrategy(analysisStrategy strategy){
        this.strategy = strategy;
    }

    // Executes the strategy
    public void execute(){
        strategy.performAnalysis(this);
    }
}

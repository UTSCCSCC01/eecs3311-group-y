package analysis;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;

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
    public void getData(){

    }

    public void setStrategy(analysisStrategy strategy){
        this.strategy = strategy;
    }

    // Executes the strategy
//    public analysisStrategy execute(){
//        return strategy.performAnalysis();
//    }
}

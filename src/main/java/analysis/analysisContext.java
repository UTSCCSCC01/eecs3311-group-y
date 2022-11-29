package analysis;

import dataFetch.DataAcquisition;
import dataFetch.ParsedData;
import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements the Strategy Design Pattern's Context Class
 * Class that is called by the Client/UI to set and execute the strategy
 */
public class analysisContext {
    public analysisStrategy strategy;
//    private ParsedData data;

    public analysisContext(analysisStrategy strategy){
        this.strategy = strategy;
    }

    // Retrieves the data from the specified metadata
    public ArrayList<ArrayList<ParsedData>> getData(){
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

    public ArrayList<StoredData> getAnalysis(){
        return strategy.getAnalysis();
    }
    
    
}

package strategyAnalysis;

import java.util.ArrayList;

import dataFetch.StoredData;

/**
 * Interface for Analysis Strategy design pattern
 * 
 * @author Abdul
 *
 */
public interface AnalysisStrategy {

    void performAnalysis(AnalysisContext analysisContext);

    ArrayList<StoredData> getAnalysis();

}

package analysis;

import dataFetch.StoredData;

import java.util.ArrayList;

// Abstract class for Strategy - base class for the different types of analysis
public interface analysisStrategy<T>{

    // Each type of analysis should present itself
     void performAnalysis(analysisContext context);

     ArrayList<StoredData> getAnalysis();
}

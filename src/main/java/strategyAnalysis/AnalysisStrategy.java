package strategyAnalysis;

import java.util.ArrayList;

import dataFetch.StoredData;

public interface AnalysisStrategy {

	void performAnalysis(AnalysisContext analysisContext);
	ArrayList<StoredData> getAnalysis();
	
}

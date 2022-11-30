package strategyAnalysis;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;

import java.util.ArrayList;


/**
 * Implements the Strategy Design Pattern's Context Class Class that is called
 * by the Client/UI to set and execute the strategy
 */
public class AnalysisContext {
	private AnalysisStrategy strategy;
//    private ParsedData data;

	public AnalysisContext(AnalysisStrategy strategy) {
		this.strategy = strategy;
	}

	// Retrieves the data from the specified metadata
	public ArrayList<StoredData> getData() {
		return DataAcquisition.getDataStorage();
	}

	// Sets the strategy
	public void setStrategy(AnalysisStrategy strategy) {
		this.strategy = strategy;
	}

	// Executes the strategy
	public void execute() {
		strategy.performAnalysis(this);
	}

	public ArrayList<StoredData> getAnalysis() {
		return strategy.getAnalysis();
	}




}
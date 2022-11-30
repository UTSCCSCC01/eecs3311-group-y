package strategyAnalysis;

import java.util.ArrayList;

import dataFetch.StoredData;
/**
 * Average strategy that calculates the average between years
 * 
 * @author Abdul
 *
 */
public class AnalysisAverage implements AnalysisStrategy {
	ArrayList<StoredData> result = new ArrayList<>();

	/**
	 * 
	 * @param  object to get data from
	 */
	@Override
	public void performAnalysis(AnalysisContext analysisContext) {
		ArrayList<StoredData> temp = new ArrayList<>();
		StoredData res = new StoredData(analysisContext.getData().get(0).getInd());
		float r = 0;
		int sizeOfResults = analysisContext.getData().get(0).getValues().size() - 1;
		for (int i = 0; i < sizeOfResults; i++) {
			r = r + (analysisContext.getData().get(0).getValues().get(i));
		}
		if (sizeOfResults > 0)
			r = r / sizeOfResults;

		res.getYears().add(analysisContext.getData().get(0).getYears().get(sizeOfResults));
		res.getValues().add(r);
		temp.add(res);
		setAnalysis(temp);

	}
	
	/**
	 * 
	 * @param sd to set to
	 */
	public void setAnalysis(ArrayList<StoredData> sd) {
		this.result = sd;
	}

	/**
	 * @return ArrayList of StoredData
	 */
	public ArrayList<StoredData> getAnalysis() {
		return this.result;
	}

}

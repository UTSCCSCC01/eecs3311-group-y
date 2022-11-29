package analysis;

import java.util.ArrayList;

import dataFetch.StoredData;

public class AnalysisAnnual implements AnalysisStrategy {
	ArrayList<StoredData> res = new ArrayList<>();

	@Override
	public void performAnalysis(AnalysisContext analysisContext) {
		ArrayList<StoredData> temp = new ArrayList<StoredData>();

		for (int i = 0; i < analysisContext.getData().size(); i++) {
			StoredData resultData = new StoredData(analysisContext.getData().get(i).getInd());
			int endYear = analysisContext.getData().get(i).getEndYear();
			int sizeOfResults = analysisContext.getData().get(i).getValues().size() - 1;
			for (int j = 0; j < sizeOfResults; j++) {
				Float tempForMath = analysisContext.getData().get(i).getValues().get(j)
						- analysisContext.getData().get(i).getValues().get(j + 1);
				Float tempFloat = tempForMath / analysisContext.getData().get(i).getValues().get(j + 1) * 100;

				resultData.getYears().add(endYear - j);

				resultData.getValues().add(tempFloat);
			}
			temp.add(resultData);

		}

		setAnalysis(temp);
	}

	public void setAnalysis(ArrayList<StoredData> sd) {
		this.res = sd;
	}

	public ArrayList<StoredData> getAnalysis() {
		return this.res;
	}

}

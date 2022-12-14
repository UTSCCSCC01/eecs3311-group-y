package strategyAnalysis;

import java.util.ArrayList;

import dataFetch.StoredData;

/**
 * Ratio Strategy that calculates the ratio of two data points
 * 
 * @author Abdul
 *
 */
public class AnalysisRatio implements AnalysisStrategy {
    ArrayList<StoredData> res = new ArrayList<>();

    /**
     * 
     * @param Analysis Object to work with
     * @exception if more than two indicator data points
     */
    @Override
    public void performAnalysis(AnalysisContext analysisContext) {
        ArrayList<StoredData> restemp = new ArrayList<>();
        if (analysisContext.getData().size() % 2 == 0) {
            StoredData data = analysisContext.getData().get(0);
            StoredData data2 = analysisContext.getData().get(1);

            int small;
            String ratioLine = "Ratio of " + data.getSeriesName() + " and " + data2.getSeriesName();
            String indLine = data.getInd() + "&" + data2.getInd();
            StoredData calData = new StoredData(indLine, ratioLine);
            ArrayList<Integer> dataYears = data.getYears();
            ArrayList<Integer> data2Years = data2.getYears();
            ArrayList<Float> dataValues = data.getValues();
            ArrayList<Float> data2Values = data2.getValues();
            ArrayList<Integer> tempDataYear;
            ArrayList<Integer> tempData2Year;
            Integer year;

            if (dataYears.size() < data2Years.size()) {
                small = dataYears.size();
                tempDataYear = dataYears;
                tempData2Year = data2Years;
            } else if (dataYears.size() > data2Years.size()) {
                small = data2Years.size();
                tempDataYear = data2Years;
                tempData2Year = dataYears;
            } else {
                small = data2Years.size();
                tempDataYear = data2Years;
                tempData2Year = dataYears;
            }

            for (int i = 0; i < small; i++) {
                year = tempDataYear.get(i);

                if (tempData2Year.contains(year)) {
                    if (tempDataYear == dataYears) {
                        int j = data2Years.indexOf(year);
                        float ratio = dataValues.get(i) / data2Values.get(j);
                        calData.getValues().add(ratio);
                        calData.getYears().add(year);
                    } else {
                        int j = dataYears.indexOf(year);
                        float ratio = dataValues.get(j) / data2Values.get(i);
                        calData.getValues().add(ratio);
                        calData.getYears().add(year);
                    }
                }
            }

            restemp.add(calData);
        } else {
            throw new IllegalArgumentException("There must be two pieces of data for ratios");
        }
        setAnalysis(restemp);
        
    }

    /**
     * 
     * @param sd to set data to
     */
    public void setAnalysis(ArrayList<StoredData> sd) {
        this.res = sd;
    }

    /**
     * @return analysis data
     */
    public ArrayList<StoredData> getAnalysis() {
        return this.res;
    }

}

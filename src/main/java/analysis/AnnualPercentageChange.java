package analysis;

import dataFetch.ParsedData;
import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Analyzes the rate of change from year to year
 */
public class AnnualPercentageChange implements analysisStrategy{
    ArrayList<StoredData> res = new ArrayList<>();

    /**
     * Iterates over all indicator data and years and returns a map of
     * indicators with its associated year PERCENTAGE CHANGES
     * @param context
     */
    @Override
    public void performAnalysis(analysisContext context){
        ArrayList<StoredData> sd = new ArrayList<>();

        for (ArrayList<ParsedData> sdList: context.getData()){      // Iterates over all the provided indicators
            if (sdList.size() > 1){
                ArrayList<Integer> years = new ArrayList<>();
                ArrayList<Float> calculatedValues = new ArrayList<>();
                for (int i = 0; i < sdList.size() - 1  ; i++){            // Iterates over all the years of the current indicator
                    float curYearVal = sdList.get(i).getYearValues();
                    float lastYearVal = sdList.get(i + 1).getYearValues();
                    float pChange = percentageChange(curYearVal, lastYearVal);
                    int curYear = sdList.get(i).getYears();
                    years.add(curYear);
                    calculatedValues.add(pChange);
                }
                String curYearInd = sdList.get(0).getSeriesIndicator();
                StoredData p = new StoredData(curYearInd, calculatedValues, years);
                sd.add(p);
            }
        }
        setAnalysis(sd);
    }
    //HashMap<String, ArrayList<YearPChange>>
    /**
     * Calculates percentage change
     * Increase from Previous Year = ((New Value - Old Value) / Old Value) * 100
     * Decrease from Previous Year = ((Old Value - New Value) / Old Value) * 100
     * @param curYearVal
     * @param lastYearVal
     * @return result - float
     */
    public float percentageChange(float curYearVal, float lastYearVal){
        float result;
        if (curYearVal == lastYearVal){
            return 0.0f;
        }else if (curYearVal < lastYearVal){
            result = ((lastYearVal - curYearVal) / lastYearVal) * 100;
        }else{
            result = ((curYearVal - lastYearVal) / lastYearVal) * 100;
        }
        return result;
    }

    public void setAnalysis(ArrayList<StoredData> sd){
        this.res = sd;
    }

    public ArrayList<StoredData> getAnalysis(){
        System.out.println("This is the analysis" + this.res);
        return this.res;
    }

}

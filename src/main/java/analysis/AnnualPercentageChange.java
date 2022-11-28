package analysis;

import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Analyzes the rate of change from year to year
 */
public class AnnualPercentageChange implements analysisStrategy{

    class YearPChange{
        int Year;
        float pChange;

        public YearPChange(int year, float pChange){
            this.Year = year;
            this.pChange = pChange;
        }

        public int getYear(){
            return this.Year;
        }

        public float getpChange(){
            return this.pChange;
        }
    }

    /**
     * Iterates over all indicator data and years
     * @param context
     */
    @Override
    public HashMap<String, ArrayList<YearPChange>> performAnalysis(analysisContext context){
        HashMap<String, ArrayList<YearPChange>> indicatorAndResult = new HashMap();
        for (ArrayList<StoredData> sdList: context.getData()){      // Iterates over all the provided indicators
            ArrayList<YearPChange> result = new ArrayList();
            if (sdList.size() > 1){
                for (int i = 0; i < sdList.size() - 1  ; i++){            // Iterates over all the years of the current indicator
                    float curYearVal = sdList.get(i).getYearValues();
                    float lastYearVal = sdList.get(i + 1).getYearValues();
                    float pChange = percentageChange(curYearVal, lastYearVal);
                    int curYear = sdList.get(i).getYears();
                    YearPChange resultPair = new YearPChange(curYear, pChange);
                    result.add(resultPair);
                }
                String curYearInd = sdList.get(0).getSeriesIndicator();
                indicatorAndResult.put(curYearInd, result);         // stores result mapped to its current indicator
            }
        }
        return indicatorAndResult;
    }

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
}

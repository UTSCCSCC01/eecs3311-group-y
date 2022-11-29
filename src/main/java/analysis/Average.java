package analysis;

import dataFetch.ParsedData;
import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

/**
 * Iterates over all data and years and returns a map of indicators with
 * its associated year AVERAGES
 */
public class Average implements analysisStrategy{
    ArrayList<StoredData> res = new ArrayList<>();

    @Override
    public void performAnalysis(analysisContext context){
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> calculatedValues = new ArrayList<>();
        ArrayList<StoredData> sd = new ArrayList<>();


        for (ArrayList<ParsedData> sdList: context.getData()){
            float subTotal = 0;
            String curYearInd = sdList.get(0).getSeriesIndicator();
            System.out.println(sdList.size());
            if (sdList.size() >= 1) {
                for (int i = 0; i < sdList.size(); i++) {
                    subTotal += sdList.get(i).getYearValues();
                    System.out.println(i);
                }
                float result = (subTotal / sdList.size());
                calculatedValues.add(result);
                years.add(sdList.get(0).getYears());
                StoredData p = new StoredData(curYearInd, calculatedValues, years);
                sd.add(p);
            }else{
                years.add(sdList.get(0).getYears());
                calculatedValues.add(sdList.get(0).getYearValues());
                StoredData p = new StoredData(curYearInd, calculatedValues, years);
                sd.add(p);
            }
        }
        setAnalysis(sd);
    }
    // arraylist Year, yearvalues - MUST be
    public void setAnalysis(ArrayList<StoredData> sd){
        this.res = sd;
    }

    public ArrayList<StoredData> getAnalysis(){
        System.out.println("hello this is the average " + this.res);
        return this.res;
    }

}
    //HashMap<String, Float>


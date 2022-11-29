package analysis;


import dataFetch.ParsedData;
import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO get rid of these notes when done
 * represent as two objects
 * {
 *  indicator1: ratio
 *  indicator2: value
 * }
 */
public class Ratio implements analysisStrategy{

    ArrayList<StoredData> res = new ArrayList<>();
//    class yearAndRatio{
//        int year;
//
//        float ratio;
//
//        public yearAndRatio(int year, float ratio){
//            this.year = year;
//            this.ratio = ratio;
//        }
//        public int getYear(){
//            return this.year;
//        }
//        public float getRatio(){
//            return this.ratio;
//        }
//    }
    @Override
    public void performAnalysis(analysisContext context){
        ArrayList<StoredData> sd = new ArrayList<>();

        if (context.getData().size() % 2 == 0){
            // Scans all input tables
            for (int i = 0; i < context.getData().size(); i+=2){
                ArrayList<Integer> years = new ArrayList<>();
                ArrayList<Float> calculatedValues = new ArrayList<>();

                ParsedData dataSubset = context.getData().get(i).get(0);
                ParsedData dataTotal = context.getData().get(i + 1).get(0);
                float ratio = dataSubset.getYearValues()/dataTotal.getYearValues();
                int curYear = dataSubset.getYears();
                years.add(curYear);
                calculatedValues.add(ratio);
                StoredData p = new StoredData("", calculatedValues, years);
                sd.add(p);
            }
        }else{
            throw new IllegalArgumentException("There must be two pieces of data for ratioso");
        }
        setAnalysis(sd);
    }

    public void setAnalysis(ArrayList<StoredData> sd){
        this.res = sd;
    }

    public ArrayList<StoredData> getAnalysis(){
        return this.res;
    }

}

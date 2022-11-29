package analysis;

import dataFetch.StoredData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
Adapter Design Pattern
 */
public class StoredDataAdapter {
    ArrayList<Integer> years = new ArrayList<>();
    ArrayList<Float> calculatedValues = new ArrayList<>();
    public StoredDataAdapter(ArrayList<Integer> years, ArrayList<Float> calculatedValues){
        this.years = years;
        this.calculatedValues = calculatedValues;
    }

//    public static ArrayList<StoredData> processAnalysisPerChange(HashMap<String, ArrayList<AnnualPercentageChange.YearPChange>> mapAnalysis){
//        ArrayList<StoredData> result = new ArrayList<>();
//
//        for (Map.Entry<String, ArrayList<AnnualPercentageChange.YearPChange>> entry: mapAnalysis.entrySet()){
//            ArrayList<Float> yearValues = new ArrayList<>();
//            ArrayList<Integer> years = new ArrayList<>();
//            for (AnnualPercentageChange.YearPChange yp: entry.getValue()){
//                yearValues.add(yp.getpChange());
//                years.add(yp.getYear());
//            }
//            StoredData sd = new StoredData(entry.getKey(), yearValues, years);
//            result.add(sd);
//        }
//        return result;
//    }
//
//    public static ArrayList<StoredData> processAnalysisRatio(ArrayList<Ratio.yearAndRatio> mapAnalysis){
//        ArrayList<StoredData> result = new ArrayList<>();
//
//        for (Ratio.yearAndRatio yr: mapAnalysis){
//            ArrayList<Float> yearValues = new ArrayList<>();
//            ArrayList<Integer> years = new ArrayList<>();
//            yearValues.add(yr.getRatio());
//            years.add(yr.getYear());
//            StoredData p = new StoredData("", yearValues, years);
//            result.add(p);
//        }
//        return result;
//    }
//
//    public static ArrayList<StoredData> processAnalysisAvg(HashMap<String, Float> resultMap){
//        ArrayList<StoredData> result = new ArrayList();
//        ArrayList<Float> yearValues = new ArrayList<>();
//        ArrayList<Integer> years = new ArrayList<>();
//        for (Map.Entry<String, Float> entry: resultMap.entrySet()) {
//            yearValues.add(entry.getValue());
//            StoredData p = new StoredData(entry.getKey(), yearValues, years);
//            result.add(p);
//            System.out.println(p.getValues());
//            System.out.println();
//        }
//        return result;
//
//    }


}

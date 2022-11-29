package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;

import java.util.ArrayList;
import java.util.Arrays;

public class analysisContextTest {
    String[][] indicatorList = new String[][]{
            { "SH.MED.BEDS.ZS"},};
    String country_code = "US";

    // Tests getData method in AnalysisContext
    @Test
    void testGetData(){
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        System.out.println(ac.getData());
        System.out.println(mockDataStorage());
        Assertions.assertIterableEquals(ac.getData().get(0).getValues(), mockDataStorage().get(0).getValues());
        Assertions.assertIterableEquals(ac.getData().get(0).getYears(), mockDataStorage().get(0).getYears());
        Assertions.assertEquals(ac.getData().get(0).getSeriesName(), mockDataStorage().get(0).getSeriesName());
    }


    private ArrayList<StoredData> mockDataStorage(){
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>(
                Arrays.asList(2017, 2016, 2015));
        ArrayList<Float> yearValues = new ArrayList<>(
                Arrays.asList(2.87f, 2.77f, 2.8f));
        StoredData sd = new StoredData(indicatorList[0][0], yearValues, years);
        test.add(sd);
        return test;
    }
}

package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;

import java.util.ArrayList;
import java.util.Arrays;

public class analysisRatioTest {
    String[][] indicatorList = new String[][]{
            { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },};
    String country_code = "US";

    // Makes a call to the analysis strategy ratio and tests if the data matches the expected data
    @Test
    void testAnalysisRatio(){
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), generateMockData().get(0).getValues());
    }

    // Generates mock data for unit test to compare with - Expected values of data retrieved
    private ArrayList<StoredData> generateMockData(){
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>(
                Arrays.asList(0.56172955f, 0.57730585f, 0.56526715f));
        for (int i = 2017; i > 2014; i--){
            years.add(i);
        }
        StoredData sd = new StoredData("", yearValues, years);
        test.add(sd);
        return test;
    }
}

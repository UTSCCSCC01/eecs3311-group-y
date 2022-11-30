package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;

import java.util.ArrayList;

public class analysisAverageTest {

    String[][] indicatorList = new String[][]{
            { "AG.LND.FRST.ZS" },};
    String country_code = "US";

    // Makes a call to the analysis strategy ratio and tests if the data matches the expected data
    @Test
    void testAnalysisAverage(){
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAverage());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), generateMockData().get(0).getValues());
    }

    // Generates mock data for unit test to compare with - Expected values of data retrieved

    private ArrayList<StoredData> generateMockData(){
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>();
        years.add(2015);
        yearValues.add(33.873486f);
        StoredData sd = new StoredData("AG.LND.FRST.ZS", yearValues, years);
        test.add(sd);
        return test;
    }
}

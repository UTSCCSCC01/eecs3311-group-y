package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisContext;

import java.util.ArrayList;

public class analysisAnnualTest {
    String country_code = "US";

    // Makes a call to the analysis strategy ratio and tests if the data matches the expected data
    @Test
    void testAnalysisAnnual() {
        String[][] indicatorList = new String[][]{
                { "AG.LND.FRST.ZS" },};

        // Builds hardcoded test data
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>();
        for (int i = 2020; i > 2018; i--){
            years.add(i);
            yearValues.add(0f);
        }
        StoredData sd = new StoredData("AG.LND.FRST.ZS", yearValues, years);
        test.add(sd);
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2018", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }
}

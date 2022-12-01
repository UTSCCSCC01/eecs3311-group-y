package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;

import java.util.ArrayList;
import java.util.Arrays;

public class strategyAnalysisTester {

//Makes a call to the analysis strategy ratio and tests if the data matches the expected data
    @Test
    void testAnalysisAverage() {
        String country_code = "US";
        String[][] indicatorList = new String[][] {

                { "AG.LND.FRST.ZS" }, };
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>();
        years.add(2015);
        yearValues.add(33.873486f);
        StoredData sd = new StoredData("AG.LND.FRST.ZS", yearValues, years);
        test.add(sd);
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        assert (dp == true);
        AnalysisContext ac = new AnalysisContext(new AnalysisAverage());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

    @Test
    void testAnalysisAnnual() {
        String country_code = "US";
        String[][] indicatorList = new String[][] {
                { "AG.LND.FRST.ZS" }, };

        // Builds hardcoded test data
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>();
        for (int i = 2020; i > 2018; i--) {
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

// Tests getData method in AnalysisContext
    @Test
    void testGetData() {
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS" }, };
        String country_code = "US";
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>(
                Arrays.asList(2017, 2016, 2015));
        ArrayList<Float> yearValues = new ArrayList<>(
                Arrays.asList(2.87f, 2.77f, 2.8f));
        StoredData sd = new StoredData(indicatorList[0][0], yearValues, years);
        test.add(sd);
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        Assertions.assertIterableEquals(ac.getData().get(0).getValues(), test.get(0).getValues());
        Assertions.assertIterableEquals(ac.getData().get(0).getYears(), test.get(0).getYears());
        Assertions.assertEquals(ac.getData().get(0).getSeriesName(), test.get(0).getSeriesName());
    }

// Makes a call to the analysis strategy ratio and tests if the data matches the expected data
    @Test
    void testAnalysisRatio() {
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" }, };
        String country_code = "US";
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>(
                Arrays.asList(0.56172955f, 0.57730585f, 0.56526715f));
        for (int i = 2017; i > 2014; i--) {
            years.add(i);
        }
        StoredData sd = new StoredData("", yearValues, years);
        test.add(sd);
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

}

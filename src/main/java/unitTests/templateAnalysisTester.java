package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import countryProcess.CountryStorage;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;
import templateAnalysis.AnalysisFour;
import templateAnalysis.AnalysisOne;
import templateAnalysis.AnalysisThree;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class templateAnalysisTester {

    @Test
    void testAnalysisFourRatio() {
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
        AnalysisFour ac = new AnalysisFour();
        ac.calculate();
        Assertions.assertIterableEquals(ac.context.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

    @Test
    void testAnalysisRatioException() {
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS" }, };
        String country_code = "US";
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisFour ac = new AnalysisFour();

        Exception thrown = assertThrows("Expected Ratio to throw exception, but it didn't",
                Exception.class,
                () -> ac.calculate()

        );
    }

    @Test
    void testAnalysisTemplateAverage() {
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
        AnalysisThree ac = new AnalysisThree();
        ac.calculate();
        Assertions.assertIterableEquals(ac.context.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

    @Test
    void testAnalysisTemplateAnnual() {
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
        AnalysisOne ac = new AnalysisOne();
        ac.calculate();
        Assertions.assertIterableEquals(ac.context.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

}

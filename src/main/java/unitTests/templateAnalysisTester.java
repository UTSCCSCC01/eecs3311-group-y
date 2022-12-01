package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import countryProcess.*;
import strategyAnalysis.*;
import templateAnalysis.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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

    String[][] indicatorList = new String[][] {
            { "AG.LND.FRST.ZS" }, };
    String country_code = "US";

    @Test
    public void AnalysisOneTest() {
        String title = "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution";
        String[] graphs = { "Line", "Scatter", "Report" };

        AnalysisOne one = new AnalysisOne();
        AnalysisContext cn = new AnalysisContext(new AnalysisAnnual());
        cn.execute();
        one.calculate();
        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(one.getViewer().getGraphs()));
        assertEquals(title, one.getViewer().getTitle());
    }

    @Test
    public void AnalysisTwoTest() {
        String title = "Annual Change in GDP per Capita and Total Population";
        String[] graphs = { "Line", "Scatter", "Report" };

        AnalysisTwo one = new AnalysisTwo();
        AnalysisContext cn = new AnalysisContext(new AnalysisAnnual());
        cn.execute();
        one.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(one.getViewer().getGraphs()));
        assertEquals(title, one.getViewer().getTitle());
    }

    @Test
    public void AnalysisThreeTest() {
        String title = "Average Forested Area";
        String[] graphs = { "Pie", "Report" };
        AnalysisAverage an = new AnalysisAverage();

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAverage());
        ac.execute();

        AnalysisThree analysisVal = new AnalysisThree();
        analysisVal.calculate();
        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisFourTest() {
        String title = "Ratio of CO2 Emissions and GDP per capita";
        String[] graphs = { "Line", "Scatter", "Report", "Bar" };
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" }, };

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();

        AnalysisFour analysisVal = new AnalysisFour();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisFiveTest() {
        String title = "Average Government Expenditure Education";
        String[] graphs = { "Pie", "Report" };

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAverage());
        ac.execute();

        AnalysisFive analysisVal = new AnalysisFive();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisSixTest() {
        String title = "Ratio of Population to Energy Use";
        String[] graphs = { "Line", "Scatter", "Report", "Bar" };
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" }, };

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();

        AnalysisSix analysisVal = new AnalysisSix();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisSevenTest() {
        String title = "Annual Change of Health Costs vs Air Pollution";
        String[] graphs = { "Line", "Scatter", "Report" };

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();

        AnalysisSeven analysisVal = new AnalysisSeven();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisEightTest() {
        String title = "Ratio of GDP and Renewable Energy Output";
        String[] graphs = { "Line", "Scatter", "Report", "Bar" };
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" }, };

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();

        AnalysisEight analysisVal = new AnalysisEight();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

}

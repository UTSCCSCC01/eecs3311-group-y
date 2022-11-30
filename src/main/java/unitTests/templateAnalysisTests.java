package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Test;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;
import templateAnalysis.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * Class that tests the various `templateAnalysis` classes
 */

public class templateAnalysisTests {

    String[][] indicatorList = new String[][]{
            { "AG.LND.FRST.ZS" },};
    String country_code = "US";

    @Test
    public void AnalysisOneTest(){
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
    public void AnalysisTwoTest(){
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
    public void AnalysisThreeTest(){
        String title = "Average Forested Area";
        String[] graphs = {"Pie", "Report"};
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
    public void AnalysisFourTest(){
        String title = "Ratio of CO2 Emissions and GDP per capita";
        String[] graphs = {"Line","Scatter","Report","Bar"};
        String[][] indicatorList = new String[][]{
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },};

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();

        AnalysisFour analysisVal = new AnalysisFour();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisFiveTest(){
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
    public void AnalysisSixTest(){
        String title = "Ratio of Population to Energy Use";
        String[] graphs = { "Line", "Scatter", "Report", "Bar" };
        String[][] indicatorList = new String[][]{
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },};

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();

        AnalysisSix analysisVal = new AnalysisSix();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    @Test
    public void AnalysisSevenTest(){
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
    public void AnalysisEightTest(){
        String title = "Ratio of GDP and Renewable Energy Output";
        String[] graphs = { "Line", "Scatter", "Report", "Bar" };
        String[][] indicatorList = new String[][]{
                { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },};

        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();

        AnalysisEight analysisVal = new AnalysisEight();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }




}
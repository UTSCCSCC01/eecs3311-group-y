package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import statsVisualiser.LoginUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import countryProcess.CountryStorage;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;
import templateAnalysis.AnalysisEight;
import templateAnalysis.AnalysisFive;
import templateAnalysis.AnalysisFour;
import templateAnalysis.AnalysisOne;
import templateAnalysis.AnalysisSeven;
import templateAnalysis.AnalysisSix;
import templateAnalysis.AnalysisThree;
import templateAnalysis.AnalysisTwo;
import visualizations.ViewerBar;
import visualizations.ViewerLine;
import visualizations.ViewerMain;
import visualizations.ViewerPie;
import visualizations.ViewerScatter;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TesterFile {

    @Test
    void testBarTwoSeries() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(5.8, "Mortality/1000 births", "2016");
        dataset.setValue(5.8, "Mortality/1000 births", "2015");
        dataset.setValue(5.9, "Mortality/1000 births", "2014");
        dataset.setValue(6, "Mortality/1000 births", "2013");
        dataset.setValue(6.1, "Mortality/1000 births", "2012");
        dataset.setValue(6.2, "Mortality/1000 births", "2011");
        dataset.setValue(6.4, "Mortality/1000 births", "2010");

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        dataset2.setValue(9877, "Health Expenditure per Capita", "2016");
        dataset2.setValue(9491, "Health Expenditure per Capita", "2015");
        dataset2.setValue(9023, "Health Expenditure per Capita", "2014");
        dataset2.setValue(8599, "Health Expenditure per Capita", "2013");
        dataset2.setValue(8399, "Health Expenditure per Capita", "2012");
        dataset2.setValue(8130, "Health Expenditure per Capita", "2011");
        dataset2.setValue(7930, "Health Expenditure per Capita", "2010");

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();
        BarRenderer barrenderer2 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, barrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart barChart = new JFreeChart("Mortality vs Expenses",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD" },
                "USA",
                "2010",
                "2016");

        ViewerBar n = new ViewerBar();
        n = new ViewerBar(DataAcquisition.getDataStorage(), "Title", "Label1", "Label2", "Label3", "Label4", "Label5",
                "Label6");
        n.seeExample();
        assertEquals(n.getChart().getChart().getCategoryPlot().getDatasetCount(),
                barChart.getCategoryPlot().getDatasetCount());
        assertEquals(n.getChart().getChart().getCategoryPlot().getDomainAxisCount(),
                barChart.getCategoryPlot().getDomainAxisCount());
        assertEquals(n.getChart().getChart().getCategoryPlot().getWeight(),
                barChart.getCategoryPlot().getWeight());

    }

    @Test
    void testBarThreeSeries() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(5.8, "Mortality/1000 births", "2016");
        dataset.setValue(5.8, "Mortality/1000 births", "2015");
        dataset.setValue(5.9, "Mortality/1000 births", "2014");
        dataset.setValue(6, "Mortality/1000 births", "2013");
        dataset.setValue(6.1, "Mortality/1000 births", "2012");
        dataset.setValue(6.2, "Mortality/1000 births", "2011");
        dataset.setValue(6.4, "Mortality/1000 births", "2010");

        dataset.setValue(2.77, "Hospital beds/1000 people", "2016");
        dataset.setValue(2.8, "Hospital beds/1000 people", "2015");
        dataset.setValue(2.83, "Hospital beds/1000 people", "2014");
        dataset.setValue(2.89, "Hospital beds/1000 people", "2013");
        dataset.setValue(2.93, "Hospital beds/1000 people", "2012");
        dataset.setValue(2.97, "Hospital beds/1000 people", "2011");
        dataset.setValue(3.05, "Hospital beds/1000 people", "2010");

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        dataset2.setValue(9877, "Health Expenditure per Capita", "2016");
        dataset2.setValue(9491, "Health Expenditure per Capita", "2015");
        dataset2.setValue(9023, "Health Expenditure per Capita", "2014");
        dataset2.setValue(8599, "Health Expenditure per Capita", "2013");
        dataset2.setValue(8399, "Health Expenditure per Capita", "2012");
        dataset2.setValue(8130, "Health Expenditure per Capita", "2011");
        dataset2.setValue(7930, "Health Expenditure per Capita", "2010");

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();
        BarRenderer barrenderer2 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, barrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart barChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2018");

        ViewerBar n = new ViewerBar();
        n = new ViewerBar(DataAcquisition.getDataStorage(), "Title", "Label1", "Label2", "Label3", "Label4", "Label5",
                "Label6");
        n.seeExample();
        assertEquals(n.getChart().getChart().getCategoryPlot().getDomainAxisCount(),
                barChart.getCategoryPlot().getDomainAxisCount());
        assertEquals(n.getChart().getChart().getCategoryPlot().getWeight(),
                barChart.getCategoryPlot().getWeight());

    }

    @Test
    void testBarView() throws Exception {
        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2018");

        ViewerBar n = new ViewerBar();
        n = new ViewerBar(DataAcquisition.getDataStorage(), "Title", "Label1", "Label2", "Label3", "Label4", "Label5",
                "Label6");
        // ensure no exception to view chart
        n.viewPanel(n.getChart());
        // you would then do n.setVisible() to view it but we wont to prevent clutter

    }

    @Test
    void testScatterTwoSeries() throws Exception {
        TimeSeries series1 = new TimeSeries("Mortality/1000 births");
        series1.add(new Year(2018), 5.6);
        series1.add(new Year(2017), 5.7);
        series1.add(new Year(2016), 5.8);
        series1.add(new Year(2015), 5.8);
        series1.add(new Year(2014), 5.9);
        series1.add(new Year(2013), 6.0);
        series1.add(new Year(2012), 6.1);
        series1.add(new Year(2011), 6.2);
        series1.add(new Year(2010), 6.4);

        TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
        series2.add(new Year(2018), 10624);
        series2.add(new Year(2017), 10209);
        series2.add(new Year(2016), 9877);
        series2.add(new Year(2015), 9491);
        series2.add(new Year(2014), 9023);
        series2.add(new Year(2013), 8599);
        series2.add(new Year(2012), 8399);
        series2.add(new Year(2011), 8130);
        series2.add(new Year(2010), 7930);
        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(series2);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, itemrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart scatterChart = new JFreeChart("Mortality vs Expenses",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD" },
                "USA",
                "2010",
                "2016");

        ViewerScatter n = new ViewerScatter(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");
        n.seeExample();
        assertEquals(n.getChart().getChart().getXYPlot().getWeight(),
                scatterChart.getXYPlot().getWeight());
        assertEquals(n.getChart().getChart().getXYPlot().getRendererCount(),
                scatterChart.getXYPlot().getRendererCount());
        assertEquals(n.getChart().getChart().getXYPlot().getRangeAxisCount(),
                scatterChart.getXYPlot().getRangeAxisCount());
        assertEquals(n.getChart().getChart().getXYPlot().getDomainAxisCount(),
                scatterChart.getXYPlot().getDomainAxisCount());
    }

    @Test
    void testScatterThreeSeries() throws Exception {
        TimeSeries series1 = new TimeSeries("Mortality/1000 births");
        series1.add(new Year(2018), 5.6);
        series1.add(new Year(2017), 5.7);
        series1.add(new Year(2016), 5.8);
        series1.add(new Year(2015), 5.8);
        series1.add(new Year(2014), 5.9);
        series1.add(new Year(2013), 6.0);
        series1.add(new Year(2012), 6.1);
        series1.add(new Year(2011), 6.2);
        series1.add(new Year(2010), 6.4);

        TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
        series2.add(new Year(2018), 10624);
        series2.add(new Year(2017), 10209);
        series2.add(new Year(2016), 9877);
        series2.add(new Year(2015), 9491);
        series2.add(new Year(2014), 9023);
        series2.add(new Year(2013), 8599);
        series2.add(new Year(2012), 8399);
        series2.add(new Year(2011), 8130);
        series2.add(new Year(2010), 7930);
        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(series2);

        TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
        series3.add(new Year(2018), 2.92);
        series3.add(new Year(2017), 2.87);
        series3.add(new Year(2016), 2.77);
        series3.add(new Year(2015), 2.8);
        series3.add(new Year(2014), 2.83);
        series3.add(new Year(2013), 2.89);
        series3.add(new Year(2012), 2.93);
        series3.add(new Year(2011), 2.97);
        series3.add(new Year(2010), 3.05);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series3);

        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, itemrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart scatterChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2016");

        ViewerScatter n = new ViewerScatter(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");
        n.seeExample();
        assertEquals(n.getChart().getChart().getXYPlot().getWeight(),
                scatterChart.getXYPlot().getWeight());
        assertEquals(n.getChart().getChart().getXYPlot().getRendererCount(),
                scatterChart.getXYPlot().getRendererCount());
        assertEquals(n.getChart().getChart().getXYPlot().getRangeAxisCount(),
                scatterChart.getXYPlot().getRangeAxisCount());
        assertEquals(n.getChart().getChart().getXYPlot().getDomainAxisCount(),
                scatterChart.getXYPlot().getDomainAxisCount());
    }

    @Test
    void testPieDouble() throws Exception {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(6.2, "Mortaility", "SP.DYN.IMRT.IN");
        dataset.addValue(7888.352, "Expenses", "SH.XPD.CHEX.PC.CD");

        JFreeChart pieChart = ChartFactory.createMultiplePieChart("Tester", dataset,
                TableOrder.BY_COLUMN, true, true, false);

        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD" },
                "USA",
                "2010",
                "2010");
        ViewerPie n = new ViewerPie(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4");
        n.seeExample();
        n.seeExample(n.getChart());
        assertEquals(n.getChart().getChart().getPlot(),
                pieChart.getPlot());
    }

    @Test
    void testViewerMain() throws Exception {
        ViewerMain temp = new ViewerMain();
        DataAcquisition newData = new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2012");
        temp = new ViewerMain(DataAcquisition.getDataStorage(), "Temp");
        temp.makeLineChart();
        temp.makeScatterChart();
        temp.makePieChart();
        temp.makeBarChart();
        temp.makeReport();
        ViewerScatter n = new ViewerScatter(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n.getChart().getChart().getXYPlot().getWeight(),
                temp.getScat().getChart().getXYPlot().getWeight());

        ViewerLine n2 = new ViewerLine(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n2.getChart().getChart().getXYPlot().getWeight(),
                temp.getLine().getChart().getXYPlot().getWeight());

        ViewerBar n3 = new ViewerBar(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n3.getChart().getChart().getCategoryPlot().getWeight(),
                temp.getBar().getChart().getCategoryPlot().getWeight());

    }

    @Test
    void testCountryDatabaseSize() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().size(), 258);

    }

    @Test
    void testCountryDatabaseReadFile() throws Exception {
        String temp = CountryStorage.readFileAsString("src/assets/Countries.json");
        assert (temp.length() > 0);

    }

    @Test
    void testCountryStorageException() throws Exception {
        CountryStorage db = new CountryStorage("Countries");

        Exception thrown = assertThrows("Expected readFile to throw, but it didn't",
                Exception.class,
                () -> CountryStorage.readFileAsString("Countries.json")

        );

    }

    @Test
    void testCountryTestCountryExist() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().get(0).getCountryName(), "Afghanistan");
        assertEquals(db.getCountryStorageList().get(0).getCountryCode(), "AFG");
        assertEquals(db.getCountryStorageList().get(0).getStartYear(), 1962);
        assertEquals(db.getCountryStorageList().get(0).getEndYear(), 2021);

    }

    @Test
    void testCountryStorageNotExist() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().get(4).getCountryName(), "American Samoa");
        assertEquals(db.getCountryStorageList().get(4).getCountryCode(), "NA");

    }

    @Test
    void testDataFetcherValidYear() {
        DataAcquisition newData = new DataAcquisition(new String[] { "AG.LND.FRST.ZS" }, "CA", "2000", "2005");
        int expectedSize = 6;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        double endingValue = 38.766228;
        double startingValue = 38.79298;
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;
        double actualStart = DataAcquisition.dataStorage.get(0).getValues().get(0);
        assertEquals(startingValue, actualStart, 0.03);
        double actualEnd = DataAcquisition.dataStorage.get(0).getValues().get(actualSize - 1);
        assertEquals(endingValue, actualEnd, 0.03);

    }

    @Test
    void testDataFetcherValidYear2() {
        DataAcquisition newData = new DataAcquisition(new String[] { "SP.POP.TOTL" }, "BA", "2000", "2006");
        int expectedSize = 7;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        double startingValue = 3765422.0;
        double endingValue = 3751176.0;
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;
        double actualStart = DataAcquisition.dataStorage.get(0).getValues().get(0);
        assertEquals(startingValue, actualStart, 0.03);
        double actualEnd = DataAcquisition.dataStorage.get(0).getValues().get(actualSize - 1);
        assertEquals(endingValue, actualEnd, 0.03);

    }

    @Test
    void testDataFetcherNoIndicatorData() {
        DataAcquisition newData = new DataAcquisition(new String[] { "SH.ACS.MONY.Q1.ZS" }, "CA", "1999", "2005");
        int expectedSize = 0;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;

    }

    @Test
    void testDataFetcherDataExistsForYear() {
        boolean actualResultFalse = DataAcquisition
                .checkifValidYear(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2014", "2019");
        boolean actualResultTrue = DataAcquisition.checkifValidYear(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

    @Test
    void testDataFetcherDatacheckIfValidData() {
        boolean actualResultFalse = DataAcquisition
                .checkIfValidData(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2017", "2019");
        boolean actualResultTrue = DataAcquisition.checkIfValidData(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

//    @Test
//    void loginReadUserTest() throws IOException {
//        LoginUI temp = new LoginUI();
//        HashMap<String, String> r = temp.readUsers();
//
//        String user = "zuhair2";
//        String pass = "Swag123!";
//        assert (r.containsKey(user));
//
//    }
//    
//    @Test
//    void loginPasswordTest() throws IOException {
//        LoginUI temp = new LoginUI();
//        String user = "zuhair2";
//        String pass = "Swag123!";
//        assert (temp.checkPassword(user, pass)==true);
//        
//        
//
//    }

    // Makes a call to the analysis strategy ratio and tests if the data matches the
    // expected data
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

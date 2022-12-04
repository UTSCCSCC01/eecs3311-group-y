package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import statsVisualiser.LoginUI;
import statsVisualiser.gui.MainUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
import visualizations.Viewer;
import visualizations.ViewerBar;
import visualizations.ViewerFactory;
import visualizations.ViewerLine;
import visualizations.ViewerMain;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class TesterFileAllCases {
    /**
     * Having pop ups show was part of the testing process to see if a
     * test case passed, CLICK "OK" or press ENTER ON EACH POPUP 
     * OTHERWISE IT WILL NOT PROCEED WITH TESTING
     * 
     * if you wish to hide the pop up test cases then
     * comment out the following test cases:
     * 
     * To run all cases it takes around 40 to 70 seconds depending on
     * your machine
     * 
     * Among these, the longest running test case takes around 6 seconds
     * So if you find your machine hanging on a test case, it might be
     * because you did not press OK on a pop up that appeared
     * 
     * removeMainUIFifthDoubleRemoval - Shows Pop up for trying to remove twice
     * loginReadUserTest - Checks if user is in database
     * loginUserValid - Checks if user and pass are valid to login
     * loginUserSignup - Checks sign up feature
     * loginUserSignupInvalid - Checks for invalid sign up
     * loginUserAlreadyExists - Checks if user already exists in sign up
     * loginUserWrongPass - Checks for wrong password
     * 
     */

    /**
     * Use case One
     * 
     * @throws Exception
     */
    @Test
    void loginReadUserTest() throws Exception {
        LoginUI.main(new String[] { "test" });
        LoginUI temp = new LoginUI("tester");
        HashMap<String, String> r = temp.readUsers();
        String user = "zuhair2";
        LoginUI.usernameField.setText(user);
        assert (r.containsKey(user));

    }

    @Test
    void loginPasswordTest() throws IOException {
        LoginUI temp = new LoginUI("tester");
        String user = "zuhair2";
        String pass = "Swag123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        assert (temp.checkPassword(user, pass) == true);

    }

    @Test
    void loginUserValid() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "nat";
        String pass = "Passy123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        assert (temp.checkPassword(user, pass) == true);
        temp.loginUser();

    }

    // need to edit to remove from json file after
    @Test
    void loginUserSignup() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "newuser24";
        String pass = "Passy123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        temp.signupUser();
        HashMap<String, String> r = temp.readUsers();
        assert (r.containsKey(user));
        // remove from json here
        temp.removeUser(user, pass);
        // check if user removed works to detect not removed
        temp.removeUser(user, pass);

    }

    @Test
    void loginUserSignupInvalid() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "randomuser";
        String pass = "badpass";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        temp.signupUser();
        HashMap<String, String> r = temp.readUsers();
        assert (r.containsKey(user) == false);
        user = "B@D U$ER";
        pass = "GoodPass123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        ActionEvent s = new ActionEvent("hi", 0, "login");
        s.setSource(LoginUI.signup);
        temp.actionPerformed(s);
        r = temp.readUsers();
        assert (r.containsKey(user) == false);

    }

    @Test
    void loginUserAlreadyExists() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "zuhair2";
        String pass = "Passy123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        temp.signupUser();
        HashMap<String, String> r = temp.readUsers();

        assert (r.containsKey(user));

    }

    @Test
    void loginUserInvalidPass() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "nat";
        String pass = "Passy123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        LoginUI.passwordField.setText("BadPass");
        ActionEvent s = new ActionEvent("hi", 0, "login");
        s.setSource(LoginUI.login);
        temp.actionPerformed(s);

    }

    @Test
    void loginUserInvalidUser() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "nat";
        String pass = "Passy123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        LoginUI.usernameField.setText("Ba");
        temp.loginUser();
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText("BadPass");
        ActionEvent s = new ActionEvent("hi", 0, "login");
        s.setSource(LoginUI.login);
        temp.actionPerformed(s);

    }

    @Test
    void loginUserWrongPass() throws Exception {
        LoginUI temp = new LoginUI("tester");
        String user = "nat";
        String pass = "wrongPass123!";
        LoginUI.usernameField.setText(user);
        LoginUI.passwordField.setText(pass);
        assert (temp.checkPassword(user, pass) == false);
        assert (temp.checkPassword("RandomUser", pass) == false);
        temp.loginUser();

    }

    /**
     * Use case Two
     */

    @Test
    void testCountryNoData() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        // indexing is differnet vs coun
        assertTrue(s.checkNullCountry(4));
        assert (s.getCountryDatabase().getCountryStorageList().get(4).getCountryCode().equals("NA"));

    }

    @Test
    void testCountryDataExist() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        // indexing is differnet vs coun
        assertFalse(s.checkNullCountry(3));

    }

    @Test
    void testCountryDataException() throws Exception {
        assertThrows("Expected readFile to throw, but it didn't",
                Exception.class,
                () -> CountryStorage.readFileAsString("Countries.json")

        );

    }

    @Test
    void testCountryDatabaseReadFile() throws Exception {
        String temp = CountryStorage.readFileAsString("src/assets/Countries.json");
        assert (temp.length() > 0);
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().size(), 258);
        assertEquals(db.getCountryStorageList().get(4).getCountryName(), "American Samoa");
        assertEquals(db.getCountryStorageList().get(4).getCountryCode(), "NA");
        assertEquals(db.getCountryStorageList().get(0).getCountryName(), "Afghanistan");
        assertEquals(db.getCountryStorageList().get(0).getCountryCode(), "AFG");
        assertEquals(db.getCountryStorageList().get(0).getStartYear(), 1962);
        assertEquals(db.getCountryStorageList().get(0).getEndYear(), 2021);
    }

    @Test
    void testDataFetcherNoIndicatorData() {
        new DataAcquisition(new String[] { "SH.ACS.MONY.Q1.ZS" }, "CA", "1999", "2005");
        int expectedSize = 0;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;

        assertThrows("Expected readFile to throw, but it didn't",
                Exception.class,
                () -> new DataAcquisition(null, null, null, null)

        );

    }

    @Test
    void testDataFetcherDatacheckIfValidData() {
        boolean actualResultFalse = DataAcquisition
                .checkIfValidData(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2017", "2019");
        boolean actualResultTrue = DataAcquisition.checkIfValidData(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

    /**
     * Use case Three
     */
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
        new AnalysisAverage();

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
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

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
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

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
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

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
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

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
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

        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();

        AnalysisEight analysisVal = new AnalysisEight();
        analysisVal.calculate();

        assertIterableEquals(Arrays.asList(graphs), Arrays.asList(analysisVal.getViewer().getGraphs()));
        assertEquals(title, analysisVal.getViewer().getTitle());
    }

    /**
     * Use case Four
     */

    @Test
    void updateYearCheck() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        new JComboBox<String>();
        s.updateAnalysis("2015", "2020", 17);
        s.updateDates("2014", "2017", 17);
        s.observerUpdate();
    }

    @Test
    void testDataFetcherValidYear() {
        new DataAcquisition(new String[] { "AG.LND.FRST.ZS" }, "CA", "2000", "2005");
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
        new DataAcquisition(new String[] { "SP.POP.TOTL" }, "BA", "2000", "2006");
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
    void testDataFetcherDataExistsForYear() {
        boolean actualResultFalse = DataAcquisition
                .checkifValidYear(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2014", "2019");
        boolean actualResultTrue = DataAcquisition.checkifValidYear(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

    @Test
    void testDataFetcherDataInvalidYear() {
        boolean actualResultTrue = DataAcquisition.checkifValidYear(new String[] { "SP.POP.TOTL" }, "USA", "2019",
                "2015");
        assert (actualResultTrue == false);
    }

    @Test
    void testDataFetcherDataExistsForAnalysis() {
        boolean actualResultFalse = DataAcquisition
                .ifSelectedIsAnnual(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2018", "2019");
        boolean actualResultTrue = DataAcquisition.ifSelectedIsAnnual(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

    @Test
    void updateYearCheckTwo() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        new JComboBox<String>();
        s.updateAnalysis("2015", "2020", 17);
        s.updateDates("2018", "2017", 17);
        // check for pop up

    }

    /**
     * Use case Five
     */
    @Test
    void addingMainUIFirst() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        new JComboBox<String>();
        s.updateAnalysis("2015", "2020", 17);

        s.recalculateButton();
        s.addGraph();
        //try adding again to test other branch
        s.addGraph();
        ChartPanel d2 = s.getViewer().getLine();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void addingMainUISecondOption() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Ratio of GDP and Renewable Energy Output";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);
        s.recalculateButton();
        s.getViewerData().setSelectedItem("Report");
        d.addItem("Report");
        s.setViewerData(d);

        s.addGraph();
        //try adding again to test other branch
        s.addGraph();
        JScrollPane d2 = s.getViewer().getReport();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void addingMainUIThirdOption() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Ratio of CO2 Emissions and GDP per capita";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);
        s.recalculateButton();
        s.getViewerData().setSelectedItem("Scatter");
        d.addItem("Scatter");
        s.setViewerData(d);

        s.addGraph();
        //try adding again to test other branch
        s.addGraph();
        ChartPanel d2 = s.getViewer().getScat();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void addingMainUIFourth() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Ratio of Population to Energy Use";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);
        s.recalculateButton();

        s.getViewerData().setSelectedItem("Bar");
        d.addItem("Bar");
        s.setViewerData(d);

        s.addGraph();
        //try adding again to test other branch
        s.addGraph();
        ChartPanel d2 = s.getViewer().getBar();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void addingMainUIFifth() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Average Forested Area";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);
        s.recalculateButton();

        s.getViewerData().setSelectedItem("Pie");
        d.addItem("Pie");
        s.setViewerData(d);

        s.addGraph();
        //try adding again to test other branch
        s.addGraph();
        ChartPanel d2 = s.getViewer().getPie();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void addingMainUIAddAll() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        JComboBox<String> d = new JComboBox<String>();
        s.updateAnalysis("2015", "2020", 10);

        s.recalculateButton();
        s.getViewerData().setSelectedItem("Scatter");
        d.addItem("Scatter");
        s.setViewerData(d);
        new ActionEvent("hi", 0, "login");
        s.addGraph();

        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Report");
        d.addItem("Report");
        s.setViewerData(d);

        s.addGraph();
        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Line");
        d.addItem("Line");
        s.setViewerData(d);

        s.addGraph();

        JScrollPane d2 = s.getViewer().getReport();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));
        ChartPanel d3 = s.getViewer().getScat();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d3));
        ChartPanel d4 = s.getViewer().getLine();
        assert (Arrays.asList(s.getMiddlePanel().getComponents()).contains(d4));
    }

    /**
     * Use case Six
     */
    @Test
    void removeMainUIFirstOption() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        // ChartPanel d = s.getViewer().getLine();
        String title = "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.getViewerData().setSelectedItem("Reset after calling obsever");
        d.addItem("Reset after calling obsever");
        s.setViewerData(d);
        // to get proper data filled
        // s.observerUpdate();
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);

        s.recalculateButton();
        s.addGraph();

        ChartPanel d2 = s.getViewer().getLine();
        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void removeMainUISecondOption() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Annual Change of Health Costs vs Air Pollution";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);

        s.recalculateButton();
        s.getViewerData().setSelectedItem("Report");
        d.addItem("Report");
        s.setViewerData(d);

        s.addGraph();

        JScrollPane d2 = s.getViewer().getReport();
        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void removeMainUIThirdOption() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Ratio of GDP and Renewable Energy Output";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);

        s.recalculateButton();
        s.getViewerData().setSelectedItem("Scatter");
        d.addItem("Scatter");
        s.setViewerData(d);

        s.addGraph();

        ChartPanel d2 = s.getViewer().getScat();
        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void removeMainUIFourth() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Ratio of CO2 Emissions and GDP per capita";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.updateAnalysis("2015", "2020", 11);
        s.setAnalysisData(d22);
        s.recalculateButton();

        s.getViewerData().setSelectedItem("Bar");
        d.addItem("Bar");
        s.setViewerData(d);

        s.addGraph();

        ChartPanel d2 = s.getViewer().getBar();
        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

    }

    @Test
    void removeMainUIFifthDoubleRemoval() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        String title = "Average Government Expenditure Education";
        JComboBox<String> d = new JComboBox<String>();
        JComboBox<String> d22 = new JComboBox<String>();
        d22.addItem(title);
        s.setAnalysisData(d22);
        s.recalculateButton();

        s.getViewerData().setSelectedItem("Pie");
        d.addItem("Pie");
        s.setViewerData(d);

        s.addGraph();

        ChartPanel d2 = s.getViewer().getPie();
        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));
        s.removeGraph();
    }

    @Test
    void removeMainUIRemoveAll() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        s.updateAnalysis("2015", "2020", 10);
        s.updateDates("2014", "2017", 10);
        JComboBox<String> d = new JComboBox<String>();
        s.recalculateButton();
        s.getViewerData().setSelectedItem("Scatter");
        d.addItem("Scatter");
        s.setViewerData(d);

        s.addGraph();

        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Report");
        d.addItem("Report");
        s.setViewerData(d);

        s.addGraph();
        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Line");
        d.addItem("Line");
        s.setViewerData(d);

        s.addGraph();

        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Line");
        d.addItem("Line");
        s.setViewerData(d);
        ChartPanel d4 = s.getViewer().getLine();
        s.removeGraph();

        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d4));

        d = new JComboBox<String>();
        s.getViewerData().setSelectedItem("Report");
        d.addItem("Report");
        s.setViewerData(d);
        JScrollPane d2 = s.getViewer().getReport();

        s.removeGraph();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d2));

        d = new JComboBox<String>();
        s.recalculateButton();
        s.getViewerData().setSelectedItem("Scatter");
        d.addItem("Scatter");
        s.setViewerData(d);
        ChartPanel d3 = s.getViewer().getScat();
        assert (!Arrays.asList(s.getMiddlePanel().getComponents()).contains(d3));

    }

    /**
     * Use case Seven
     */
// Makes a call to the analysis strategy ratio and tests if the data matches the
// expected data
    @Test
    void testAnalysisAverage() {
        String country_code = "US";
        String[][] indicatorList = new String[][] {

                { "AG.LND.FRST.ZS", "AG.LND.FRST.ZS" }, };
        ArrayList<StoredData> test = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Float> yearValues = new ArrayList<>();
        years.add(2016);
        yearValues.add(33.865105f);
        StoredData sd = new StoredData("AG.LND.FRST.ZS", yearValues, years);
        test.add(sd);
        Boolean dp = DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2011", "2020");
        assert (dp == true);
        AnalysisContext ac = new AnalysisContext(new AnalysisAverage());
        ac.execute();
        Assertions.assertEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
        new DataAcquisition(indicatorList[0], country_code, "2020", "2020");
        ac.execute();
        test = new ArrayList<>();
        years = new ArrayList<>();
        yearValues = new ArrayList<>();
        years.add(2016);
        yearValues.add(0.0f);
        sd = new StoredData("AG.LND.FRST.ZS", yearValues, years);
        Assertions.assertEquals(ac.getAnalysis().get(0).getValues().toString(), "[0.0]");

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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2018", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisAnnual());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

//Tests getData method in AnalysisContext
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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        Assertions.assertIterableEquals(ac.getData().get(0).getValues(), test.get(0).getValues());
        Assertions.assertIterableEquals(ac.getData().get(0).getYears(), test.get(0).getYears());
        Assertions.assertEquals(ac.getData().get(0).getSeriesName(), test.get(0).getSeriesName());
    }

//Makes a call to the analysis strategy ratio and tests if the data matches the expected data
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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisContext ac = new AnalysisContext(new AnalysisRatio());
        ac.execute();
        Assertions.assertIterableEquals(ac.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

    @Test
    void testAnalysisRatioSameSize() {
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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2017");
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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisFour ac = new AnalysisFour();
        ac.calculate();
        Assertions.assertIterableEquals(ac.context.getAnalysis().get(0).getValues(), test.get(0).getValues());
    }

    @Test
    void testAnalysisRatioException() {
        String[][] indicatorList = new String[][] {
                { "SH.MED.BEDS.ZS" }, };
        String country_code = "US";
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2015", "2020");
        AnalysisFour ac = new AnalysisFour();

        assertThrows("Expected Ratio to throw exception, but it didn't",
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
        DataAcquisition.ifSelectedIsAnnual(indicatorList[0], country_code, "2018", "2020");
        AnalysisOne ac = new AnalysisOne();
        ac.calculate();
        Assertions.assertIterableEquals(ac.context.getAnalysis().get(0).getValues(), test.get(0).getValues());

    }

    @Test
    void testRecalculateInvalid() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        s.updateAnalysis("2017", "2020", 2);
        s.updateDates("2020", "2020", 2);
        s.updateRecalculate();

        JComboBox<String> tester = s.getAnalysisData();
        // assertEquals(tester.getSelectedItem().toString(),"No valid analysis");
        assertEquals(tester.getItemAt(0).toString(), ("No valid analysis"));
    }

    @Test
    void testRecalculateSameYear() throws Exception {
        MainUI s = MainUI.getInstance();
        MainUI.getInstance();
        s.getInstanceTest();
        s.updateAnalysis("2017", "2020", 6);
        s.updateDates("2020", "2020", 6);
        s.updateRecalculate();

        JComboBox<String> tester = s.getAnalysisData();
        assert (!(tester.getSelectedItem().equals(null)));
    }

    /**
     * Use case Eight
     */
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
        new DataAcquisition(new String[] {
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

        new DataAcquisition(new String[] {
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

        new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2018");

        n = new ViewerBar();
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

        new DataAcquisition(new String[] {
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
        new DataAcquisition(new String[] {
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

        new DataAcquisition(new String[] {
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
    void testViewersDouble() throws Exception {
        ViewerMain temp = new ViewerMain();
        new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD" },
                "USA",
                "2010",
                "2012");
        temp = new ViewerMain(DataAcquisition.getDataStorage(), "Temp");
        temp.makeLineChart();
        temp.makeScatterChart();
        temp.makePieChart();
        temp.makeBarChart();
        temp.makeReport();
        ViewerScatter n = new ViewerScatter(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n.getChart().getChart().getXYPlot().getWeight(),
                temp.getScat().getChart().getXYPlot().getWeight());

        ViewerLine n2 = new ViewerLine(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n2.getChart().getChart().getXYPlot().getWeight(),
                temp.getLine().getChart().getXYPlot().getWeight());

        ViewerBar n3 = new ViewerBar(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");
        ViewerReport n4 = new ViewerReport(temp.getAnalysisList(),
                "Title");
        assertEquals(n4.getPanel().getName(),
                temp.getReport().getName());
        n4.seeExample();
        assertEquals(n3.getChart().getChart().getCategoryPlot().getWeight(),
                temp.getBar().getChart().getCategoryPlot().getWeight());
        

    
    }
    
    @Test
    void testViewersTriple() {
        new DataAcquisition(new String[] {
                "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" },
                "USA",
                "2010",
                "2012");
        ViewerMain temp = new ViewerMain(DataAcquisition.getDataStorage(), "Temp");
        temp.makeLineChart();
        temp.makeScatterChart();
        temp.makePieChart();
        temp.makeBarChart();
        temp.makeReport();
        ViewerScatter n = new ViewerScatter(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n.getChart().getChart().getXYPlot().getWeight(),
                temp.getScat().getChart().getXYPlot().getWeight());

        ViewerLine n2 = new ViewerLine(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n2.getChart().getChart().getXYPlot().getWeight(),
                temp.getLine().getChart().getXYPlot().getWeight());

        ViewerBar n3 = new ViewerBar(temp.getAnalysisList(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n3.getChart().getChart().getCategoryPlot().getWeight(),
                temp.getBar().getChart().getCategoryPlot().getWeight());
    }
    
    @Test
    void testViewersNull() {

        new DataAcquisition(new String[] {
                "" },
                "USA",
                "2010",
                "2012");
        ViewerMain temp = new ViewerMain(DataAcquisition.getDataStorage(), "Temp");
        temp.makeLineChart();
        temp.makeScatterChart();
        temp.makePieChart();
        temp.makeBarChart();
        temp.makeReport();
        ViewerScatter n = new ViewerScatter(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");
        ViewerLine n2 = new ViewerLine(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");
        ViewerBar n3 = new ViewerBar(DataAcquisition.getDataStorage(),
                "Title", "Label1", "Label2", "Label3", "Label4", "Label5", "Label6");

        assertEquals(n.getChart(), temp.getScat());
        assertEquals(null, temp.getPie());
        assertEquals(n3.getChart(), temp.getBar());
        assertEquals(n2.getChart(), temp.getLine());
        assertEquals(null, temp.getReport());
        ViewerFactory d = new ViewerFactory();
        Viewer s = d.CreateViewerFactory(country_code, DataAcquisition.getDataStorage(), country_code, country_code,
                country_code, country_code, country_code, country_code, country_code);
        assertEquals(null, s);
        s = d.CreateViewerFactory(null, DataAcquisition.getDataStorage(), country_code, country_code, country_code,
                country_code, country_code, country_code, country_code);

    }

}

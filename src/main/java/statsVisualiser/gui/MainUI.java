package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Map.Entry;

import countryProcess.CountryStorage;
import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import strategyAnalysis.*;
import templateAnalysis.AnalysisEight;
import templateAnalysis.AnalysisFive;
import templateAnalysis.AnalysisFour;
import templateAnalysis.AnalysisOne;
import templateAnalysis.AnalysisSeven;
import templateAnalysis.AnalysisSix;
import templateAnalysis.AnalysisThree;
import templateAnalysis.AnalysisTwo;
import visualizations.ViewerBar;
import visualizations.ViewerMain;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.category.BarRenderer;

public class MainUI extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private CountryStorage countryDB;
    private JLabel countrySelectionLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel viewerLabels;
    private JLabel analysisLabels;

    private JButton addButton;
    private JButton removeButton;
    private JButton recalculateButton;
    private JLabel loading;
    TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();
    boolean manualUpdate = false;
    private JComboBox<String> countryData;
    private JComboBox<String> startYearData;
    private JComboBox<String> endYearData;
    private JComboBox<String> viewerData;
    private JComboBox<String> analysisData;
    private ArrayList<String> countriesNames;
    private ArrayList<String> years_tmp;
    private ArrayList<String> years_tmpCopy;

    private String[] viewsAvaliable = { "Pie", "Line", "Bar", "Scatter", "Report" };
    private HashMap<String, String> analysisIndicators = new HashMap<String, String>();

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        String c = "USA";
        String[][] a = { { "SH.MED.BEDS.ZS", "SH.XPD.CHEX.PC.CD", "SP.DYN.IMRT.IN" } };

        // DataAcquisition test = new DataAcquisition(a[0], c, "2014", "2018");
        //
        // Viewer_Report tt = new Viewer_Report(test.dataStorage, "Title");
        // JScrollPane temp = tt.getPanel();
        //
        // Viewer_Scatter tt2 = new Viewer_Scatter(test.dataStorage, "Years", "Values",
        // "Title");
        // ChartPanel temp2 = tt2.getChart();

        //
        // s.panelForGraph.add(temp);
        // s.panelForGraph.add(temp2);
        // s.panelForGraph.revalidate();
        boolean userClick = false;

        TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();
        mapToPopulate.put("Annual Change of CO2 Emissions vs Energy Use vs Air Pollution",
                new String[] { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" });
        mapToPopulate.put("Annual Change in GDP per Capita and Total Population",
                new String[] { "NY.GDP.PCAP.CD", "SP.POP.TOTL" });
        mapToPopulate.put("Average Government Expenditure Education", new String[] { "SE.XPD.TOTL.GD.ZS" });
        mapToPopulate.put("Average Forested Area",
                new String[] { "AG.LND.FRST.ZS" });
        mapToPopulate.put("Ratio of CO2 Emissions and GDP per capita",
                new String[] { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" });
        mapToPopulate.put("Ratio of Population to Energy Use",
                new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" });
        mapToPopulate.put("Annual Change of Health Costs vs Air Pollution",
                new String[] { "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" });
        mapToPopulate.put("Ratio of GDP and Renewable Energy Output",
                new String[] { "NY.GDP.PCAP.CD", "EG.ELC.RNEW.ZS" });

        String selectedAnalysis = "Average Forested Area";
        String selectedCountry = "USA";
        String startYear = "2000";
        String endYear = "2020";

        ViewerBar sd = new ViewerBar();

        DataAcquisition dp4 = new DataAcquisition(mapToPopulate.get(selectedAnalysis), selectedCountry, startYear,
                endYear);
        AnalysisOne s = null;
        AnalysisTwo s2 = null;
        AnalysisThree s3 = null;
        AnalysisFour s4 = null;
        AnalysisFive s5 = null;
        AnalysisSix s6 = null;
        AnalysisSeven s7 = null;
        AnalysisEight s8 = null;
        ViewerMain viewer = new ViewerMain();
        switch (selectedAnalysis) {
            case "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution":
                // code block
                s = new AnalysisOne();
                s.calculate();
                viewer = s.getViewer();
                break;
            case "Annual Change in GDP per Capita and Total Population":
                // code block
                s2 = new AnalysisTwo();
                s2.calculate();
                viewer = s2.getViewer();
                break;
            case "Average Forested Area":
                // code block
                s3 = new AnalysisThree();
                s3.calculate();
                viewer = s3.getViewer();
                break;
            case "Ratio of CO2 Emissions and GDP per capita":
                // code block
                s4 = new AnalysisFour();
                s4.calculate();
                viewer = s4.getViewer();
                break;
            case "Average Government Expenditure Education":
                // code block
                s5 = new AnalysisFive();
                s5.calculate();
                viewer = s5.getViewer();
                break;
            case "Ratio of Population to Energy Use":
                // code block
                s6 = new AnalysisSix();
                s6.calculate();
                viewer = s6.getViewer();
                break;
            case "Annual Change of Health Costs vs Air Pollution":
                // code block
                s7 = new AnalysisSeven();
                s7.calculate();
                viewer = s7.getViewer();
                break;
            case "Ratio of GDP and Renewable Energy Output":
                // code block
                s8 = new AnalysisEight();
                s8.calculate();
                viewer = s8.getViewer();
                break;
            default:
                System.out.println("Nothing");
        }

        viewer.makeLineChart();
        ChartPanel d = viewer.getLine();
        // sd.viewPanel(d);
        ChartPanel d3 = viewer.getBar();
        // sd.viewPanel(d3);
        ChartPanel d4 = viewer.getScat();
        // sd.viewPanel(d4);
        ChartPanel d5 = viewer.getPie();
        sd.viewPanel(d5);
        JScrollPane d2 = viewer.getReport();
        // sd.viewPanelScroll(d2);
        sd.setVisible(true);

        //

        // Average: Report and Pie
        // Annual: Report, Line, Scatter
        // Ratio: Report, Line, Bar, Scatter

    }

    public void seeExample(ChartPanel chartPanel) {
        BarRenderer br = new BarRenderer();

        br.setMaximumBarWidth(.35);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static StoredData average(StoredData data) {
        StoredData res = new StoredData(data.getInd());
        float r = 0;
        int sizeOfResults = data.getValues().size() - 1;
        for (int i = 0; i < sizeOfResults; i++) {
            r = r + (data.getValues().get(i));
        }
        if (sizeOfResults > 0)
            r = r / sizeOfResults;

        res.getYears().add(data.getYears().get(sizeOfResults));
        res.getValues().add(r);
        return res;
    }

    public static StoredData calculateRatio(StoredData data, StoredData data2) {
        int small;
        String ratioLine = "Ratio of " + data.getSeriesName() + " and " + data2.getSeriesName();
        String indLine = data.getInd() + "&" + data2.getInd();
        StoredData calData = new StoredData(indLine, ratioLine);
        ArrayList<Integer> dataYears = data.getYears();
        ArrayList<Integer> data2Years = data2.getYears();
        ArrayList<Float> dataValues = data.getValues();
        ArrayList<Float> data2Values = data2.getValues();
        ArrayList<Integer> tempDataYear;
        ArrayList<Integer> tempData2Year;
        Integer year;

        if (dataYears.size() < data2Years.size()) {
            small = dataYears.size();
            tempDataYear = dataYears;
            tempData2Year = data2Years;
        } else if (dataYears.size() > data2Years.size()) {
            small = data2Years.size();
            tempDataYear = data2Years;
            tempData2Year = dataYears;
        } else {
            small = data2Years.size();
            tempDataYear = data2Years;
            tempData2Year = dataYears;
        }

        for (int i = 0; i < small; i++) {
            year = tempDataYear.get(i);

            if (tempData2Year.contains(year)) {
                if (tempDataYear == dataYears) {
                    int j = data2Years.indexOf(year);
                    float ratio = dataValues.get(i) / data2Values.get(j);
                    calData.getValues().add(ratio);
                    calData.getYears().add(year);
                } else {
                    int j = dataYears.indexOf(year);
                    float ratio = dataValues.get(j) / data2Values.get(i);
                    calData.getValues().add(ratio);
                    calData.getYears().add(year);
                }
            }
        }
        return calData;
    }

    public static ArrayList<StoredData> annualPercentageCalculator(ArrayList<StoredData> data) {
        ArrayList<StoredData> temp = new ArrayList<StoredData>();

        for (int i = 0; i < data.size(); i++) {
            StoredData resultData = new StoredData(data.get(i).getInd());
            int endYear = data.get(i).getEndYear();
            int sizeOfResults = data.get(i).getValues().size() - 1;
            for (int j = 0; j < sizeOfResults; j++) {
                Float tempForMath = data.get(i).getValues().get(j) - data.get(i).getValues().get(j + 1);
                Float tempFloat = tempForMath / data.get(i).getValues().get(j + 1) * 100;

                resultData.getYears().add(endYear - j);

                resultData.getValues().add(tempFloat);
            }
            temp.add(resultData);

        }

        return temp;

    }

    private static MainUI instance;

    public static MainUI getInstance() throws Exception {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    private MainUI() throws Exception {
        mainFrame = new JFrame();
        mainFrame.setSize(1300, 750);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // jpanel creation
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(10, 50));
        topPanel.setBackground(Color.GRAY);
        topPanel.setPreferredSize(new Dimension(10, 50));
        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        // labels
        analysisLabels = new JLabel("Choose analysis method: ");
        viewerLabels = new JLabel("Available Viewes: ");
        countrySelectionLabel = new JLabel("Choose a country: ");
        toLabel = new JLabel("To");
        fromLabel = new JLabel("From");
        loading = new JLabel("Loading...");

        // buttons
        addButton = new JButton("+");
        removeButton = new JButton("-");
        recalculateButton = new JButton("Recalculate");
        recalculateButton.setEnabled(false);

        // data call
        populateData();

        // adding to panels
        bottomPanel.add(viewerLabels);
        bottomPanel.add(viewerData);
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(Box.createHorizontalStrut(50));
        bottomPanel.add(analysisLabels);
        bottomPanel.add(analysisData);
        bottomPanel.add(recalculateButton);
        topPanel.add(countrySelectionLabel);
        topPanel.add(countryData);
        topPanel.add(loading);
        topPanel.add(Box.createHorizontalStrut(25));
        topPanel.add(fromLabel);
        topPanel.add(startYearData);
        topPanel.add(toLabel);
        topPanel.add(endYearData);

        // adding listeners
        analysisData.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        startYearData.addActionListener(this);
        endYearData.addActionListener(this);
        recalculateButton.addActionListener(this);
        countryData.addActionListener(this);

        mainFrame.setVisible(true);
        loading.setVisible(false);
        panelForGraph = new JPanel(new GridLayout(4, 4));
        panelForGraph.setVisible(true);
        panelForGraph.revalidate();
        mainFrame.add(panelForGraph, BorderLayout.CENTER);
        mainFrame.revalidate();

    }

    String[][] indicatorListForDataFetch = new String[][] {
            { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" }, { "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" },
            { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" }, { "AG.LND.FRST.ZS" }, { "SE.XPD.TOTL.GD.ZS" },
            { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" }, { "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
            { "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" },

    };

    private JPanel panelForGraph;
    private JPanel[][] secPanel = new JPanel[viewsAvaliable.length][viewsAvaliable.length];
    String[] analysisTypes = {
            "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution",
            "Annual Change in GDP per Capita and Total Population",
            "Average Government Expenditure Education",
            "Average Forested Area",
            "Ratio of CO2 Emissions and GDP per capita",
            "Ratio of Population to Energy Use",
            "Ratio of GDP and Renewable Energy Output",
            "Annual Change of Health Costs vs Air Pollution" };

    private void populateData() throws Exception {
        years_tmp = new ArrayList<String>();
        countryDB = new CountryStorage("Countries");
        countriesNames = new ArrayList<String>();
        countriesNames.add("");

        // ENVIRONMENT
        analysisIndicators.put("Total Population", "SP.POP.TOTL");
        analysisIndicators.put("CO2 emissions (metric tons per capita)", "EN.ATM.CO2E.PC");
        analysisIndicators.put("PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)",
                "EN.ATM.PM25.MC.M3");
        analysisIndicators.put("Energy use (kg of oil equivalent per capita)", "EG.USE.PCAP.KG.OE");
        analysisIndicators.put("Forest area (% of land area)", "AG.LND.FRST.ZS");
        analysisIndicators.put("GDP per capita (current US$)", "NY.GDP.PCAP.CD");

        // Health
        analysisIndicators.put("Hospital beds (per 1,000 people)", "SH.MED.BEDS.ZS");
        analysisIndicators.put("Government expenditure on education, total (% of GDP)", "SE.XPD.TOTL.GD.ZS");
        analysisIndicators.put("Maternal mortality ratio (modeled estimate, per 100,000 live births)", "SH.STA.MMRT");
        analysisIndicators.put("Current health expenditure per capita (current US$)", "SH.XPD.CHEX.PC.CD");
        analysisIndicators.put("Current health expenditure (% of GDP)", "SH.XPD.CHEX.GD.ZS");
        analysisIndicators.put("Mortality rate, infant (per 1,000 live births)", "SP.DYN.IMRT.IN");
        analysisIndicators.put("Problems in accessing health care (% of women): Q1 (lowest)", "SH.ACS.MONY.Q1.ZS");

        mapToPopulate.put("Annual Change of CO2 Emissions vs Energy Use vs Air Pollution",
                new String[] { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" });
        mapToPopulate.put("Annual Change in GDP per Capita and Total Population",
                new String[] { "NY.GDP.PCAP.CD", "SP.POP.TOTL" });
        mapToPopulate.put("Average Government Expenditure Education", new String[] { "SE.XPD.TOTL.GD.ZS" });
        mapToPopulate.put("Average Forested Area",
                new String[] { "AG.LND.FRST.ZS" });
        mapToPopulate.put("Ratio of CO2 Emissions and GDP per capita",
                new String[] { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" });
        mapToPopulate.put("Ratio of Population to Energy Use",
                new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" });
        mapToPopulate.put("Annual Change of Health Costs vs Air Pollution",
                new String[] { "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" });
        mapToPopulate.put("Ratio of GDP and Renewable Energy Output",
                new String[] { "NY.GDP.PCAP.CD", "EG.ELC.RNEW.ZS" });

        years_tmp.add("");

        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) {
            countriesNames.add(countryDB.getCountryStorageList().get(i).getCountryName());
        }

        for (int years = 1960; years <= 2021; years++) {
            years_tmp.add(years + "");
        }

        years_tmpCopy = new ArrayList<String>(years_tmp);
        countryData = new JComboBox<String>(countriesNames.toArray(new String[countriesNames.size()]));
        startYearData = new JComboBox<String>(years_tmp.toArray(new String[years_tmp.size()]));
        endYearData = new JComboBox<String>(years_tmpCopy.toArray(new String[years_tmpCopy.size()]));
        analysisData = new JComboBox<String>();
        viewerData = new JComboBox<String>();

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("IT WAS ACTIONED AGAIN AHHHHHHHHHH");
        viewerData.removeAllItems();
        if (e.getSource() == countryData || e.getSource() == startYearData || e.getSource() == endYearData) {
            if (countryData.getSelectedIndex() != 0) {
                if (manualUpdate == false) {
                    observerUpdate();
                }
            }
        } else if (e.getSource() == analysisData) {
            if (analysisData.getSelectedIndex() >= 0) {
                System.out.println(analysisData.getSelectedIndex() + "here");
                updateRecalculate();
            }
        } else if (e.getSource() == recalculateButton) {
            recalculateButton();
        }
    }

    public void observerUpdate() {
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH IT WAS CALLED AGAIN FOR SOME REASON");
        int countryIndex = 0; // index of the country in the countryDB
        String selectedStartYear = "";
        String selectedEndYear = "";
        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) {
            if (countryData.getSelectedItem().equals(countryDB.getCountryStorageList().get(i).getCountryName())) {
                countryIndex = i;
                break; // breaks the loop
            }
        }
        if (startYearData.getSelectedItem().equals("") || endYearData.getSelectedItem().equals("")) {
            System.out.println(countryDB.getCountryStorageList().get(countryIndex).getStartYear());
            System.out.println(countryDB.getCountryStorageList().get(countryIndex).getEndYear());
            selectedStartYear = countryDB.getCountryStorageList().get(countryIndex).getStartYear() + "";
            selectedEndYear = countryDB.getCountryStorageList().get(countryIndex).getEndYear() + "";
            System.out.println(selectedStartYear);
            System.out.println(selectedEndYear);

        } else {
            selectedStartYear = startYearData.getSelectedItem().toString();
            selectedEndYear = endYearData.getSelectedItem().toString();
        }

        boolean countryNull = checkNullCountry(countryIndex);
        if (countryNull == false) {
            updateDates(selectedStartYear, selectedEndYear, countryIndex);
            updateAnalysis(selectedStartYear, selectedEndYear, countryIndex);
            updateRecalculate();
        } else {
            JOptionPane.showMessageDialog(null, "No data for this country");
            countryData.setSelectedIndex(0);
        }
    }

    public boolean checkNullCountry(int countryIndex) {
        System.out.println(countryDB.getCountryStorageList().get(countryIndex).getCountryCode());
        if (countryDB.getCountryStorageList().get(countryIndex).getCountryCode().equals("NA")) {
            return true;
        }
        return false;
    }

    public void updateRecalculate() {
        System.out.println(analysisData.getSelectedItem().toString());
        if (!analysisData.getSelectedItem().toString().equals(" ")
                && !analysisData.getSelectedItem().toString().equals("No valid analysis")
                && !countryData.getSelectedItem().toString().equals(" ")
                && !startYearData.getSelectedItem().toString().equals(" ")
                && !endYearData.getSelectedItem().toString().equals(" ")) {
            recalculateButton.setEnabled(true);
        } else {
            return;
        }
    }

    public void recalculateButton() {
        ViewerBar sd = new ViewerBar();

        DataAcquisition dp4 = new DataAcquisition(mapToPopulate.get(analysisData.getSelectedItem().toString()),
                countryData.getSelectedItem().toString(), startYearData.getSelectedItem().toString(),
                endYearData.getSelectedItem().toString());
        AnalysisOne s = null;
        AnalysisTwo s2 = null;
        AnalysisThree s3 = null;
        AnalysisFour s4 = null;
        AnalysisFive s5 = null;
        AnalysisSix s6 = null;
        AnalysisSeven s7 = null;
        AnalysisEight s8 = null;
        ViewerMain viewer = new ViewerMain();
        switch (analysisData.getSelectedItem().toString()) {
            case "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution":
                // code block
                s = new AnalysisOne();
                s.calculate();
                viewer = s.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Annual Change in GDP per Capita and Total Population":
                // code block
                s2 = new AnalysisTwo();
                s2.calculate();
                viewer = s2.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Average Forested Area":
                // code block
                s3 = new AnalysisThree();
                s3.calculate();
                viewer = s3.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Ratio of CO2 Emissions and GDP per capita":
                // code block
                s4 = new AnalysisFour();
                s4.calculate();
                viewer = s4.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Average Government Expenditure Education":
                // code block
                s5 = new AnalysisFive();
                s5.calculate();
                viewer = s5.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Ratio of Population to Energy Use":
                // code block
                s6 = new AnalysisSix();
                s6.calculate();
                viewer = s6.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Annual Change of Health Costs vs Air Pollution":
                // code block
                s7 = new AnalysisSeven();
                s7.calculate();
                viewer = s7.getViewer();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            case "Ratio of GDP and Renewable Energy Output":
                // code block
                s8 = new AnalysisEight();
                s8.calculate();
                viewer = s8.getViewer();
                viewer.getArray();
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            default:
                System.out.println("Nothing");
        }

        viewer.makeLineChart();
        ChartPanel d = viewer.getLine();
        // sd.viewPanel(d);
        ChartPanel d3 = viewer.getBar();
        // sd.viewPanel(d3);
        ChartPanel d4 = viewer.getScat();
        // sd.viewPanel(d4);
        ChartPanel d5 = viewer.getPie();
        // sd.viewPanel(d5);
        JScrollPane d2 = viewer.getReport();
        // sd.viewPanelScroll(d2);
        sd.setVisible(true);

        //

        // Average: Report and Pie
        // Annual: Report, Line, Scatter
        // Ratio: Report, Line, Bar, Scatter

    }

    public void updateAnalysis(String selectedStartYear, String selectedEndYear, int countryIndex) {

        analysisData.removeAllItems();
        for (Entry<String, String[]> entry : mapToPopulate.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue()[0]);
            boolean valid = DataAcquisition.checkIfValidData(entry.getValue(),
                    countryDB.getCountryStorageList().get(countryIndex).getCountryCode(), selectedStartYear,
                    selectedEndYear);
            System.out.println(valid);
            boolean annualValid = DataAcquisition.ifSelectedIsAnnual(entry.getValue(),
                    countryDB.getCountryStorageList().get(countryIndex).getCountryCode(), selectedStartYear,
                    selectedEndYear);
            if (valid) {
                if (annualValid == true) {
                    analysisData.addItem(entry.getKey());
                } else if (annualValid == false && !entry.getKey().contains("Annual Change")) {
                    analysisData.addItem(entry.getKey());
                }
            }
        }
        if (analysisData.getItemCount() == 0) {
            analysisData.addItem("No valid analysis");
            recalculateButton.setEnabled(false);
        }
        analysisData.setSelectedIndex(0);
    }

    public void updateDates(String selectedStartYear, String selectedEndYear, int countryIndex) {
        // TODO Auto-generated method stub

        // get start and end year of country index
        int startYear = countryDB.getCountryStorageList().get(countryIndex).getStartYear();
        int endYear = countryDB.getCountryStorageList().get(countryIndex).getEndYear();
        years_tmp.clear();
        for (int year = startYear; year <= endYear; year++) {
            years_tmp.add(year + "");
        }
        years_tmpCopy = new ArrayList<String>(years_tmp);
        startYearData.setModel(new DefaultComboBoxModel<String>(years_tmp.toArray(new String[years_tmp.size()])));
        endYearData.setModel(new DefaultComboBoxModel<String>(years_tmpCopy.toArray(new String[years_tmpCopy.size()])));
        System.out.println(selectedStartYear.equals(" ") && selectedEndYear.equals(" "));
        if (selectedStartYear.equals(selectedEndYear)) {
            manualUpdate = true;
            startYearData.setSelectedIndex(0);
            endYearData.setSelectedIndex(startYearData.getSelectedIndex() + 1);
            manualUpdate = false;
        } else if (Integer.parseInt(selectedStartYear) > Integer.parseInt(selectedEndYear)) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Starting year must be lower than end year. Years have been reset to their default values."); // error
                                                                                                                  // message
            manualUpdate = true;
            startYearData.setSelectedIndex(0);
            endYearData.setSelectedIndex(startYearData.getSelectedIndex() + 1);
            manualUpdate = false;
        } else {
            manualUpdate = true;
            startYearData.setSelectedItem(selectedStartYear);
            endYearData.setSelectedItem(selectedEndYear);
            manualUpdate = false;
        }
    }

    public CountryStorage getCountryDatabase() {
        return this.countryDB;
    }

}
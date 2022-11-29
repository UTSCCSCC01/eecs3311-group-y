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
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

import analysis.*;

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
    TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();
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
        // MainUI s = MainUI.getInstance();
        //
        // s.panelForGraph.add(temp);
        // s.panelForGraph.add(temp2);
        String[] analysisTypes = {
                "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution",
                "Annual Change in GDP per Capita and Total Population",
                "Average Energy Use",
                "Average Forested Area",
                "Ratio of CO2 Emissions and GDP per capita",
                "Ratio of Population to Energy Use",
                "Ratio of GDP and Renewable Energy Output",
                "Annual Change of Health Costs vs Air Pollution"
        };

        List al = Arrays.asList(analysisTypes);

        TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();
        mapToPopulate.put("Annual Percentage Change of Total Population and CO2 Emissions",
                new String[] { "SP.POP.TOTL", "EN.ATM.CO2E.PC" });
        mapToPopulate.put("Indivuidals using the Internet", new String[] { "IT.NET.USER.ZS" });
        mapToPopulate.put("Average Energy Use", new String[] { "EG.USE.PCAP.KG.OE" });
        mapToPopulate.put("Literacy rate and Government Expenditure on Education",
                new String[] { "SE.ADT.LITR.ZS", "SE.XPD.TOTL.GB.ZS" });
        mapToPopulate.put("Ratio of Total Population to Energy Use",
                new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" });
        mapToPopulate.put("Average Government Expenditure on Education", new String[] { "SE.XPD.TOTL.GD.ZS" });
        mapToPopulate.put("Average Forested Area", new String[] { "AG.LND.FRST.ZS" });
        mapToPopulate.put("Annual Percentage Change of GDP per Capita and Total Population",
                new String[] { "SP.POP.TOTL", "NY.GDP.PCAP.CD" });

        // System.out.println(temp.dataStorage.toString());
        // System.out.println();
        // System.out.println(DataAcquisition.checkifValidYear(mapToPopulate.get("Ratio
        // of Total Population to Energy Use"),c,"2014","2019"));

        // MainUI s = MainUI.getInstance();
        // MainUI.getInstance();

        // System.out.println(dp1.dataStorage.toString());
        //
        DataAcquisition dp1 = new DataAcquisition(new String[] { "AG.LND.FRST.ZS", "EN.ATM.CO2E.PC" }, "CA", "2017",
                "2020"); // "EN.ATM.CO2E.PC"

        ArrayList<StoredData> data = DataAcquisition.getDataStorage();

        ArrayList<StoredData> temp = annualPercentageCalculator(DataAcquisition.dataStorage);
        System.out.println("Expected annual is " + temp.toString());
        System.out.println();

        StoredData sop = calculateRatio(DataAcquisition.getDataStorage().get(0),
                DataAcquisition.getDataStorage().get(1));
        System.out.println("Expected Ratio is " + sop.toString());
        System.out.println();

        StoredData d = average(DataAcquisition.getDataStorage().get(0));
        System.out.println("Expected Average is " + d.toString());
        System.out.println();

        AnalysisContext context = new AnalysisContext(new AnalysisAverage());
        context.setStrategy(new AnalysisAverage());
        context.execute();
        System.out.println("Actual Average is " + context.getAnalysis().toString());
        context = new AnalysisContext(new AnalysisRatio());
        context.setStrategy(new AnalysisRatio());
        context.execute();
        System.out.println("Actual Ratio is " + context.getAnalysis().toString());
        context = new AnalysisContext(new AnalysisAnnual());
        context.setStrategy(new AnalysisAnnual());
        context.execute();
        System.out.println("Actual Ratio is " + context.getAnalysis().toString());

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

        // buttons
        addButton = new JButton("+");
        removeButton = new JButton("-");
        recalculateButton = new JButton("Recalculate");

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
    private String[] analysisTypes = { "Average Energy Use",
            "Annual Percentage Change of Total Population and CO2 Emissions",
            "Annual Percentage Change of GDP per Capit and Total Population",
            "Ratio of Problems in Accessing Health care : Mortality rate : Infant",
            "Ratio of Hospital Beds to problems in Accessing Health Care", "Ratio of Total Population to Energy Use",
            "Ratio of CO2 Emissions and GDP Per Captia", "Ratio of Current Health Expenditure and Hospital Beds",

    };

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
        mapToPopulate.put("Average Energy Use", new String[] { "EG.USE.PCAP.KG.OE" });
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
        analysisData = new JComboBox<String>(analysisTypes);
        viewerData = new JComboBox<String>(viewsAvaliable);

    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == countryData) {
            if (countryData.getSelectedIndex() != 0) {
                observerUpdate();
            }
        }
    }

    public void observerUpdate() {
        updateDates();
        updateAnalysis();
    }

    public void updateAnalysis() {
        int countryIndex = 0; // index of the country in the countryDB
        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) {
            if (countryData.getSelectedItem().equals(countryDB.getCountryStorageList().get(i).getCountryName())) {
                countryIndex = i;
                break; // breaks the loop
            }
        }
        // get start and end year of country index
        int startYear = countryDB.getCountryStorageList().get(countryIndex).getStartYear();
        int endYear = countryDB.getCountryStorageList().get(countryIndex).getEndYear();
        ArrayList<String> validAnalysis = new ArrayList<String>();

        for (Entry<String, String[]> entry : mapToPopulate.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue()[0]);
            boolean valid = DataAcquisition.checkIfValidData(entry.getValue(),
                    countryDB.getCountryStorageList().get(countryIndex).getCountryCode(), (startYear + ""),
                    (endYear + ""));
            if (valid) {
                System.out.println("valid");
                validAnalysis.add(entry.getKey());
            } else {
                System.out.println("invalid");
            }
        }
        analysisData.removeAllItems();
        analysisData.addItem("");
        for (int i = 0; i < validAnalysis.size(); i++) {
            analysisData.addItem(validAnalysis.get(i));
        }
    }

    public void updateDates() {
        // TODO Auto-generated method stub
        int countryIndex = 0; // index of the country in the countryDB
        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) {
            if (countryData.getSelectedItem().equals(countryDB.getCountryStorageList().get(i).getCountryName())) {
                countryIndex = i;
                break; // breaks the loop
            }
        }
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
    }

    public CountryStorage getCountryDatabase() {
        return this.countryDB;
    }

}
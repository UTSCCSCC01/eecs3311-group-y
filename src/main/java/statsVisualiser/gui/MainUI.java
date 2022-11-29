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

import countryProcess.CountryStorage;
import dataFetch.DataAcquisition;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

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
        String[][] a = { { "SH.MED.BEDS.ZS", "SH.XPD.CHEX.PC.CD","SP.DYN.IMRT.IN"} };
        

//        DataAcquisition test = new DataAcquisition(a[0], c, "2014", "2018");
//
//        Viewer_Report tt = new Viewer_Report(test.dataStorage, "Title");
//        JScrollPane temp = tt.getPanel();
//
//        Viewer_Scatter tt2 = new Viewer_Scatter(test.dataStorage, "Years", "Values", "Title");
//        ChartPanel temp2 = tt2.getChart();
//        MainUI s = MainUI.getInstance();
//
//        s.panelForGraph.add(temp);
//        s.panelForGraph.add(temp2);
        String[] analysisTypes = {
                "Average Energy Use",
                "Annual Percentage Change of Total Population and CO2 Emissions",
                "Annual Percentage Change of GDP per Capit and Total Population",
                "Ratio of Problems in Accessing Health care : Mortality rate : Infant",
                "Ratio of Hospital Beds to problems in Accessing Health Care",
                "Ratio of Total Population to Energy Use",
                "Ratio of CO2 Emissions and GDP Per Captia",
                "Ratio of Current Health Expenditure and Hospital Beds",

        };
        
                
                
        
        List al = Arrays.asList(analysisTypes);
        
        TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();
        mapToPopulate.put("Annual Percentage Change of Total Population and CO2 Emissions", new String[]{"SP.POP.TOTL", "EN.ATM.CO2E.PC"});
        mapToPopulate.put("Indivuidals using the Internet", new String[]{"IT.NET.USER.ZS"});
        mapToPopulate.put("Average Energy Use", new String[]{"EG.USE.PCAP.KG.OE"});
        mapToPopulate.put("Literacy rate and Government Expenditure on Education", new String[]{"SE.ADT.LITR.ZS" , "SE.XPD.TOTL.GB.ZS"});
        mapToPopulate.put("Ratio of Total Population to Energy Use", new String[]{"SP.POP.TOTL","EG.USE.PCAP.KG.OE"});
        mapToPopulate.put("Average Government Expenditure on Education", new String[]{"SE.XPD.TOTL.GD.ZS"});
        mapToPopulate.put("Average Forested Area", new String[]{"AG.LND.FRST.ZS"});
        mapToPopulate.put("Annual Percentage Change of GDP per Capita and Total Population", new String[]{"SP.POP.TOTL", "NY.GDP.PCAP.CD"});

       DataAcquisition temp = new DataAcquisition( new String[]{"SP.POP.TOTL","EG.USE.PCAP.KG.OE"},c,"2014","2019");
        //System.out.println(temp.dataStorage.toString());
        //System.out.println();
     //  System.out.println(DataAcquisition.checkifValidYear(mapToPopulate.get("Ratio of Total Population to Energy Use"),c,"2014","2019"));
       DataAcquisition newData = new DataAcquisition(new String[]{"SP.POP.TOTL"},"BA","2000","2006");
       System.out.println(newData.dataStorage.toString());
//        MainUI s = MainUI.getInstance();
//        MainUI.getInstance();

        

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
            { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" }, 
            { "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" },
            { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" },
            { "AG.LND.FRST.ZS" },
            { "SE.XPD.TOTL.GD.ZS" },
            { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
            { "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
            { "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" },

    };

    private JPanel panelForGraph;
    private JPanel[][] secPanel = new JPanel[viewsAvaliable.length][viewsAvaliable.length];
    private String[] analysisTypes = {
            "Average Energy Use",
            "Annual Percentage Change of Total Population and CO2 Emissions",
            "Annual Percentage Change of GDP per Capit and Total Population",
            "Ratio of Problems in Accessing Health care : Mortality rate : Infant",
            "Ratio of Hospital Beds to problems in Accessing Health Care",
            "Ratio of Total Population to Energy Use",
            "Ratio of CO2 Emissions and GDP Per Captia",
            "Ratio of Current Health Expenditure and Hospital Beds",

    };
    
    
    
    
    

    private void populateData() throws Exception {
        years_tmp = new ArrayList<String>();
        countryDB = new CountryStorage();
        countriesNames = new ArrayList<String>();
        countriesNames.add("");

        // ENVIRONMENT
        analysisIndicators.put("Total Population", "SP.POP.TOTL");
        analysisIndicators.put("CO2 emissions (metric tons per capita)", "EN.ATM.CO2E.PC");
        analysisIndicators.put("PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)","EN.ATM.PM25.MC.M3");
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

    }

    public CountryStorage getCountryDatabase() {
        return this.countryDB;
    }

}
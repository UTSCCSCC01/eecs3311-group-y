package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Map.Entry;

import countryProcess.CountryStorage;
import dataFetch.DataAcquisition;
import templateAnalysis.AnalysisEight;
import templateAnalysis.AnalysisFive;
import templateAnalysis.AnalysisFour;
import templateAnalysis.AnalysisOne;
import templateAnalysis.AnalysisSeven;
import templateAnalysis.AnalysisSix;
import templateAnalysis.AnalysisThree;
import templateAnalysis.AnalysisTwo;
import visualizations.ViewerMain;

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

public class MainUI extends JFrame implements ActionListener {
    // Panel creation
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel middlePanel; // middle panel

    // Chart panels
    private ChartPanel d;
    private ChartPanel d2;
    private ChartPanel d3;
    private ChartPanel d4;
    private JScrollPane d5;

    // CountryStorage object
    private CountryStorage countryDB;

    // JLabels for UI compoennts
    private JLabel countrySelectionLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel viewerLabels;
    private JLabel analysisLabels;

    // JButtons for UI components
    private JButton addButton;
    private JButton removeButton;
    private JButton recalculateButton;

    // TreeMap for storing country names and their respective data
    TreeMap<String, String[]> mapToPopulate = new TreeMap<String, String[]>();

    boolean manualUpdate = false; // Boolean that ensures that when the dates are changed manually, the observer
                                  // is not called again
    ViewerMain viewer = new ViewerMain();

    // Combo boxes for UI components
    private JComboBox<String> countryData;
    private JComboBox<String> startYearData;
    private JComboBox<String> endYearData;
    private JComboBox<String> viewerData;
    private JComboBox<String> analysisData;
    private ArrayList<String> countriesNames;
    private ArrayList<String> years_tmp;
    private ArrayList<String> years_tmpCopy;

    private HashMap<String, String> analysisIndicators = new HashMap<String, String>(); // analysis indicators

    private static MainUI instance;

    public static MainUI getInstance() throws Exception {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    private MainUI() throws Exception {
        this.setSize(1300, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // jpanel creation
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(1300, 50));
        topPanel.setBackground(Color.GRAY);
        topPanel.setPreferredSize(new Dimension(1300, 50));
        middlePanel.setPreferredSize(new Dimension(1300, 500));
        middlePanel.setLayout(new GridLayout(2, 3));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // labels
        analysisLabels = new JLabel("Choose analysis method: ");
        viewerLabels = new JLabel("Available Viewes: ");
        countrySelectionLabel = new JLabel("Choose a country: ");
        toLabel = new JLabel("To");
        fromLabel = new JLabel("From");

        // buttons
        addButton = new JButton("+");
        addButton.setEnabled(false);
        removeButton = new JButton("-");
        removeButton.setEnabled(false);
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

        this.setVisible(true);
        this.setTitle("Main Stats Page");
        this.setLocationRelativeTo(null);
        this.pack();
        this.revalidate();

    }

    /**
     * Method that populates all the data needed for the UI to work
     *
     */
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

        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) { // loop that adds
                                                                                                     // countries to the
                                                                                                     // countriesNames
                                                                                                     // arraylist
            countriesNames.add(countryDB.getCountryStorageList().get(i).getCountryName());
        }

        for (int years = 1960; years <= 2021; years++) { // loop that adds years to the years arraylsit
            years_tmp.add(years + "");
        }

        years_tmpCopy = new ArrayList<String>(years_tmp); // copying start year data for end year data to use
        countryData = new JComboBox<String>(countriesNames.toArray(new String[countriesNames.size()])); // adding
                                                                                                        // countries to
                                                                                                        // the country
                                                                                                        // data combobox
        startYearData = new JComboBox<String>(years_tmp.toArray(new String[years_tmp.size()])); // adding years to the
                                                                                                // start year data
                                                                                                // combobox
        endYearData = new JComboBox<String>(years_tmpCopy.toArray(new String[years_tmpCopy.size()])); // adding years to
                                                                                                      // the end year
                                                                                                      // data combobox
        analysisData = new JComboBox<String>(); // setting an empty analysis data combobox for the user to select from
                                                // after they have picked a country and years
        viewerData = new JComboBox<String>(); // setting an empty viewer data combobox for the user to select from after
                                              // they have picked a country and years

    }

    public void actionPerformed(ActionEvent e) { // action listener for the UI buttons
        if (e.getSource() == countryData || e.getSource() == startYearData || e.getSource() == endYearData) { // if the
                                                                                                              // user
                                                                                                              // selects
                                                                                                              // a
                                                                                                              // country
                                                                                                              // or a
                                                                                                              // year
            if (countryData.getSelectedIndex() != 0) { // if the user has selected a country
                if (manualUpdate == false) { // prevents loops from occuring due to manually settings the start and end
                                             // year
                    observerUpdate(); // call observerUpdate which will update all observer components
                }
            }
        } else if (e.getSource() == analysisData) { // if the user selects an analysis data
            if (analysisData.getSelectedIndex() >= 0) { // if the user has selected an analysis data
                updateRecalculate(); // call updateRecalculate which will unlock the recalculate button if all UI
                                     // elements are selected
            }
        } else if (e.getSource() == recalculateButton) { // if the user clicks the recalculate button
            recalculateButton(); // call recalculateButton which will recalculate the data and update the UI
        }
        if (e.getSource() == addButton) { // if the user clicks the add button
            addGraph(); // call addGraph which will add a graph to the UI
        }
        if (e.getSource() == removeButton) { // if the user clicks the remove button
            removeGraph(); // call removeGraph which will remove a graph from the UI
        }
    }

    /**
     * This method will update the UI components that are dependent on the country,
     * start year and end year
     * Uses observer design pattern to update the UI components
     */
    public void observerUpdate() { // updates all observer components
        int countryIndex = 0; // index of the country in the countryDB
        String selectedStartYear = ""; // start year selected by the user
        String selectedEndYear = ""; // end year selected by the user
        for (int i = 0; i < Objects.requireNonNull(countryDB).getCountryStorageList().size(); i++) { // loop that finds
                                                                                                     // the index of the
                                                                                                     // country in the
                                                                                                     // countryDB
            if (countryData.getSelectedItem().equals(countryDB.getCountryStorageList().get(i).getCountryName())) { // if
                                                                                                                   // the
                                                                                                                   // country
                                                                                                                   // selected
                                                                                                                   // by
                                                                                                                   // the
                                                                                                                   // user
                                                                                                                   // is
                                                                                                                   // the
                                                                                                                   // same
                                                                                                                   // as
                                                                                                                   // the
                                                                                                                   // country
                                                                                                                   // in
                                                                                                                   // the
                                                                                                                   // countryDB
                countryIndex = i; // set the country index to the index of the country in the countryDB
                break; // breaks the loop
            }
        }
        if (startYearData.getSelectedItem().equals("") || endYearData.getSelectedItem().equals("")) { // if the user has
                                                                                                      // not selected a
                                                                                                      // start year or
                                                                                                      // end year
            selectedStartYear = countryDB.getCountryStorageList().get(countryIndex).getStartYear() + ""; // set the
                                                                                                         // start year
                                                                                                         // to the start
                                                                                                         // year of the
                                                                                                         // country in
                                                                                                         // the
                                                                                                         // countryDB
            selectedEndYear = countryDB.getCountryStorageList().get(countryIndex).getEndYear() + ""; // set the end year
                                                                                                     // to the end year
                                                                                                     // of the country
                                                                                                     // in the countryDB

        } else { // if the user has selected a start year and end year
            selectedStartYear = startYearData.getSelectedItem().toString(); // set the start year to the start year
                                                                            // selected by the user
            selectedEndYear = endYearData.getSelectedItem().toString(); // set the end year to the end year selected by
                                                                        // the user
        }

        boolean countryNull = checkNullCountry(countryIndex); // check if the country in the countryDB has any data
        if (countryNull == false) { // if the country in the countryDB has data
            updateDates(selectedStartYear, selectedEndYear, countryIndex); // call updateDates which will update the
                                                                           // start and end year data comboboxes
            updateAnalysis(selectedStartYear, selectedEndYear, countryIndex); // call updateAnalysis which will update
                                                                              // the analysis data combobox
            updateRecalculate(); // call updateRecalculate which will unlock the recalculate button if all UI
                                 // elements are selected
        } else { // if the country in the countryDB does not have data
            JOptionPane.showMessageDialog(null, "No data for this country"); // display a message to the user
            countryData.setSelectedIndex(0); // reset the country data combobox
        }
    }

    public boolean checkNullCountry(int countryIndex) { // checks if the country in the countryDB has any data
        if (countryDB.getCountryStorageList().get(countryIndex).getCountryCode().equals("NA")) { // if the country in
                                                                                                 // the countryDB does
                                                                                                 // not have data
            return true; // return true
        }
        return false; // return false
    }

    /**
     * Method that removes the selected graph
     */
    public void removeGraph() { // removes a graph from the UI

        // If statements to check if which graph is selected and remove it from the UI
        if (viewerData.getSelectedItem().toString().equals("Line")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d)) { // if the line graph is in the middle panel
                middlePanel.remove(d); // remove the line graph from the middle panel
            } else {
                JOptionPane.showMessageDialog(null, "You must remove a graph that is in the viewer"); // otherwise,
                                                                                                      // display a
                                                                                                      // message to the
                                                                                                      // user
            }
        } else if (viewerData.getSelectedItem().toString().equals("Bar")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d2)) {
                middlePanel.remove(d2);
            } else {
                JOptionPane.showMessageDialog(null, "You must remove a graph that is in the viewer");
            }
        } else if (viewerData.getSelectedItem().toString().equals("Scatter")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d3)) {
                middlePanel.remove(d3);
            } else {
                JOptionPane.showMessageDialog(null, "You must remove a graph that is in the viewer");
            }
        } else if (viewerData.getSelectedItem().toString().equals("Pie")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d4)) {
                middlePanel.remove(d4);
            } else {
                JOptionPane.showMessageDialog(null, "You must remove a graph that is in the viewer");
            }
        } else if (Arrays.asList(middlePanel.getComponents()).contains(d5)) {
            middlePanel.remove(d5);
        } else {
            JOptionPane.showMessageDialog(null, "You must remove a graph that is in the viewer");
        }

        middlePanel.revalidate(); // revalidate the middle panel
        middlePanel.repaint(); // repaint the middle panel
        this.validate();
        if (middlePanel.getComponentCount() == 0) { // if there are no graphs in the middle panel
            removeButton.setEnabled(false); // disable the remove button
        }

    }

    /**
     * Method checks whether or not the user has selected all the required UI
     * elements to recalculate the data
     */
    public void updateRecalculate() { // unlocks the recalculate button if all UI elements are selected
        if (!analysisData.getSelectedItem().toString().equals(" ")
                && !analysisData.getSelectedItem().toString().equals("No valid analysis")
                && !countryData.getSelectedItem().toString().equals(" ")
                && !startYearData.getSelectedItem().toString().equals(" ")
                && !endYearData.getSelectedItem().toString().equals(" ")) {
            recalculateButton.setEnabled(true); // enable the recalculate button
        } else {
            return; // otherwise, return
        }
    }

    /**
     * Calculates which views are available for selected analysis type
     */
    public void recalculateButton() { // recalculates the analysis data
        if (middlePanel.getComponentCount() > 0) { // if there is a graph in the middle panel
            middlePanel.removeAll(); // remove all graphs from the middle panel
            middlePanel.revalidate(); // revalidate the middle panel
            middlePanel.repaint(); // repaint the middle panel
        }
        viewerData.removeAllItems(); // remove all items from the viewer data combobox
        // Create analysis objects
        AnalysisOne s = null;
        AnalysisTwo s2 = null;
        AnalysisThree s3 = null;
        AnalysisFour s4 = null;
        AnalysisFive s5 = null;
        AnalysisSix s6 = null;
        AnalysisSeven s7 = null;
        AnalysisEight s8 = null;
        // Switch case to check which analysis is selected and create the appropriate
        // analysis object
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
                for (int i = 0; i < viewer.allowedGraphs.length; i++) {
                    viewerData.addItem(viewer.allowedGraphs[i]);
                }
                break;
            default:
        }

        addButton.setEnabled(true); // enable the add button

    }

    /**
     * Method will add a graph to the middle panel based on the user's selection
     */
    public void addGraph() {
        // Create analysis objects
        d = viewer.getLine();
        d2 = viewer.getBar();
        d3 = viewer.getScat();
        d4 = viewer.getPie();
        d5 = viewer.getReport();

        // Switch case to check which graph is selected and add the appropriate graph to
        // the middle panel
        if (viewerData.getSelectedItem().toString().equals("Line")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d)) {
                JOptionPane.showMessageDialog(null, "Graph already added");
                return;
            } else {
                middlePanel.add(d);
                middlePanel.revalidate();
                middlePanel.repaint();
                this.validate();
                removeButton.setEnabled(true);
            }
            d.setPreferredSize(new Dimension(260, 150));
            middlePanel.add(d);
        } else if (viewerData.getSelectedItem().toString().equals("Bar")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d2)) {
                JOptionPane.showMessageDialog(null, "Graph already added");
                return;
            } else {
                middlePanel.add(d2);
                middlePanel.revalidate();
                middlePanel.repaint();
                this.validate();
                removeButton.setEnabled(true);
            }
            d2.setPreferredSize(new Dimension(260, 150));
            middlePanel.add(d2);
        } else if (viewerData.getSelectedItem().toString().equals("Scatter")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d3)) {
                JOptionPane.showMessageDialog(null, "Graph already added");
                return;
            } else {
                middlePanel.add(d3);
                middlePanel.revalidate();
                middlePanel.repaint();
                this.validate();
                removeButton.setEnabled(true);
            }
            d3.setPreferredSize(new Dimension(260, 150));
            middlePanel.add(d3);
        } else if (viewerData.getSelectedItem().toString().equals("Pie")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d4)) {
                JOptionPane.showMessageDialog(null, "Graph already added");
            } else {
                middlePanel.add(d4);
                middlePanel.revalidate();
                middlePanel.repaint();
                this.validate();
                removeButton.setEnabled(true);
            }
            d4.setPreferredSize(new Dimension(260, 150));
            middlePanel.add(d4);
        } else if (viewerData.getSelectedItem().toString().equals("Report")) {
            if (Arrays.asList(middlePanel.getComponents()).contains(d5)) {
                JOptionPane.showMessageDialog(null, "Graph already added");
                return;
            } else {
                middlePanel.add(d5);
                middlePanel.revalidate();
                middlePanel.repaint();
                this.validate();
                removeButton.setEnabled(true);
            }
            d5.setPreferredSize(new Dimension(260, 150));
            middlePanel.add(d5);
        }
        // Add the graph to the middle panel
        this.add(middlePanel, BorderLayout.CENTER);
        middlePanel.repaint(); // repaint the middle panel
        this.validate();
        removeButton.setEnabled(true); // enable the remove button
    }

    /**
     * 
     * @param selectedStartYear the selected start year for the analysis
     * @param selectedEndYear   the selected end year for the analysis
     * @param countryIndex      the index of the country selected
     */
    public void updateAnalysis(String selectedStartYear, String selectedEndYear, int countryIndex) {

        analysisData.removeAllItems(); // remove all items from the analysis data combo box
        for (Entry<String, String[]> entry : mapToPopulate.entrySet()) { // loop through the map
            boolean valid = DataAcquisition.checkIfValidData(entry.getValue(),
                    countryDB.getCountryStorageList().get(countryIndex).getCountryCode(), selectedStartYear,
                    selectedEndYear); // check if the data is valid
            boolean annualValid = DataAcquisition.ifSelectedIsAnnual(entry.getValue(),
                    countryDB.getCountryStorageList().get(countryIndex).getCountryCode(), selectedStartYear,
                    selectedEndYear); // check if the data allows for annual analysis
            if (valid) { // if the data is valid
                if (annualValid == true) { // if the data allows for annual analysis
                    analysisData.addItem(entry.getKey()); // add the data to the combo box
                } else if (annualValid == false && !entry.getKey().contains("Annual Change")) { // if the data does not
                                                                                                // allow for annual
                                                                                                // analysis, add the
                                                                                                // data to the combo box
                                                                                                // that is not annual
                                                                                                // change
                    analysisData.addItem(entry.getKey()); // add the data to the combo box
                }
            }
        }
        if (analysisData.getItemCount() == 0) { // if there are no items in the combo box
            analysisData.addItem("No valid analysis"); // add the no valid analysis item
            recalculateButton.setEnabled(false); // disable the recalculate button
        }
        analysisData.setSelectedIndex(0); // set the selected index to 0
    }

    /**
     * 
     * @param selectedStartYear the selected start year for the analysis
     * @param selectedEndYear   the selected end year for the analysis
     * @param countryIndex      the index of the country selected
     */
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
        startYearData.setModel(new DefaultComboBoxModel<String>(years_tmp.toArray(new String[years_tmp.size()]))); // set
                                                                                                                   // the
                                                                                                                   // start
                                                                                                                   // year
                                                                                                                   // combo
                                                                                                                   // box
                                                                                                                   // to
                                                                                                                   // the
                                                                                                                   // years_tmp
                                                                                                                   // array
        endYearData.setModel(new DefaultComboBoxModel<String>(years_tmpCopy.toArray(new String[years_tmpCopy.size()]))); // set
                                                                                                                         // the
                                                                                                                         // end
                                                                                                                         // year
                                                                                                                         // combo
                                                                                                                         // box
                                                                                                                         // to
                                                                                                                         // the
                                                                                                                         // years_tmpCopy
                                                                                                                         // array
        if (selectedStartYear.equals(selectedEndYear)) { // if the start year is equal to the end year
            manualUpdate = true; // set manual update to true
            startYearData.setSelectedIndex(0); // set the selected index of the start year combo box to 0
            endYearData.setSelectedIndex(startYearData.getSelectedIndex() + 1); // set the selected index of the end
                                                                                // year combo box to the selected index
                                                                                // of the start year combo box + 1
            manualUpdate = false; // set manual update to false
        } else if (Integer.parseInt(selectedStartYear) > Integer.parseInt(selectedEndYear)) { // if the start year is
                                                                                              // greater than the end
                                                                                              // year
            JOptionPane.showMessageDialog(this,
                    "Starting year must be lower than end year. Years have been reset to their default values."); // error
                                                                                                                  // message
            manualUpdate = true; // set manual update to true
            startYearData.setSelectedIndex(0); // set the selected index of the start year combo box to 0
            endYearData.setSelectedIndex(startYearData.getSelectedIndex() + 1); // set the selected index of the end
                                                                                // year combo box to the selected index
                                                                                // of the start year combo box + 1
            manualUpdate = false; // set manual update to false
        } else { // if the start year is less than the end year
            manualUpdate = true; // set manual update to true
            startYearData.setSelectedItem(selectedStartYear); // set the selected item of the start year combo box to
                                                              // the selected start year
            endYearData.setSelectedItem(selectedEndYear); // set the selected item of the end year combo box to the
                                                          // selected end year
            manualUpdate = false;
        }
    }

    public CountryStorage getCountryDatabase() { // get the country database
        return this.countryDB;
    }

}
package visualizations;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import analysis.*;

import dataFetch.DataAcquisition;

import dataFetch.StoredData;

public class ViewerReport extends JFrame implements Viewer{

    JScrollPane outputScrollPane;
    ArrayList<StoredData> dataStorage;
    String title;
    

    public ViewerReport(ArrayList<StoredData> dataStorage2, String title) throws IOException {
        this.dataStorage = dataStorage2;
        this.title = title;
        pop();

    }

    public void pop() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < dataStorage.size(); i++) {
            names.add(dataStorage.get(i).getSeriesName());

        }
        
        
        TreeMap<Integer, HashMap<String, Float>> mapToPopulate = new TreeMap<Integer, HashMap<String, Float>>();

        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append(title + "==========================================\n");

        for (int i = 0; i < dataStorage.size(); i++) {
            for (int j = 0; j < dataStorage.get(i).getValues().size(); j++) {
                if (dataStorage.get(i).getValues().get(j) == null) {
                    continue;
                }
                if (!mapToPopulate.containsKey(dataStorage.get(i).getYears().get(j))) {
                    mapToPopulate.put(dataStorage.get(i).getYears().get(j), new HashMap<String, Float>());

                }
                mapToPopulate.get(dataStorage.get(i).getYears().get(j)).put(dataStorage.get(i).getSeriesName(),
                        dataStorage.get(i).getValues().get(j));

            }
        }

        String message = title + "\n" + "==============================\n";

        for (Integer d : mapToPopulate.descendingKeySet()) {
            message = message + "Year " + d + ":\n";
            HashMap<String, Float> valueForFirstKey = mapToPopulate.get(d);
            for (int i = 0; i < valueForFirstKey.size(); i++) {
                Object firstKey = mapToPopulate.get(d).keySet().toArray()[i];

                Float s = mapToPopulate.get(d).get(firstKey);
                message = message + "\t" + firstKey + "=>" + s + "\n";

            }
            message = message + "\n";

        }

        JTextArea report = new JTextArea();
        report.setText(message);

        report.setEditable(false);
        report.setLineWrap(true);
        report.setWrapStyleWord(true);
        report.setEditable(false);
        report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        report.setEditable(false);
        report.setPreferredSize(new Dimension(200, 200));

        report.setBackground(Color.white);
        outputScrollPane = new JScrollPane(report);
        outputScrollPane.createVerticalScrollBar();
        outputScrollPane.setPreferredSize(new Dimension(500, 300));
        outputScrollPane.setBackground(Color.white);
        outputScrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

// Bottom is to see it in main
         seeExample();
    }
    
    public void seeExample() {
        add(outputScrollPane);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JScrollPane getPanel() {
        return this.outputScrollPane;
    }

    public static void main(String[] args) throws IOException {
        String[][] indicatorList = new String[][] {
            { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" },
            { "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" },
            { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" },
            { "AG.LND.FRST.ZS" },
            { "SE.XPD.TOTL.GD.ZS" },
            { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
            { "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
            { "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" },
    };
    String[][] ab = { { "AG.LND.FRST.ZS", "NY.GDP.PCAP.CD" } };
    String cc = "USA";
//  DataAcquisition test = new DataAcquisition(ab[0], cc, "2010", "2010");

    String country_code = "CA";

    DataAcquisition dp1 = new DataAcquisition(indicatorList[2], country_code, "2015", "2020");
//  DataAcquisition dp = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
    ArrayList<StoredData> data = DataAcquisition.getDataStorage();
    AnalysisContext context = new AnalysisContext(new AnalysisAverage());
    context.setStrategy(new AnalysisAverage());
//  context.setStrategy(new Ratio());
    context.execute();

    ViewerFactory s = new ViewerFactory();
    ViewerPie d = (ViewerPie) s.CreateViewerFactory("Pie", context.getAnalysis(), "s", "d", "f", "dd", "bruh", "cc", country_code);
   // ViewerBar d = new ViewerBar(context.getAnalysis(), "s", "d", "f", country_code, country_code, country_code, country_code);
    d.setVisible(true);

    }

}
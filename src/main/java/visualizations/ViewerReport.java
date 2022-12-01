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

import dataFetch.DataAcquisition;

import dataFetch.StoredData;
import strategyAnalysis.*;

/**
 * Creates report and implements Viewer for
 * factory pattern
 * 
 * @author Abdul, Zuhair
 *
 */
public class ViewerReport extends JFrame implements Viewer {

    JScrollPane outputScrollPane;
    ArrayList<StoredData> dataStorage;
    String title;

    /**
     * Creates report
     * 
     * @param dataStorage data to create report from
     * @param title       name of report
     */
    public ViewerReport(ArrayList<StoredData> dataStorage2, String title) {
        this.dataStorage = dataStorage2;
        this.title = title;
        populate();

    }

    /**
     * populate report using TreeMaps
     */
    public void populate() {
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
        outputScrollPane = new JScrollPane(report,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.createVerticalScrollBar();
        outputScrollPane.setPreferredSize(new Dimension(500, 300));
        outputScrollPane.setBackground(Color.white);
        outputScrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

// Bottom is to see it in main

    }

    /**
     * see report
     */
    public void seeExample() {
        add(outputScrollPane);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * @return report
     */
    public JScrollPane getPanel() {
        return this.outputScrollPane;
    }

}
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

public class ViewerReport extends JFrame implements Viewer{

    JScrollPane outputScrollPane;
    ArrayList<StoredData> dataStorage;
    String title;
    

    public ViewerReport(ArrayList<StoredData> dataStorage, String title) throws IOException {
        this.dataStorage = dataStorage;
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
        // title will later be changed to whatever the analysis is
        String c = "USA";
        String[][] a = { { "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" } };
        DataAcquisition test = new DataAcquisition(a[0], c, "2016", "2018");

        ViewerReport tt = new ViewerReport(test.dataStorage, "Title");
        tt.setVisible(true);

    }

}
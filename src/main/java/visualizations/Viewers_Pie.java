package visualizations;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;

public class Viewers_Pie extends JFrame {
    private String data;
    DefaultPieDataset dataset;
    DefaultPieDataset temp2;
    PiePlot plot;
    LegendItemSource[] temp;
    private String title;
    private ArrayList<StoredData> dataStorage;
    private ChartPanel chartPanel;

    public Viewers_Pie(ArrayList<StoredData> dataStorage, String title) {
        if (dataStorage.equals(null)) {
            return;
        }

        this.title = title;
        this.dataStorage = dataStorage;

    }

    private PieDataset createDataset() {

        DefaultPieDataset piedataset = new DefaultPieDataset();

        data = this.dataStorage.get(0).getYears().get(0) + ": " + this.dataStorage.get(0).getValues().get(0);
        String[] s = data.split(": ");

        piedataset.setValue(this.dataStorage.get(0).getSeriesName(), Double.parseDouble(s[1]));

        piedataset.setValue("non-" + this.dataStorage.get(0).getSeriesName(), 100D - (Double.parseDouble(s[1])));

        return piedataset;
    }

    public JFreeChart helperChart() {

        PieDataset dataset1 = (PieDataset) createDataset();
        JFreeChart jfreechart = ChartFactory.createPieChart(title, dataset1, true, true, false);
        PiePlot plot = (PiePlot) jfreechart.getPlot();
        plot.setForegroundAlpha(0.3f);
        return jfreechart;
    }

    private void createChart() {
        JFreeChart chart = helperChart();
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // uncomment to see example
        seeExample();
    }

    public ChartPanel getChart() {
        return this.chartPanel;
    }

    public void seeExample() {
        add(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        String[][] a = { { "AG.LND.FRST.ZS" } };
        String c = "USA";
        DataAcquisition test = new DataAcquisition(a[0], c, "2010", "2010");
        Viewers_Pie s = new Viewers_Pie(test.dataStorage, "balls");
        s.createChart();
        s.setVisible(true);
    }

}
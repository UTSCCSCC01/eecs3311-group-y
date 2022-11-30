package visualizations;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import dataFetch.DataAcquisition;

import dataFetch.StoredData;
import strategyAnalysis.*;

/**
 * Creates pie chart and implements Viewer for
 * factory pattern
 * 
 * @author Abdul, Zuhair
 *
 */
public class ViewerPie extends JFrame implements Viewer {
    private String data, data2;
    DefaultPieDataset dataset, dataset2;
    DefaultPieDataset temp2;
    PiePlot plot;
    LegendItemSource[] temp;
    private String title;
    private ArrayList<StoredData> dataStorage;
    private ChartPanel chartPanel;
    private String title2, title3;
    private String seriesOne, seriesTwo;

    /**
     * 
     * @param dataStorage data to graph from
     * @param title       name of graph
     */
    public ViewerPie(ArrayList<StoredData> dataStorage2, String title) {
        if (dataStorage2.equals(null)) {
            return;
        }

        this.title = title;
        this.dataStorage = dataStorage2;
        // if(dataStorage.get(0).getEndYear()==dataStorage.get(0).getStartYear())
        // pop2();
        populate();
    }

    /**
     * 
     * @param dataStorage data to make graph from
     * @param title       name of graph
     * @param title2      axes label for x
     * @param title3      axes label for y
     * @param seriesName  name of first indicator
     * @param seriesName2 name of second indicator
     * @param seriesName3 name of third indicator
     */
    public ViewerPie(ArrayList<StoredData> dataStorage, String title, String title2, String title3, String seriesOne,
            String seriesTwo) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.title3 = title3;
        this.title2 = title2;
        this.title = title;
        this.seriesOne = seriesOne;
        this.seriesTwo = seriesTwo;
        this.dataStorage = dataStorage;
        if (dataStorage.get(0).getEndYear() == dataStorage.get(0).getStartYear())
            populate();

        populate2();
    }

    /**
     * default
     */
    public ViewerPie() {
        // TODO Auto-generated constructor stub
    }

    /**
     * creates data for pie
     * 
     * @return pie data
     */
    private PieDataset createDataset() {

        DefaultPieDataset piedataset = new DefaultPieDataset();

        data = this.dataStorage.get(0).getYears().get(0) + ": " + this.dataStorage.get(0).getValues().get(0);
        String[] s = data.split(": ");

        piedataset.setValue(this.dataStorage.get(0).getSeriesName(), Double.parseDouble(s[1]));

        piedataset.setValue("non-" + this.dataStorage.get(0).getSeriesName(), 100D - (Double.parseDouble(s[1])));

        return piedataset;
    }

    /**
     * Creates data set for pair
     * 
     * @return pie data
     */
    private DefaultCategoryDataset createDataset2() {
        DefaultCategoryDataset datasetNew = new DefaultCategoryDataset();

        data2 = this.dataStorage.get(1).getYears().get(0) + ": " + this.dataStorage.get(1).getValues().get(0);
        data = this.dataStorage.get(0).getYears().get(0) + ": " + this.dataStorage.get(0).getValues().get(0);
        String[] s = data.split(": ");

        String[] s2 = data2.split(": ");
        datasetNew.setValue(Double.parseDouble(s[1]), seriesOne, title2);

        datasetNew.setValue(100D - (Double.parseDouble(s[1])), "non-" + seriesOne, title2);

        datasetNew.setValue(Double.parseDouble(s2[1]), seriesTwo, title3);

        datasetNew.setValue(100D - (Double.parseDouble(s2[1])), "non-" + seriesTwo, title3);

        return datasetNew;
    }

    /**
     * Helper to create two pie
     * 
     * @return pie chart
     */
    public JFreeChart helperChart2() {

        DefaultCategoryDataset dataset2 = createDataset2();

        JFreeChart pieChart = ChartFactory.createMultiplePieChart(title, dataset2, TableOrder.BY_COLUMN, true, true,
                false);

        return pieChart;
    }

    /**
     * Helper to create one pie
     * 
     * @return pie chart
     */
    public JFreeChart helperChart() {

        PieDataset dataset1 = (PieDataset) createDataset();
        JFreeChart jfreechart = ChartFactory.createPieChart(title, dataset1, true, true, false);
        PiePlot plot = (PiePlot) jfreechart.getPlot();
        plot.setForegroundAlpha(0.3f);
        // seeExample();
        return jfreechart;
    }

    /**
     * Populate chart for two
     */
    public void populate2() {
        JFreeChart chart = helperChart2();
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // uncomment to see example
        // seeExample();
    }

    /**
     * Populate chart for one
     */
    public void populate() {
        JFreeChart chart = helperChart();
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

//        // uncomment to see example
        // seeExample();
    }

    /**
     * 
     * @return pie chart
     */
    public ChartPanel getChart() {
        return this.chartPanel;
    }

    /**
     * See pie
     */
    public void seeExample() {
        add(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * See outside pie
     * 
     * @param chartPanel chart to see
     */
    public void seeExample(ChartPanel chartPanel) {
        add(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
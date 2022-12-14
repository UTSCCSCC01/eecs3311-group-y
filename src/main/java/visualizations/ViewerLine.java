package visualizations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import strategyAnalysis.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Creates line graph and implements Viewer for
 * factory pattern
 * 
 * @author Abdul, Zuhair
 *
 */
public class ViewerLine extends JFrame implements Viewer {

    private StoredData data, data2, data3;
    private String title;
    private String series1;
    private String series2;
    private String series3;
    private ArrayList<StoredData> dataStorage;
    private String xLabel, yLabel;
    private XYSeriesCollection dataset;
    private ChartPanel chartPanel;
    private int vers;

    /**
     * 
     * @param dataStorage data to make graph from
     * @param title       name of graph
     * @param xLabel      axes label for x
     * @param yLabel      axes label for y
     * @param yLabel2     axes label two for y
     * @param seriesName  name of first indicator
     * @param seriesName2 name of second indicator
     * @param seriesName3 name of third indicator
     */
    public ViewerLine(ArrayList<StoredData> dataStorage, String title, String xLabel, String yLabel, String yLabel2,
            String seriesName, String seriesName2, String seriesName3) {

        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.title = title;
        this.series1 = seriesName;
        this.series2 = seriesName2;
        this.series3 = seriesName3;
        this.dataStorage = dataStorage;

        draw();

    }

    /**
     * draw object and call helper to populate
     */
    private void draw() {
        switch (dataStorage.size()) {
            case 0:
                break;
            case 1:
                this.data = dataStorage.get(0);
                this.vers = 1;
                break;
            case 2:
                this.data = dataStorage.get(0);
                this.data2 = dataStorage.get(1);
                this.vers = 2;
                break;
            case 3:
                this.data = dataStorage.get(0);
                this.data2 = dataStorage.get(1);
                this.data3 = dataStorage.get(2);

                this.vers = 3;
                break;

        }
        populate();

    }

    /**
     * populate object
     */
    public void populate() {
        XYSeries seriesLine, seriesLine2, seriesLine3;
        dataset = null;
        switch (vers) {
            case 0:
                break;
            case 1:
                seriesLine = new XYSeries(this.series1);
                for (int i = 0; i < data.getValues().size(); i++) {
                    seriesLine.add(data.getYears().get(i), data.getValues().get(i));
                }
                dataset = new XYSeriesCollection();
                dataset.addSeries(seriesLine);
                createChart();
                // to see uncomment this and the code in main
                // seeExample();

                break;
            case 2:
                seriesLine = new XYSeries(this.series1);
                seriesLine2 = new XYSeries(this.series2);
                for (int i = 0; i < data.getValues().size(); i++) {
                    seriesLine.add(data.getYears().get(i), data.getValues().get(i));
                }
                for (int i = 0; i < data2.getValues().size(); i++) {
                    seriesLine2.add(data2.getYears().get(i), data2.getValues().get(i));
                }
                dataset = new XYSeriesCollection();
                dataset.addSeries(seriesLine);
                dataset.addSeries(seriesLine2);
                createChart();
                // to see uncomment this and the code in main
                // seeExample();

                break;
            case 3:
                seriesLine = new XYSeries(this.series1);
                seriesLine2 = new XYSeries(this.series2);
                seriesLine3 = new XYSeries(this.series3);
                for (int i = 0; i < data.getValues().size(); i++) {
                    seriesLine.add(data.getYears().get(i), data.getValues().get(i));
                }
                for (int i = 0; i < data2.getValues().size(); i++) {
                    seriesLine2.add(data2.getYears().get(i), data2.getValues().get(i));
                }
                for (int i = 0; i < data3.getValues().size(); i++) {
                    seriesLine3.add(data3.getYears().get(i), data3.getValues().get(i));
                }
                dataset = new XYSeriesCollection();
                dataset.addSeries(seriesLine);
                dataset.addSeries(seriesLine2);
                dataset.addSeries(seriesLine3);

                createChart();
                // to see uncomment this and the code in main
                // seeExample();

                break;

        }

    }

    /**
     * Creates chart object
     */
    public void createChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(this.title, this.xLabel, this.yLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(title, new Font("Serif", java.awt.Font.BOLD, 18)));
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        final NumberAxis domainAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        final DecimalFormat format = new DecimalFormat("####");
        domainAxis.setNumberFormatOverride(format);

    }

    /**
     * 
     * @return graph
     */
    public ChartPanel getChart() {
        return this.chartPanel;
    }

    /**
     * To see chart
     */
    public void seeExample() {
        add(chartPanel);
        pack();
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
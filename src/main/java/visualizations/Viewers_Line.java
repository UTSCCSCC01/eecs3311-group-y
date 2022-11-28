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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Line viewer class which creates the line chart
 * 
 * @author group21
 *
 */
public class Viewers_Line extends JFrame {

public class Viewers_Line extends JFrame implements Viewer {

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

    public Viewers_Line(ArrayList<StoredData> dataStorage, String xLabel, String yLabel, String title) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.title = title;
        this.dataStorage = dataStorage;

        draw();

    }


    private void draw() {
        switch (dataStorage.size()) {
            case 0:
                break;
            case 1:
                this.data = dataStorage.get(0);
                this.series1 = data.getSeriesName();
                this.vers = 1;
                break;
            case 2:
                this.data = dataStorage.get(0);
                this.data2 = dataStorage.get(1);
                this.series1 = data.getSeriesName();
                this.series2 = data2.getSeriesName();
                this.vers = 2;
                break;
            case 3:
                this.data = dataStorage.get(0);
                this.data2 = dataStorage.get(1);
                this.data3 = dataStorage.get(2);
                this.series1 = data.getSeriesName();
                this.series2 = data2.getSeriesName();
                this.series3 = data3.getSeriesName();
                this.vers = 3;
                break;

        }
        pop();

    }

    public void pop() {
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
                seeExample();

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
                seeExample();

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
                seeExample();

                break;

        }

    }

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
    
    public ChartPanel getChart() {
        return this.chartPanel;
    }

    public void seeExample() {
        add(chartPanel);
        pack();
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    


    public static void main(String[] args) {
//        String c = "CA";
//        
//        DataFetcher test = new DataFetcher(a[0], c, "2000", "2021");
//        Viewers_Line testgrap = new Viewers_Line(test.dataStorage.get(0), "years", "values", "Chart", "Lol");
//        testgrap.populateLine(1);
//        testgrap.setVisible(true);

//  String[][] a = { { "EG.USE.PCAP.KG.OE" } };
        String c = "USA";
        String[][] ab = { { "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" } };
        DataAcquisition test = new DataAcquisition(ab[0], c, "2010", "2017");
        // title will later be changed to whatever the analysis is
        Viewers_Line tt = new Viewers_Line(test.dataStorage, "Years", "Values", "Title");
        tt.setVisible(true);

    }

    public StoredData getData() {
        return data;
    }

    public void setData(StoredData data) {
        this.data = data;
    }

    public StoredData getData2() {
        return data2;
    }

    public void setData2(StoredData data2) {
        this.data2 = data2;
    }

    public StoredData getData3() {
        return data3;
    }

    public void setData3(StoredData data3) {
        this.data3 = data3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries1() {
        return series1;
    }

    public void setSeries1(String series1) {
        this.series1 = series1;
    }

    public String getSeries2() {
        return series2;
    }

    public void setSeries2(String series2) {
        this.series2 = series2;
    }

    public String getSeries3() {
        return series3;
    }

    public void setSeries3(String series3) {
        this.series3 = series3;
    }

    public ArrayList<StoredData> getDataStorage() {
        return dataStorage;
    }

    public void setDataStorage(ArrayList<StoredData> dataStorage) {
        this.dataStorage = dataStorage;
    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public XYSeriesCollection getDataset() {
        return dataset;
    }

    public void setDataset(XYSeriesCollection dataset) {
        this.dataset = dataset;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public int getVers() {
        return vers;
    }

    public void setVers(int vers) {
        this.vers = vers;
    }
}
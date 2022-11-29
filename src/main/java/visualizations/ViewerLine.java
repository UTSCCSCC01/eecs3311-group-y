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

import analysis.*;
import dataFetch.DataAcquisition;
import dataFetch.StoredData;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    // ArrayList<StoredData> dataStorage, String title, String xLabel, String
    // yLabel, String yLabel2, String seriesName,
    // String seriesName2, String seriesName3
    public ViewerLine(ArrayList<StoredData> dataStorage, String title,String xLabel, String yLabel, String yLabel2,
            String seriesName, String seriesName2, String seriesName3) {
        if (dataStorage.equals(null)) {
            return;
        }

        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.title = title;
        this.series1 = seriesName;
        this.series2 = seriesName2;
        this.series3 = seriesName3;
        this.dataStorage = dataStorage;

        draw();

    }

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

    DataAcquisition dp1 = new DataAcquisition(new String[] {"EN.ATM.CO2E.PC","AG.LND.FRST.ZS"}, country_code, "2015", "2020");
//  DataAcquisition dp = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
    ArrayList<StoredData> data = DataAcquisition.getDataStorage();
    AnalysisContext context = new AnalysisContext(new AnalysisRatio());
    context.setStrategy(new AnalysisRatio());
//  context.setStrategy(new Ratio());
    context.execute();
    System.out.println(context.getData().toString());
    System.out.println(context.getAnalysis().toString());
    ViewerFactory s = new ViewerFactory();
    ViewerLine d = (ViewerLine) s.CreateViewerFactory("Line", context.getAnalysis(), "s", "d", "f", "dd", "bruh", "cc", country_code);
   // ViewerBar d = new ViewerBar(context.getAnalysis(), "s", "d", "f", country_code, country_code, country_code, country_code);
    //d.setVisible(true);

    }


}
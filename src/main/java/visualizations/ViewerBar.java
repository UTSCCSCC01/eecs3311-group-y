package visualizations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import dataFetch.*;
import strategyAnalysis.*;
import templateAnalysis.AnalysisOne;

public class ViewerBar extends JFrame implements Viewer {
    private StoredData data, data2, data3;
    private String title;
    private String series1;
    private String series2;
    private String series3;
    private ArrayList<StoredData> dataStorage;
    private String xLabel, yLabel, yLabel2;
    private int vers;
    private ChartPanel chartPanel;
    private JFreeChart barChart;
    private CategoryPlot plot;
    private String seriesName, seriesName2, seriesName3;

    
    public ViewerBar() {
        
    }
    
    // ArrayList<StoredData> dataStorage, String title, String xLabel, String
    // yLabel, String yLabel2, String seriesName,
    // String seriesName2, String seriesName3
    public ViewerBar(StoredData data, StoredData data2, StoredData data3, String title, String xLabel, String yLabel,
            String seriesName) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.yLabel2 = yLabel;
        this.title = title;
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;

        populate();
        draw();

    }

    public ViewerBar(ArrayList<StoredData> dataStorage, String title, String xLabel, String yLabel, String yLabel2,
            String seriesName, String seriesName2, String seriesName3) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.yLabel2 = yLabel2;
        this.title = title;
        this.dataStorage = dataStorage;
        this.series1 = seriesName;
        this.series2 = seriesName2;
        this.series3 = seriesName3;
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

        populate();
    }

    public void populate() {
        plot = new CategoryPlot();
        DefaultCategoryDataset dataset, dataset2, dataset3;
        BarRenderer barrenderer1, barrenderer2, barrenderer3;
        CategoryAxis domainAxis;

        switch (vers) {
            case 0:
                break;
            case 1:

                dataset = new DefaultCategoryDataset();
                for (int i = data.getValues().size() - 1; i >= 0; i--) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));
                }

                barrenderer1 = new BarRenderer();
                plot.setDataset(0, dataset);
                plot.setRenderer(0, barrenderer1);
                domainAxis = new CategoryAxis("Years");
                plot.setDomainAxis(domainAxis);
                plot.setRangeAxis(new NumberAxis(yLabel));

                plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
                plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

                createChart();
                // to see uncomment this and the code in main
               // seeExample();
                break;
            case 2:
                dataset = new DefaultCategoryDataset();
                dataset2 = new DefaultCategoryDataset();
                for (int i = data.getValues().size() - 1; i >= 0; i--) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));

                }
                for (int i = data2.getValues().size() - 1; i >= 0; i--) {
                    dataset.setValue(data2.getValues().get(i), series2, data2.getYears().get(i));
                }
                barrenderer1 = new BarRenderer();
                barrenderer2 = new BarRenderer();
                plot.setDataset(0, dataset);
                plot.setRenderer(0, barrenderer1);
                domainAxis = new CategoryAxis(xLabel);
                plot.setDomainAxis(domainAxis);
                plot.setRangeAxis(new NumberAxis(yLabel));
                plot.setDataset(1, dataset2);
                plot.setRenderer(1, barrenderer2);

                plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
                plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

                createChart();
                // to see uncomment this and the code in main
               // seeExample();

                break;
            case 3:

                dataset = new DefaultCategoryDataset();
                dataset2 = new DefaultCategoryDataset();
                dataset3 = new DefaultCategoryDataset();
                for (int i = data.getValues().size() - 1; i >= 0; i--) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));
                }

                for (int i = data2.getValues().size() - 1; i >= 0; i--) {
                    dataset2.setValue(data2.getValues().get(i), series2, data2.getYears().get(i));
                }

                for (int i = data3.getValues().size() - 1; i >= 0; i--) {
                    dataset3.setValue(data3.getValues().get(i), series3, data3.getYears().get(i));
                }

                plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
                plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

                barrenderer1 = new BarRenderer();
                barrenderer2 = new BarRenderer();
                barrenderer3 = new BarRenderer();
                plot.setDataset(0, dataset);
                plot.setRenderer(0, barrenderer1);
                domainAxis = new CategoryAxis(xLabel);
                plot.setDomainAxis(domainAxis);
                plot.setRangeAxis(new NumberAxis(yLabel));
                plot.setDataset(1, dataset2);
                plot.setDataset(2, dataset3);

                plot.setRenderer(1, barrenderer2);
                plot.setRenderer(2, barrenderer3);
                plot.setRangeAxis(1, new NumberAxis(yLabel2));

                createChart();
                // to see uncomment this and the code in main
                //seeExample();

                break;

        }

    }

    public void createChart() {
        barChart = new JFreeChart(title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
    }

    public ChartPanel getChart() {
        return this.chartPanel;
    }

    public void seeExample() {
        BarRenderer br = new BarRenderer();

        br.setMaximumBarWidth(.35);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public void viewPanel(ChartPanel chartPanel) {
        BarRenderer br = new BarRenderer();

        br.setMaximumBarWidth(.35);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void viewPanelScroll(JScrollPane outputScrollPane) {
        add(outputScrollPane);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
    }

//    public static void main(String[] args) {
//        //String[][] ab = { { "EG.USE.PCAP.KG.OE" } };
//        String c = "USA";
//        String[][] a = { { "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" } };
//        DataAcquisition test = new DataAcquisition(a[0], c, "2010", "2017");
//        // title will later be changed to whatever the analysis is
//       // ViewerBar tt = new ViewerBar(test.dataStorage.get(0), "Years", "Values", "Title", c);
//        //System.out.println(test.getDataStorage().get(0).getSeriesIndicator());
//        System.out.println(test.getDataStorage().get(0).get(0).getSeriesIndicator());
//
//    }

    public static void main(String[] args) throws IOException {
//		String[][] indicatorList = new String[][] { 
//		    { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" },
//				{ "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" }, { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" }, { "AG.LND.FRST.ZS" },
//				{ "SE.XPD.TOTL.GD.ZS" }, { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
//				{ "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
//				{ "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" }, };
//		String[][] ab = { { "AG.LND.FRST.ZS", "NY.GDP.PCAP.CD" } };
//		String cc = "USA";
////      DataAcquisition test = new DataAcquisition(ab[0], cc, "2010", "2010");

		String country_code = "USA";

		DataAcquisition dp1 = new DataAcquisition(new String[] {"EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" }, "USA", "2013", "2020");
//      DataAcquisition dp = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
		ArrayList<StoredData> data = DataAcquisition.getDataStorage();
		AnalysisContext context = new AnalysisContext(new AnalysisAnnual());
		context.setStrategy(new AnalysisAnnual());
		context.execute();

		ViewerFactory s = new ViewerFactory();
		ViewerBar d = (ViewerBar) s.CreateViewerFactory("Bar", context.getAnalysis(), "s", "d", "f", "dd", "bruh", "cc",
				country_code);
		d.setVisible(true);


    }
}
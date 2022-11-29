package visualizations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import dataFetch.DataAcquisition;
import dataFetch.StoredData;

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
    private String seriesName,seriesName2,seriesName3;

    
    public ViewerBar(StoredData data,  StoredData data2, StoredData data3, String xLabel, String yLabel, String title,
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
        
        pop();
     //  draw();

    }
    
    
    
    
    public ViewerBar(ArrayList<StoredData> dataStorage, String xLabel, String yLabel, String title,
            String seriesName) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.yLabel2 = yLabel;
        this.title = title;
        this.dataStorage = dataStorage;
        
        pop();
     //  draw();

    }

    public ViewerBar(ArrayList<StoredData> dataStorage, String xLabel, String yLabel, String yLabel2,
            String title,String seriesName,String seriesName2,String seriesName3) {
        if (dataStorage.equals(null)) {
            return;
        }
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.yLabel2 = yLabel2;
        this.title = title;
        this.dataStorage = dataStorage;
      //  draw();

    }

//    private void draw() {
//        switch (dataStorage.size()) {
//            case 0:
//                break;
//            case 1:
//                this.data = dataStorage.get(0);
//               // this.series1 = data.getSeriesName();
//                this.vers = 1;
//                break;
//            case 2:
//                this.data = dataStorage.get(0);
//                this.data2 = dataStorage.get(1);
//              //  this.series1 = data.getSeriesName();
//              //  this.series2 = data2.getSeriesName();
//                this.vers = 2;
//                break;
//            case 3:
//                this.data = dataStorage.get(0);
//                this.data2 = dataStorage.get(1);
//                this.data3 = dataStorage.get(2);
//            //   this.series1 = data.getSeriesName();
//            //    this.series2 = data2.getSeriesName();
//            //    this.series3 = data3.getSeriesName();
//                this.vers = 3;
//                break;
//
//        }
//        pop();
//    }

    public void pop() {
        plot = new CategoryPlot();
        DefaultCategoryDataset dataset, dataset2;
        BarRenderer barrenderer1, barrenderer2;
        CategoryAxis domainAxis;

        switch (vers) {
            case 0:
                break;
            case 1:

                dataset = new DefaultCategoryDataset();
                for (int i = 0; i < data.getValues().size(); i++) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));
                }
                barrenderer1 = new BarRenderer();
                plot.setDataset(0, dataset);
                plot.setRenderer(0, barrenderer1);
                domainAxis = new CategoryAxis(xLabel);
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
                for (int i = 0; i < data.getValues().size(); i++) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));
                }
                for (int i = 0; i < data2.getValues().size(); i++) {
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
                plot.setRangeAxis(1, new NumberAxis(yLabel2));

                plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
                plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

                createChart();
                // to see uncomment this and the code in main
                // seeExample();

                break;
            case 3:
                dataset = new DefaultCategoryDataset();
                dataset2 = new DefaultCategoryDataset();
                for (int i = 0; i < data.getValues().size(); i++) {
                    dataset.setValue(data.getValues().get(i), series1, data.getYears().get(i));
                }

                for (int i = 0; i < data2.getValues().size(); i++) {
                    dataset2.setValue(data2.getValues().get(i), series2, data2.getYears().get(i));
                }

                for (int i = 0; i < data3.getValues().size(); i++) {
                    dataset.setValue(data3.getValues().get(i), series3, data3.getYears().get(i));
                }

                plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
                plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

                barrenderer1 = new BarRenderer();
                barrenderer2 = new BarRenderer();
                plot.setDataset(0, dataset);
                plot.setRenderer(0, barrenderer1);
                domainAxis = new CategoryAxis(xLabel);
                plot.setDomainAxis(domainAxis);
                plot.setRangeAxis(new NumberAxis(yLabel));
                plot.setDataset(1, dataset2);
                plot.setRenderer(1, barrenderer2);
                plot.setRangeAxis(1, new NumberAxis(yLabel2));

                createChart();
                // to see uncomment this and the code in main
                // seeExample();

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

    public static void main(String[] args) {
        //String[][] ab = { { "EG.USE.PCAP.KG.OE" } };
        String c = "USA";
        String[][] a = { { "SP.DYN.IMRT.IN", "SH.XPD.CHEX.PC.CD", "SH.MED.BEDS.ZS" } };
        DataAcquisition test = new DataAcquisition(a[0], c, "2010", "2017");
        // title will later be changed to whatever the analysis is
        ViewerBar tt = new ViewerBar(test.dataStorage, "Years", "Values", "Title", c);
        tt.setVisible(true);

    }

}
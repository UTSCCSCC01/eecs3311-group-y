package templateAnalysis;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.category.BarRenderer;

import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;
import visualizations.ViewerBar;
import visualizations.ViewerFactory;
import visualizations.ViewerLine;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

public class AnalysisThree extends AnalysisTemplate {
    public String[] graphs = {"Pie","Report"};
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    String title = "Average Forested Area";
    // AG.LND.FRST.ZS
    
    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisAverage());
        context.setStrategy(new AnalysisAverage());
        context.execute();

    }

    public void makeLineChart() {

    }

    public void makeScatterChart() {
      
    }

    public void makePieChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.getAnalysis().size()) {
            case 3:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                seriesTwo = context.getAnalysis().get(1).getSeriesName();
                seriesThree = context.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                seriesTwo = context.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerPie d = (ViewerPie) s.CreateViewerFactory(
                    "Pie",
                    context.getAnalysis(),
                    "Year",
                    "Value",
                    "Value",
                    title,
                    seriesOne,
                    seriesTwo,
                    seriesThree);
            this.pieChart = d.getChart();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void makeBarChart() {
        
    }

    public void makeReport() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.getAnalysis().size()) {
            case 3:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                seriesTwo = context.getAnalysis().get(1).getSeriesName();
                seriesThree = context.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                seriesTwo = context.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerReport d = (ViewerReport) s.CreateViewerFactory(
                    "Report",
                    context.getAnalysis(),
                    "Year",
                    "Value",
                    "Value",
                    title,
                    seriesOne,
                    seriesTwo,
                    seriesThree);
            this.reportPlot = d.getPanel();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ChartPanel getLine() {
        // TODO Auto-generated method stub
        return lineChart;
    }

    public ChartPanel getScat() {
        // TODO Auto-generated method stub
        return scatterChart;
    }

    public JScrollPane getReport() {
        // TODO Auto-generated method stub
        return reportPlot;
    }

    public ChartPanel getPie() {
        // TODO Auto-generated method stub
        return pieChart;
    }

    public ChartPanel getBar() {
        // TODO Auto-generated method stub
        return barChart;
    }

}

package templateAnalysis;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.category.BarRenderer;

import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;
import visualizations.ViewerBar;
import visualizations.ViewerFactory;
import visualizations.ViewerLine;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

public class AnalysisEight extends AnalysisTemplate {
    public String[] graphs = {"Line","Scatter","Report","Bar"};
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    String title = "Ratio of GDP and Renewable Energy Output";
    // "NY.GDP.PCAP.CD" , "EG.ELC.RNEW.ZS"
    
    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisRatio());
        context.setStrategy(new AnalysisRatio());
        context.execute();

    }

    public void makeLineChart() {

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

            ViewerLine d = (ViewerLine) s.CreateViewerFactory(
                    "Line",
                    context.getAnalysis(),
                    "Year",
                    "Value",
                    "Value",
                    title,
                    seriesOne,
                    seriesTwo,
                    seriesThree);
            this.lineChart = d.getChart();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void makeScatterChart() {
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

            ViewerScatter d = (ViewerScatter) s.CreateViewerFactory(
                    "Scatter",
                    context.getAnalysis(),
                    "Year",
                    "Value",
                    "Value",
                    title,
                    seriesOne,
                    seriesTwo,
                    seriesThree);
            this.scatterChart = d.getChart();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

            ViewerBar d = (ViewerBar) s.CreateViewerFactory(
                    "Bar",
                    context.getAnalysis(),
                    "Year",
                    "Value",
                    "Value",
                    title,
                    seriesOne,
                    seriesTwo,
                    seriesThree);
            this.barChart = d.getChart();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        return new ChartPanel(null);
    }

    public ChartPanel getBar() {
        // TODO Auto-generated method stub
        return barChart;
    }

}

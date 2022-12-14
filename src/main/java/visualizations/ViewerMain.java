package visualizations;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

import dataFetch.StoredData;
import strategyAnalysis.AnalysisContext;

/**
 * Main that calls on factory and gets called by the analysis templates
 * to create graphs. This stores graphs
 * 
 * @author Abdul, Zuhair
 *
 */
public class ViewerMain {
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public ArrayList<StoredData> context;
    public String title;
    public String[] allowedGraphs;

    /**
     * Creates graphs and figures out which to create
     * 
     * @param analysis      data to create from
     * @param title         names of graphs
     * @param allowedGraphs which graphs are allowed to use
     */
    public ViewerMain(ArrayList<StoredData> analysis, String title, String[] allowedGraphs) {
        this.context = analysis;
        this.title = title;
        this.allowedGraphs = allowedGraphs;
        for (int i = 0; i < allowedGraphs.length; i++) {
            System.out.println(allowedGraphs[i]);
            if (allowedGraphs[i].equals("Pie"))
                this.makePieChart();
            if (allowedGraphs[i].equals("Bar"))
                this.makeBarChart();
            if (allowedGraphs[i].equals("Scatter"))
                this.makeScatterChart();
            if (allowedGraphs[i].equals("Report"))
                this.makeReport();
            if (allowedGraphs[i].equals("Line")) {
                this.makeLineChart();
            }

        }

    }

    /**
     * 
     * @param analysis data
     * @param title    name of
     */
    public ViewerMain(ArrayList<StoredData> analysis, String title) {
        // TODO Auto-generated constructor stub
        this.context = analysis;
        this.title = title;
    }

    /**
     * default
     */
    public ViewerMain() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Make line chart
     */
    public void makeLineChart() {

        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.size()) {
            case 3:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                seriesThree = context.get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.get(0).getSeriesName();
                break;
            default:
                return;
        }

        ViewerLine d = (ViewerLine) s.CreateViewerFactory(
                "Line",
                context,
                "Year",
                "Value",
                "Value",
                title,
                seriesOne,
                seriesTwo,
                seriesThree);
        this.lineChart = d.getChart();

    }

    /**
     * Make scatter chart
     */
    public void makeScatterChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.size()) {
            case 3:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                seriesThree = context.get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.get(0).getSeriesName();
                break;
            default:
                return;
       
        }

        ViewerScatter d = (ViewerScatter) s.CreateViewerFactory(
                "Scatter",
                context,
                "Year",
                "Value",
                "Value",
                title,
                seriesOne,
                seriesTwo,
                seriesThree);
        this.scatterChart = d.getChart();
    }

    /**
     * Make pie chart
     */
    public void makePieChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.size()) {
            case 3:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                seriesThree = context.get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.get(0).getSeriesName();
                break;
            default:
                return;
        }

        ViewerPie d = (ViewerPie) s.CreateViewerFactory(
                "Pie",
                context,
                "Year",
                "Value",
                "Value",
                title,
                seriesOne,
                seriesTwo,
                seriesThree);
        this.pieChart = d.getChart();

    }

    /**
     * Make bar chart
     */
    public void makeBarChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.size()) {
            case 3:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                seriesThree = context.get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.get(0).getSeriesName();
                break;
            default:
                return;
        }

        ViewerBar d = (ViewerBar) s.CreateViewerFactory(
                "Bar",
                context,
                "Year",
                "Value",
                "Value",
                title,
                seriesOne,
                seriesTwo,
                seriesThree);
        this.barChart = d.getChart();
    }

    /**
     * Make report
     */
    public void makeReport() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (context.size()) {
            case 3:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                seriesThree = context.get(2).getSeriesName();
                break;
            case 2:
                seriesOne = context.get(0).getSeriesName();
                seriesTwo = context.get(1).getSeriesName();
                break;
            case 1:
                seriesOne = context.get(0).getSeriesName();
                break;
            default:
                return;
        }

        ViewerReport d = (ViewerReport) s.CreateViewerFactory(
                "Report",
                context,
                "Year",
                "Value",
                "Value",
                title,
                seriesOne,
                seriesTwo,
                seriesThree);
        this.reportPlot = d.getPanel();
    }

    /**
     * 
     * @return line chart
     */
    public ChartPanel getLine() {
        // TODO Auto-generated method stub
        return lineChart;
    }

    /**
     * 
     * @return scatter chart
     */
    public ChartPanel getScat() {
        // TODO Auto-generated method stub
        return scatterChart;
    }

    /**
     * 
     * @return report
     */
    public JScrollPane getReport() {
        // TODO Auto-generated method stub
        return reportPlot;
    }

    /**
     * 
     * @return pie chart
     */
    public ChartPanel getPie() {
        // TODO Auto-generated method stub
        return pieChart;
    }

    /**
     * 
     * @return bar chart
     */
    public ChartPanel getBar() {
        // TODO Auto-generated method stub
        return barChart;
    }


    // Retrieves the ViewerMain objects allowed graphs
    public String[] getGraphs(){
        return this.allowedGraphs;
    }

    // Retrieves the title of the ViewerMain object
    public String getTitle(){
        return this.title;
    }

    // Retrieves the AnalysisList of the ViewerMain Object
    public ArrayList<StoredData> getAnalysisList(){
        return this.context;
    }

}

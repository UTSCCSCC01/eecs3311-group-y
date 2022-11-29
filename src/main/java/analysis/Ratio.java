package analysis;

import dataFetch.ParsedData;
import dataFetch.StoredData;
import visualizations.ViewerBar;
import visualizations.ViewerFactory;
import visualizations.ViewerLine;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * TODO get rid of these notes when done
 * represent as two objects
 * {
 * indicator1: ratio
 * indicator2: value
 * }
 */
public class Ratio implements analysisStrategy {
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;

    ArrayList<StoredData> res = new ArrayList<>();

//    class yearAndRatio{
//        int year;
//
//        float ratio;
//
//        public yearAndRatio(int year, float ratio){
//            this.year = year;
//            this.ratio = ratio;
//        }
//        public int getYear(){
//            return this.year;
//        }
//        public float getRatio(){
//            return this.ratio;
//        }
//    }
    @Override
    public void performAnalysis(analysisContext context) {
        ArrayList<StoredData> sd = new ArrayList<>();

        if (context.getData().size() % 2 == 0) {
            // Scans all input tables
            for (int i = 0; i < context.getData().size(); i += 2) {
                ArrayList<Integer> years = new ArrayList<>();
                ArrayList<Float> calculatedValues = new ArrayList<>();

                ParsedData dataSubset = context.getData().get(i).get(0);
                ParsedData dataTotal = context.getData().get(i + 1).get(0);
                float ratio = dataSubset.getYearValues() / dataTotal.getYearValues();
                int curYear = dataSubset.getYears();
                years.add(curYear);
                calculatedValues.add(ratio);
                StoredData p = new StoredData("", calculatedValues, years);
                sd.add(p);
            }
        } else {
            throw new IllegalArgumentException("There must be two pieces of data for ratioso");
        }
        setAnalysis(sd);
    }

    public void setAnalysis(ArrayList<StoredData> sd) {
        this.res = sd;
    }

    public ArrayList<StoredData> getAnalysis() {
        return this.res;
    }

    // ArrayList<StoredData> dataStorage,
    // String title,
    // String xLabel, String yLabel, String yLabel2,
    // String seriesName,
    // String seriesName2, String seriesName3
    public void makeLineChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (this.getAnalysis().size()) {
            case 3:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                seriesThree = this.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerLine d = (ViewerLine) s.CreateViewerFactory(
                    "Line",
                    this.getAnalysis(),
                    "Ratio Of", // Need to figure out title
                    "Year",
                    "Value",
                    "Value",
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

        switch (this.getAnalysis().size()) {
            case 3:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                seriesThree = this.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerScatter d = (ViewerScatter) s.CreateViewerFactory(
                    "Scatter",
                    this.getAnalysis(),
                    "Ratio Of", // Need to figure out title
                    "Year",
                    "Value",
                    "Value",
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
        // cant make
        

    }

    public void makeBarChart() {
        ViewerFactory s = new ViewerFactory();
        String seriesOne = "";
        String seriesTwo = "";
        String seriesThree = "";

        switch (this.getAnalysis().size()) {
            case 3:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                seriesThree = this.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerBar d = (ViewerBar) s.CreateViewerFactory(
                    "Bar",
                    this.getAnalysis(),
                    "Ratio Of", // Need to figure out title
                    "Year",
                    "Value",
                    "Value",
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

        switch (this.getAnalysis().size()) {
            case 3:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                seriesThree = this.getAnalysis().get(2).getSeriesName();
                break;
            case 2:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                seriesTwo = this.getAnalysis().get(1).getSeriesName();
                break;
            case 1:
                seriesOne = this.getAnalysis().get(0).getSeriesName();
                break;
            default:
                // code block
        }

        try {

            ViewerReport d = (ViewerReport) s.CreateViewerFactory(
                    "Report",
                    this.getAnalysis(),
                    "Ratio Of", // Need to figure out title
                    "Year",
                    "Value",
                    "Value",
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
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
import visualizations.ViewerMain;
import visualizations.ViewerPie;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;

public class AnalysisFour extends AnalysisTemplate {
    public String[] graphs = {"Line","Scatter","Report","Bar"};
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    ViewerMain viewer;
    String title = "Ratio of CO2 Emissions and GDP per capita";
    //"EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD"
    // Average: Report and Pie
    // Annual: Report, Line, Scatter
    // Ratio: Report, Line, Bar, Scatter
    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisRatio());
        context.setStrategy(new AnalysisRatio());
        context.execute();
        this.viewer = new ViewerMain(context.getAnalysis(), title, graphs);

    }

    public ViewerMain getViewer() {
        return this.viewer;
    }
   
}

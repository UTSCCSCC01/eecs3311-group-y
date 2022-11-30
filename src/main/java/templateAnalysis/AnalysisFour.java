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

/**
 * Template for Analysis, has specifications for creating different
 * viewers and does the specific math for the analysis as well
 * 
 * @author Abdul
 *
 */
public class AnalysisFour extends AnalysisTemplate {
    public String[] graphs = { "Line", "Scatter", "Report", "Bar" };
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    ViewerMain viewer;
    String title = "Ratio of CO2 Emissions and GDP per capita";

    /**
     * Perform Ratio calculations
     */
    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisRatio());
        context.setStrategy(new AnalysisRatio());
        context.execute();
        this.viewer = new ViewerMain(context.getAnalysis(), title, graphs);

    }

    /**
     * 
     * @return ViewerMain object that stores Graphs
     */
    @Override
    public ViewerMain getViewer() {
        return this.viewer;
    }

}

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
import visualizations.ViewerBar;
import visualizations.ViewerFactory;
import visualizations.ViewerLine;
import visualizations.ViewerMain;
import visualizations.ViewerReport;
import visualizations.ViewerScatter;
/**
 * Template for Analysis, has specifications for creating different 
 * viewers and does the specific math for the analysis as well
 * 
 * @author Abdul
 *
 */
public class AnalysisOne extends AnalysisTemplate {
    public String[] graphs = { "Line", "Scatter", "Report" };
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    ViewerMain viewer;
    String title = "Annual Change of CO2 Emissions vs Energy Use vs Air Pollution";
    
    

    /**
     * Perform Annual calculations
     */
    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisAnnual());
        context.setStrategy(new AnalysisAnnual());
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

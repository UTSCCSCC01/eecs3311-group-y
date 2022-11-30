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

public class AnalysisSeven extends AnalysisTemplate {
    public String[] graphs = { "Line", "Scatter", "Report" };
    private ChartPanel barChart, pieChart, lineChart, scatterChart;
    private JScrollPane reportPlot;
    public AnalysisContext context;
    ViewerMain viewer;
    String title = "Annual Change of Health Costs vs Air Pollution";
    // "EN.ATM.PM25.MC.M3" , "AG.LND.FRST.ZS"

    @Override
    public void calculate() {
        context = new AnalysisContext(new AnalysisAnnual());
        context.setStrategy(new AnalysisAnnual());
        context.execute();
        this.viewer = new ViewerMain(context.getAnalysis(), title, graphs);

    }

    public ViewerMain getViewer() {
        return this.viewer;
    }

}

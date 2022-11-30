package templateAnalysis;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import visualizations.ViewerBar;
import visualizations.ViewerReport;
import visualizations.ViewerLine;
import visualizations.ViewerMain;
import visualizations.ViewerPie;
import visualizations.ViewerScatter;

/**
 * Template for all analysis to abide by
 * 
 * @author Abdul
 *
 */
abstract class AnalysisTemplate {
    String[] viewerTypes;
    String xAxis;
    String yXais;
    String title;
    ViewerMain s;

    /**
     * Does optional calculations on raw data,
     * this is akin to template
     */
    public abstract void calculate();

    public ViewerMain getViewer() {
        return s;

    }

}

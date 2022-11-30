package templateAnalysis;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import visualizations.ViewerBar;
import visualizations.ViewerReport;
import visualizations.ViewerLine;
import visualizations.ViewerPie;
import visualizations.ViewerScatter;

abstract class AnalysisTemplate {
    String[] viewerTypes;
    String xAxis;
    String yXais;
    String title;
    
    /**
     * Does optional calculations on raw data - different for each anaylsis class. This represents a strategy design pattern  
     */
    public abstract void calculate();
    
 
}

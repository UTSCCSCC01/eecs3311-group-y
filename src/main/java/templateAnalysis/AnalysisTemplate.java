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
    
    /**
     * makes a line chart 
     */
    public abstract void makeLineChart();
    
    /**
     * makes a scatter chart 
     */
    public abstract void makeScatterChart();
    
    /**
     * makes pie chart 
     */
    public abstract void makePieChart();
    
    
    /**
     * makes bar chart 
     */
    public abstract void makeBarChart();
    
    /**
     * returns viewer object for line graph 
     * @return
     */
    public abstract ChartPanel getLine();
    /**
     * returns viewer object for scatter graph 
     * @return
     */
    public abstract ChartPanel getScat();
    /**
     * returns viewer object for area graph 
     * @return
     */
    public abstract ChartPanel getPie();
    /**
     * returns viewer object for bar graph 
     * @return
     */
    public abstract ChartPanel getBar();
    /**
     * returns viewer object for report
     * @return
     */
    public abstract JScrollPane getReport();
}

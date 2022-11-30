package visualizations;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import strategyAnalysis.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

public class ViewerScatter extends JFrame implements Viewer {

	private static final long serialVersionUID = 6043180864004509049L;
	private ArrayList<StoredData> dataStorage;
	private StoredData data, data2, data3;
	private String title;
	private String xLabel, yLabel, yLabel2;
	public String series1;
	private String series2;
	private String series3;
	private JFreeChart scatterChart;
	private ChartPanel chartPanel;
	private int vers;
	private XYPlot plot;

	public ViewerScatter(ArrayList<StoredData> dataStorage, String xLabel, String yLabel, String title) {
		if (dataStorage.equals(null)) {
			return;
		}
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.yLabel2 = yLabel;
		this.title = title;
		this.dataStorage = dataStorage;
		draw();

	}

	public ViewerScatter(ArrayList<StoredData> dataStorage, String title, String xLabel, String yLabel, String yLabel2,
			String seriesName, String seriesName2, String seriesName3) {
		if (dataStorage.equals(null)) {
			return;
		}
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.yLabel2 = yLabel2;
		this.title = title;
		this.dataStorage = dataStorage;
		this.series1 = seriesName;
		this.series2 = seriesName2;
		this.series3 = seriesName3;
		draw();

	}

//
	private void draw() {
		switch (dataStorage.size()) {
		case 0:
			break;
		case 1:
			this.data = dataStorage.get(0);
			// this.series1 = data.getSeriesName();
			this.vers = 1;
			break;
		case 2:
			this.data = dataStorage.get(0);
			this.data2 = dataStorage.get(1);
			// this.series1 = data.getSeriesName();
			// this.series2 = data2.getSeriesName();
			this.vers = 2;
			break;
		case 3:
			this.data = dataStorage.get(0);
			this.data2 = dataStorage.get(1);
			this.data3 = dataStorage.get(2);
			// this.series1 = data.getSeriesName();
			// this.series2 = data2.getSeriesName();
			// this.series3 = data3.getSeriesName();
			this.vers = 3;
			break;

		}
		populate();
	}

	public void populate() {
		TimeSeries scatterSeries1, scatterSeries2, scatterSeries3;
		TimeSeriesCollection dataset, dataset2;
		XYItemRenderer itemrenderer1, itemrenderer2;
		DateAxis domainAxis;

		switch (vers) {
		case 0:
			break;
		case 1:
			scatterSeries1 = new TimeSeries(series1);
			for (int i = 0; i < data.getValues().size(); i++) {
				scatterSeries1.add(new Year(data.getYears().get(i)), data.getValues().get(i));
			}
			dataset = new TimeSeriesCollection();
			dataset.addSeries(scatterSeries1);
			plot = new XYPlot();
			itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			domainAxis = new DateAxis(xLabel);
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(yLabel));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			scatterChart = new JFreeChart(title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			createChart();
			// to see uncomment this and the code in main
		//	seeExample();

			break;
		case 2:

			scatterSeries1 = new TimeSeries(series1);
			scatterSeries2 = new TimeSeries(series2);

			for (int i = 0; i < data.getValues().size(); i++) {
				scatterSeries1.add(new Year(data.getYears().get(i)), data.getValues().get(i));
			}
			for (int i = 0; i < data2.getValues().size(); i++) {
				scatterSeries2.add(new Year(data2.getYears().get(i)), data2.getValues().get(i));
			}
			dataset = new TimeSeriesCollection();
			dataset.addSeries(scatterSeries1);

			dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(scatterSeries2);

			plot = new XYPlot();
			itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			domainAxis = new DateAxis(this.xLabel);
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(this.yLabel));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(this.yLabel2));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			scatterChart = new JFreeChart(title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			createChart();
			// to see uncomment this and the code in main
		//	seeExample();

			break;
		case 3:
			scatterSeries1 = new TimeSeries(series1);
			scatterSeries2 = new TimeSeries(series2);
			scatterSeries3 = new TimeSeries(series3);

			for (int i = 0; i < data.getValues().size(); i++) {
				scatterSeries1.add(new Year(data.getYears().get(i)), data.getValues().get(i));
			}
			for (int i = 0; i < data2.getValues().size(); i++) {
				scatterSeries2.add(new Year(data2.getYears().get(i)), data2.getValues().get(i));
			}
			for (int i = 0; i < data3.getValues().size(); i++) {
				scatterSeries3.add(new Year(data3.getYears().get(i)), data3.getValues().get(i));
			}
			dataset = new TimeSeriesCollection();
			dataset.addSeries(scatterSeries1);

			dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(scatterSeries2);

			plot = new XYPlot();
			itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			dataset.addSeries(scatterSeries3);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			domainAxis = new DateAxis(this.xLabel);
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(this.yLabel));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(this.yLabel2));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			createChart();
			// to see uncomment this and the code in main
		//	seeExample();

			break;

		}

	}

	public void createChart() {
		scatterChart = new JFreeChart(title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		chartPanel = new ChartPanel(scatterChart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
	}

	public void seeExample() {

		add(chartPanel);

		pack();
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public ChartPanel getChart() {
		return this.chartPanel;
	}

	public static void main(String[] args) throws IOException {
		String[][] indicatorList = new String[][] { { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" },
				{ "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" }, { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" }, { "AG.LND.FRST.ZS" },
				{ "SE.XPD.TOTL.GD.ZS" }, { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
				{ "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
				{ "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" }, };
		String[][] ab = { { "AG.LND.FRST.ZS", "NY.GDP.PCAP.CD" } };
		String cc = "USA";
//  DataAcquisition test = new DataAcquisition(ab[0], cc, "2010", "2010");

		String country_code = "CA";

		DataAcquisition dp1 = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
//  DataAcquisition dp = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
		ArrayList<StoredData> data = DataAcquisition.getDataStorage();
		AnalysisContext context = new AnalysisContext(new AnalysisAnnual());
		context.setStrategy(new AnalysisAnnual());
//  context.setStrategy(new Ratio());
		context.execute();

		ViewerFactory s = new ViewerFactory();
		ViewerScatter d = (ViewerScatter) s.CreateViewerFactory("Scatter", context.getAnalysis(), "s", "d", "f", "dd",
				"bruh", "cc", country_code);
		// ViewerBar d = new ViewerBar(context.getAnalysis(), "s", "d", "f",
		// country_code, country_code, country_code, country_code);
		d.setVisible(true);

	}

}
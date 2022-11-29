package dataFetch;

import java.util.ArrayList;

import analysis.AnnualPercentageChange;
import analysis.Average;
import analysis.Ratio;
import dataFetch.DataAcquisition;
import analysis.analysisContext;
import analysis.StoredDataAdapter;

public class testFetcher {
    public static void main(String[] args) {
		String[][] indicatorList = new String[][] {
				{ "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" },
				{ "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" },
				{ "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" },
				{ "AG.LND.FRST.ZS" },
				{ "SE.XPD.TOTL.GD.ZS" },
				{ "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
				{ "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
				{ "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" },
		};
		String[][] ab = { { "AG.LND.FRST.ZS" ,"NY.GDP.PCAP.CD"} };
		String cc = "USA";
//		DataAcquisition test = new DataAcquisition(ab[0], cc, "2010", "2010");

		String country_code = "CA";


		DataAcquisition dp1 = new DataAcquisition(indicatorList[3], country_code, "2015", "2020");
//		DataAcquisition dp = new DataAcquisition(indicatorList[0], country_code, "2015", "2020");
		ArrayList<ArrayList<ParsedData>> data = DataAcquisition.getDataStorage();
		analysisContext context = new analysisContext(new AnnualPercentageChange());
		context.setStrategy(new Average());
//		context.setStrategy(new Ratio());
		context.execute();
		System.out.println(context.getAnalysis() + " this is the final call");

	}
}

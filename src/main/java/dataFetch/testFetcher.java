package dataFetch;

public class testFetcher {
    public static void main(String[] args) {
		String[][] indicatorList = new String[][] { { "EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3" },
				{ "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS" }, { "EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD" }, { "AG.LND.FRST.ZS" },
				{ "SE.XPD.TOTL.GD.ZS" }, { "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS" },
				{ "SH.XPD.CHEX.GD.ZS", "NY.GDP.PCAP.CD", "SP.DYN.IMRT.IN" },
				{ "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS" },

		};

		String country_code = "CA";

		DataFetcher dp = new DataFetcher(indicatorList[1], country_code, "2015", "2020");
		System.out.println(dp.dataStorage.toString());

	}
}

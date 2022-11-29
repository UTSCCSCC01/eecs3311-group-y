package dataFetch;

import java.util.ArrayList;
import java.util.HashMap;

public class StoredData {

    String seriesIndicator;
    ArrayList<Float> yearvalues;
    ArrayList<Integer> years;
    String country;
    String seriesName;
    HashMap<String, String> analysisIndicators = new HashMap<String, String>();
    HashMap<String, String> axisLabels = new HashMap<String, String>();

    public StoredData(String ind, ArrayList<Float> yearvalues, ArrayList<Integer> years) {
        this.seriesIndicator = ind;
        this.yearvalues = yearvalues;
        this.years = years;
        this.populate();
        if (analysisIndicators.containsKey(ind))
            this.seriesName = analysisIndicators.get(ind);
        else
            this.seriesName = "Cannot Find Indicator";

    }

    public StoredData() {

    }

    private void populate() {
        analysisIndicators.put("SP.POP.TOTL", "Total Population");
        analysisIndicators.put("EN.ATM.CO2E.PC", "CO2 emissions (metric tons per capita)");
        analysisIndicators.put("EN.ATM.PM25.MC.M3",
                "PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)");
        analysisIndicators.put("IT.NET.USER.ZS",
                "Internet Use");
        analysisIndicators.put("SE.ADT.LITR.ZS", "Literacy Rate");
        analysisIndicators.put("EG.USE.PCAP.KG.OE", "Energy use (kg of oil equivalent per capita)");
        analysisIndicators.put("AG.LND.FRST.ZS", "Forest area (% of land area)");
        analysisIndicators.put("NY.GDP.PCAP.CD", "GDP per capita (current US$)");
        analysisIndicators.put("SH.MED.BEDS.ZS", "Hospital beds (per 1,000 people)");
        analysisIndicators.put("SE.XPD.TOTL.GD.ZS", "Government expenditure on education, total (% of GDP)");
        analysisIndicators.put("SH.STA.MMRT", "Maternal mortality ratio (modeled estimate, per 100,000 live births)");
        analysisIndicators.put("SH.XPD.CHEX.PC.CD", "Current health expenditure per capita (current US$)");
        analysisIndicators.put("SH.XPD.CHEX.GD.ZS", "Current health expenditure (% of GDP)");
        analysisIndicators.put("SP.DYN.IMRT.IN", "Mortality rate, infant (per 1,000 live births)");
        analysisIndicators.put("SH.ACS.MONY.Q1.ZS", "Problems in accessing health care (% of women): Q1 (lowest)");

    }

    public String toString() {
        String toReturn = "";

        for (int i = 0; i < years.size(); i++) {
            toReturn = toReturn + seriesIndicator + " " + years.get(i) + " " + yearvalues.get(i) + "\n";
        }
        return toReturn;
    }

    public ArrayList<Float> getValues() {
        return this.yearvalues;
    }

    public ArrayList<Integer> getYears() {
        return this.years;
    }

    public int getStartYear() {
        return Integer.valueOf(this.years.get(this.years.size() - 1));
    }

    public int getEndYear() {
        return Integer.valueOf(this.years.get(0));
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public String getInd() {
        return this.seriesIndicator;
    }

}
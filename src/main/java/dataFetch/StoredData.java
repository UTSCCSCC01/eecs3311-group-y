package dataFetch;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * StoredData object which has the data for each called Indicator
 * Has ArrayList of Year and Value which correspond to each other
 * 
 * @author Abdul
 *
 */
public class StoredData {

    String seriesIndicator;
    ArrayList<Float> yearvalues;
    ArrayList<Integer> years;
    String country;
    String seriesName;
    HashMap<String, String> analysisIndicators = new HashMap<String, String>();
    HashMap<String, String> axisLabels = new HashMap<String, String>();

    /**
     * Default constructor
     */
    public StoredData() {

    }

    /**
     * 
     * @param ind        the indicator value for data
     * @param yearvalues ArrayList of the values
     * @param years      ArrayList of the years
     */
    public StoredData(String ind, ArrayList<Float> yearvalues, ArrayList<Integer> years) {
        this.seriesIndicator = ind;
        this.yearvalues = yearvalues;
        this.years = years;
        this.populate();
        if (analysisIndicators.containsKey(ind))
            this.seriesName = analysisIndicators.get(ind);
        else
            this.seriesName = "Null";

    }

    /**
     * 
     * @param ind        the indicator value for data
     * @param seriesName the name of the series based off indicator
     */
    public StoredData(String ind, String seriesName) {
        populate();
        this.years = new ArrayList<Integer>();
        this.yearvalues = new ArrayList<Float>();
        this.seriesName = seriesName;
        this.seriesIndicator = ind;

        if (analysisIndicators.containsKey(ind))
            this.seriesName = analysisIndicators.get(ind);
        else
            this.seriesName = seriesName;

    }

    /**
     * 
     * @param ind the indicator value for data
     */
    public StoredData(String ind) {
        populate();
        this.years = new ArrayList<Integer>();
        this.yearvalues = new ArrayList<Float>();
        this.seriesIndicator = ind;
        System.out.println(ind);
        if (analysisIndicators.containsKey(ind))
            this.seriesName = analysisIndicators.get(ind);
        else
            this.seriesName = "Cannot Findo";

    }

    /**
     * Populates map of analysis indicators for lookup
     */
    private void populate() {
        analysisIndicators.put("SP.POP.TOTL", "Total Population");
        analysisIndicators.put("EN.ATM.CO2E.PC", "CO2 emissions (metric tons per capita)");
        analysisIndicators.put("EN.ATM.PM25.MC.M3",
                "PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)");
        analysisIndicators.put("EG.USE.PCAP.KG.OE", "Energy use (kg of oil equivalent per capita)");
        analysisIndicators.put("AG.LND.FRST.ZS", "Forest area (% of land area)");
        analysisIndicators.put("NY.GDP.PCAP.CD", "GDP per capita (current US$)");
        analysisIndicators.put("IT.NET.USER.ZS", "Individuals using the Internet (% of population");
        analysisIndicators.put("SH.MED.BEDS.ZS", "Hospital beds (per 1,000 people)");
        analysisIndicators.put("SE.XPD.TOTL.GD.ZS", "Government expenditure on education, total (% of GDP)");
        analysisIndicators.put("SH.STA.MMRT", "Maternal mortality ratio (modeled estimate, per 100,000 live births)");
        analysisIndicators.put("SH.XPD.CHEX.PC.CD", "Current health expenditure per capita (current US$)");
        analysisIndicators.put("SH.XPD.CHEX.GD.ZS", "Current health expenditure (% of GDP)");
        analysisIndicators.put("SP.DYN.IMRT.IN", "Mortality rate, infant (per 1,000 live births)");
        analysisIndicators.put("AG.LND.FRST.ZS", "Forested Area");
        analysisIndicators.put("EG.ELC.ACCS.UR.ZS", "Access to electricity");
        analysisIndicators.put("EG.ELC.RNEW.ZS", "Renewable Energy Output");

    }

    /**
     * Converts Stored Data to String
     */
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < years.size(); i++) {
            toReturn = toReturn + seriesIndicator + " " + years.get(i) + " " + yearvalues.get(i) + "\n";
        }
        return toReturn;
    }

    /**
     * 
     * @return yearvalues ArrayList of values
     */
    public ArrayList<Float> getValues() {
        return this.yearvalues;
    }

    /**
     * 
     * @return years ArrayList of years
     */
    public ArrayList<Integer> getYears() {
        return this.years;
    }

    /**
     * 
     * @return Start Year
     */
    public int getStartYear() {
        return Integer.valueOf(this.years.get(this.years.size() - 1));
    }

    /**
     * 
     * @return End Year
     */
    public int getEndYear() {
        return Integer.valueOf(this.years.get(0));
    }

    /**
     * 
     * @return Name of Series
     */
    public String getSeriesName() {
        return this.seriesName;
    }

    /**
     * 
     * @param series to set to
     */
    public void setSeriesName(String name) {
        this.seriesName = name;
    }

    /**
     * 
     * @return series Indicator
     */
    public String getInd() {
        return this.seriesIndicator;
    }

}
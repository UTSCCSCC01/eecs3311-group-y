package countryProcess;

import java.util.ArrayList;

public class Country {
    ArrayList<ArrayList<Float>> value = new ArrayList<>();
    ArrayList<String> indicators = new ArrayList<>();
    int startYear;
    int endYear;
    String countryName;
    String countryCode;

    public Country(String countryName, String countryCode, int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.countryName = countryName;
        this.countryCode = countryCode;

    }

    public String getCountryName() {
        return this.countryName;
    }
    public String getCountryCode(){
        return this.countryCode;
    }
    public int getStartYear(){
        return this.startYear;
    }
    public int getEndYear(){
        return this.endYear;
    }
    public ArrayList<ArrayList<Float>> getValue(){
        return value;
    }
    public ArrayList<String> getIndicators(){
        return indicators;
    }

}

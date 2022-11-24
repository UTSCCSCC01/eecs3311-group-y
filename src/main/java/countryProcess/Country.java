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

}

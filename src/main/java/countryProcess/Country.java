package countryProcess;

import java.util.ArrayList;

/**
 * Country object that stores data for single country
 * 
 * @author Abdul
 *
 */
public class Country {
    private int startYear;
    private int endYear;
    private String countryName;
    private String countryCode;

    /**
     * country object constructor
     * 
     * @param countryName string of country name 
     * @param countryCode string of the country code for api
     * @param startYear int of the valid start year
     * @param endYear int of the valid end year
     */
    public Country(String countryName, String countryCode, int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.countryName = countryName;
        this.countryCode = countryCode;

    }
    /**
     * @return countryName
     */
    public String getCountryName() {
        return this.countryName;
    }
    /**
     * @return countryCode
     */
    public String getCountryCode() {
        return this.countryCode;
    }
    /**
     * @return startYear
     */
    public int getStartYear() {
        return this.startYear;
    }
    /**
     * @return endYear
     */
    public int getEndYear() {
        return this.endYear;
    }

}

package dataFetch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;

/**
 * Facade Design Pattern for data fetching
 * Calls upon other components and is a simple
 * Interface that makes it easy for the user to call
 * 
 * @author Abdul
 *
 */
public class DataAcquisition {
    public ArrayList<Float> dataFromURL = new ArrayList<>();
    public static ArrayList<StoredData> dataStorage = new ArrayList<>();
    public String[] ind;
    public String startingYear;
    public String endingYear;
    public String countrycode;
    private ArrayList<Integer> years;
    private ArrayList<Float> yearvalues;

    /**
     * 
     * @param ind          array of indicators to get data from
     * @param countrycode  code of country to retrieve data from
     * @param startingYear year to start data retrieval
     * @param endingYear   year to end data retrieval
     */
    public DataAcquisition(String[] ind, String countrycode, String startingYear, String endingYear) {

        this.ind = ind;
        this.countrycode = countrycode;
        this.startingYear = startingYear;
        dataStorage = new ArrayList<>();
        this.endingYear = endingYear;
        for (int j = 0; j < ind.length; j++) {
            // array lists to populate
            yearvalues = new ArrayList<>();
            years = new ArrayList<>();

            try {
                // make URL
                String urlToGet = String.format("http://api.worldbank.org/v2/country/" + countrycode + "/indicator/"
                        + ind[j] + "?date=" + startingYear + ":" + endingYear + "&format=json", "can");

                // make HTTP connection
                URL urlConstruct = new URL(urlToGet);
                String parsedText = "";
                HttpURLConnection connection = (HttpURLConnection) urlConstruct.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responsecode = connection.getResponseCode();

                if (responsecode != 200) {
                    System.out.println("Error");
                } else if (responsecode == 200) {
                    // parsed text from Website
                    parsedText = WorldBankAPI.getData(urlToGet);
                    // store parsed text into Json Array
                    JsonArray jsonStore = JsonParse.parseToJson(parsedText);

                    if (jsonStore.isJsonNull()) {
                        System.out.println("Null");
                    } else if (jsonStore.size() > 1) {

                        // Process Json array and convert to values we can use
                        JsonProcess.ProcessJsonArray(jsonStore);
                        yearvalues = JsonProcess.getYearValues();
                        years = JsonProcess.getYears();
                        // create a stored data object which has the data we need
                        StoredData p = new StoredData(ind[j], yearvalues, years);

                        dataStorage.add(p);

                    }

                    else {

                        System.out.println("Country not there");
                    }

                } else {
                    System.out.println("Catch all");
                }

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * Method to check if Valid Year, now obsolete
     *
     * @param ind          array of indicators to get data from
     * @param countrycode  code of country to retrieve data from
     * @param startingYear year to start data retrieval
     * @param endingYear   year to end data retrieval
     * @return whether the year range and country are valid
     */
    public static Boolean checkifValidYear(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        int minIndex = 1000;
        if (DataAcquisition.dataStorage.size() == 0)
            return false;
        for (int i = 0; i < DataAcquisition.dataStorage.size(); i++) {
            if (DataAcquisition.dataStorage.get(i).getValues().size() < minIndex) {
                minIndex = DataAcquisition.dataStorage.get(i).getValues().size();
            }
        }

        String end = temp.years.get(0) + "";
        String start = temp.getYearForTest().get(minIndex - 1) + "";
        if (!(end.equals(endingYear)) || !(start.equals(startingYear))) {
            System.out.println("Not valid");
            return false;
        }

        return true;

    }

    /**
     * Method to check if country and year range have valid data
     *
     * @param ind          array of indicators to get data from
     * @param countrycode  code of country to retrieve data from
     * @param startingYear year to start data retrieval
     * @param endingYear   year to end data retrieval
     * @return whether the year range and country have valid data to work with
     */
    public static Boolean checkIfValidData(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        if (DataAcquisition.dataStorage.size() == 0) {
            return false;
        }
        if (DataAcquisition.dataStorage.get(0).years.size() == 0) {
            return false;
        }
        int minIndex = 1000;
        for (int i = 0; i < DataAcquisition.dataStorage.size(); i++) {
            if (DataAcquisition.dataStorage.get(i).getValues().size() < minIndex) {
                minIndex = DataAcquisition.dataStorage.get(i).getValues().size();
            }
            if (DataAcquisition.dataStorage.get(i).getValues().size() == 0) {
                return false;
            }
        }
        if (minIndex == 0) {
            return false;
        }

        return true;

    }

    /**
     * Method to check if country and year range have valid data for
     * the Annual percentage calculation
     *
     * @param ind          array of indicators to get data from
     * @param countrycode  code of country to retrieve data from
     * @param startingYear year to start data retrieval
     * @param endingYear   year to end data retrieval
     * @return whether the year range and country have valid data to work with for
     *         annual
     */
    public static Boolean ifSelectedIsAnnual(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        int minIndex = 1000;
        if (DataAcquisition.dataStorage.size() == 0) {
            return false;
        }

        for (int i = 0; i < DataAcquisition.dataStorage.size(); i++) {
            if (DataAcquisition.dataStorage.get(i).getValues().size() <= 3) {
                return false;
            }

        }

        return true;

    }

    /**
     * @return Array List of Integers for years for test
     */
    public ArrayList<Integer> getYearForTest() {
        return this.years;
    }

    /**
     * @return Array List of Stored Data from data fetching
     */
    public static ArrayList<StoredData> getDataStorage() {
        return DataAcquisition.dataStorage;
    }

}
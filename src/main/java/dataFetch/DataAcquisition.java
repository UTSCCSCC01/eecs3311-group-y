package dataFetch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;

/**
 * Facade Design Pattern
 * 
 * @author owner
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

    public DataAcquisition(String[] ind, String countrycode, String startingYear, String endingYear) {

        this.ind = ind;
        this.countrycode = countrycode;
        this.startingYear = startingYear;
        dataStorage = new ArrayList<>();
        this.endingYear = endingYear;
        for (int j = 0; j < ind.length; j++) {

            yearvalues = new ArrayList<>();
            years = new ArrayList<>();

            String urlToGet = String.format("http://api.worldbank.org/v2/country/" + countrycode + "/indicator/"
                    + ind[j] + "?date=" + startingYear + ":" + endingYear + "&format=json", "can");

            try {

                URL urlConstruct = new URL(urlToGet);
                String parsedText = "";
                HttpURLConnection connection = (HttpURLConnection) urlConstruct.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responsecode = connection.getResponseCode();

                if (responsecode != 200) {
                    System.out.println("Error");
                } else if (responsecode == 200) {
                    parsedText = WorldBankAPI.getData(urlToGet);
                    JsonArray jsonStore = JsonParse.parseToJson(parsedText);

                    if (jsonStore.isJsonNull()) {
                        System.out.println("Null");
                    } else if (jsonStore.size() > 1) {

                        JsonProcess.ProcessJsonArray(jsonStore);
                        yearvalues = JsonProcess.getYearValues();
                        years = JsonProcess.getYears();
                        System.out.println("Data Parsed");
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

    public static Boolean checkifValidYear(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        int minIndex = 1000;
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

    public static Boolean checkIfValidData(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        if (DataAcquisition.dataStorage.size() == 0) {
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

    public static boolean ifSelectedIsAnnual(String[] ind, String countrycode, String startingYear, String endingYear) {
        DataAcquisition temp = new DataAcquisition(ind, countrycode, startingYear, endingYear);
        int minIndex = 1000;
        if (DataAcquisition.dataStorage.size() == 0) {
            return false;
        }

        for (int i = 0; i < DataAcquisition.dataStorage.size(); i++) {
            if (DataAcquisition.dataStorage.get(i).getValues().size() < 3) {
                return false;
            }

        }

        return true;

    }

    public ArrayList<Integer> getYearForTest() {
        return this.years;
    }

    public static ArrayList<StoredData> getDataStorage() {
        return DataAcquisition.dataStorage;
    }

}
package countryProcess;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * Creates Country Database/Storage list 
 * Stores every country from JSON database of countries
 * 
 * @author Abdul, Zuhair 
 *
 */
public class CountryStorage {

    ArrayList<Country> countryStorageList = new ArrayList<>();
    String[] country = new String[4];
    /**
     * Populates countryStorageList with country object
     * 
     * @param fileName file to get data from
     * @throws Exception if file not found or cannot parse
     */
    public CountryStorage(String fileName) throws Exception {
        String json = readFileAsString("src/assets/Countries.json");

        JsonArray jsonStore = JsonParser.parseString(json).getAsJsonArray();
        for (JsonElement jsonElement : jsonStore) {
            for (Map.Entry<String, JsonElement> entry1 : jsonElement.getAsJsonObject().entrySet()) {
                if (entry1.getKey().equals("Country Name")) {
                    country[0] = entry1.getValue().toString();
                } else if (entry1.getKey().equals("Var")) {
                    country[1] = entry1.getValue().toString();
                } else if (entry1.getKey().equals("Start Valid Year")) {
                    country[2] = entry1.getValue().toString();
                } else if (entry1.getKey().equals("End Valid Year")) {
                    country[3] = entry1.getValue().toString();
                }

            }

            Country temp = new Country(country[0].replace("\"", ""), country[1].replace("\"", ""),
                    Integer.parseInt(country[2]), Integer.parseInt(country[3]));

            countryStorageList.add(temp); // Country database set

        }

    }

    /**
     * static method to read the file as a string 
     * 
     * @param Name of file to read from
     * @return Parsed file as string
     * @throws Exception If file not found
     */
    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/" + file)));
    }
    
    /**
     * 
     * @return ArrayList of Countries 
     */
    public ArrayList<Country> getCountryStorageList() {
        return this.countryStorageList;
    }
    
}
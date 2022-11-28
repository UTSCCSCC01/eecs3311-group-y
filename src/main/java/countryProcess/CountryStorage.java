package countryProcess;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CountryStorage {

    ArrayList<Country> countryStorageList = new ArrayList<>();
    String[] country = new String[4];

    public CountryStorage(String fileName) throws Exception {
        System.out.println(fileName);
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

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public ArrayList<Country> getCountryStorageList() {
        return this.countryStorageList;
    }

    public void setCountryStorageList(ArrayList<Country> countryStorageList) {
        this.countryStorageList = countryStorageList;
    }
}
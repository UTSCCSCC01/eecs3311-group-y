package dataFetch;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Process the JsonArray into ArrayLists of year and value
 * Used to create Data object
 * 
 * @author Abdul
 *
 */
public class JsonProcess {
    static ArrayList<Float> yearvalues;
    static ArrayList<Integer> years;

    /**
     * 
     * @param jsonStore JsonArray of data to process and create data we can work
     *                  with
     */
    public static void ProcessJsonArray(JsonArray jsonStore) {
        yearvalues = new ArrayList<>();
        years = new ArrayList<>();
        int sizeOfResults = jsonStore.get(1).getAsJsonArray().size();
        float valueForYear = 0;
        int year = 0;
        JsonElement temp;
        for (int i = 0; i < sizeOfResults; i++) {
            year = 0;
            temp = jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date");
            year = temp.getAsInt();

            if (!jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
                years.add(year);
                valueForYear = jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsFloat();

                yearvalues.add(valueForYear);
            }

        }
    }

    /**
     * 
     * @return Float ArrayList of the values for each year
     */
    public static ArrayList<Float> getYearValues() {

        return yearvalues;
    }

    /**
     * 
     * @return Integer ArrayList of each year
     */
    public static ArrayList<Integer> getYears() {
        return years;
    }

}
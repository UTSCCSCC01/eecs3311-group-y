package dataFetch;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Parses input text into Json that can be worked with
 * 
 * 
 * @author Abdul
 *
 */
public class JsonParse {

    /**
     * 
     * @param parsedText to parse to Json
     * @return JsonArray of parsed Json
     */
    public static JsonArray parseToJson(String parsedText) {
        JsonArray jsonStore = null;
        try {
            // for GSON 2.8.6
            jsonStore = JsonParser.parseString(parsedText).getAsJsonArray();
            // for GSON Versions below 2.8.6.

        }

        catch (Exception e) {
            System.out.println("Err");
        }

        if (jsonStore.isJsonNull()) {
            jsonStore.add("");
        }
        return jsonStore;
    }

}

package dataFetch;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JsonParse {

	public static JsonArray parseToJson(String parsedText) {
		JsonArray jsonStore = null;
		try {
			// for GSON 2.8.6
			//jsonStore = JsonParser.parseString(parsedText).getAsJsonArray();
			// for GSON Versions below 2.8.6.
			jsonStore = new JsonParser().parse(parsedText).getAsJsonArray();

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

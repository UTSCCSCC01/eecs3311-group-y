package dataFetch;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonProcess {
	static ArrayList<Float> yearvalues;
	static ArrayList<Integer> years;

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

	public static ArrayList<Float> getYearValues() {

		return yearvalues;
	}

	public static ArrayList<Integer> getYears() {
		return years;
	}

}
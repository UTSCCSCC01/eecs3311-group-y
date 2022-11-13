package dataFetch;

import java.io.*;
import java.net.*;
import com.google.gson.*;
import java.util.*;

public class DataFetcher {

	public ArrayList<Float> dataFromURL = new ArrayList<>();
	public ArrayList<StoredData> dataStorage = new ArrayList<>();
	public String ind;
	public String startingYear;
	public String endingYear;
	public String countrycode;

	public DataFetcher(String ind, String countrycode, String startingYear, String endingYear) {

		this.ind = ind;
		this.countrycode = countrycode;
		this.startingYear = startingYear;
		this.endingYear = endingYear;

		ArrayList<Float> yearvalues = new ArrayList<>();
		ArrayList<Integer> years = new ArrayList<>();

		String urlToGet = String.format("http://api.worldbank.org/v2/country/" + countrycode + "/indicator/" + ind
				+ "?date=" + startingYear + ":" + endingYear + "&format=json", "can");

		float valueForYear = 0;

		try {

			if (urlToGet.isEmpty()) {
				System.out.println("Empty URL");
			}

			URL urlConstruct = new URL(urlToGet);

			HttpURLConnection connection = (HttpURLConnection) urlConstruct.openConnection();
			connection.setRequestMethod("GET");

			connection.connect();

			int responsecode = connection.getResponseCode();
			BufferedReader textIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String s = null;
			String parsedText = "";

			while ((s = textIn.readLine()) != null) {

				parsedText = parsedText + s;
			}

			if (responsecode != 200) {
				System.out.println("Error");
			} else if (responsecode == 200) {

				JsonArray jsonStore = new JsonParser().parse(parsedText).getAsJsonArray();

				if (jsonStore.isJsonNull()) {
					System.out.println();
				} else if (jsonStore.size() > 1) {
					int sizeOfResults = jsonStore.get(1).getAsJsonArray().size();

					int year = 0;

					for (int i = 0; i < sizeOfResults; i++) {

						year = jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();

						if (!jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
							years.add(year);
							valueForYear = jsonStore.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
									.getAsFloat();

							yearvalues.add(valueForYear);
						}

					}

				} else {

					System.out.println("Country not there");
				}

				System.out.println("Data Parsed");
				StoredData p = new StoredData(ind, yearvalues, years);

				dataStorage.add(p);
			} else {
				System.out.println("Catch all");
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
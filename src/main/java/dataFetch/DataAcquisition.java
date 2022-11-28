package dataFetch;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;
/**
 * Facade Design Pattern
 * @author owner
 *
 */
public class DataAcquisition {
	
	public ArrayList<Float> dataFromURL = new ArrayList<>();
	public static ArrayList<ArrayList<StoredData>> dataStorage = new ArrayList<>();
	public String[] ind;
	public String startingYear;
	public String endingYear;
	public String countrycode;

	public DataAcquisition(String[] ind, String countrycode, String startingYear, String endingYear) {

		this.ind = ind;
		this.countrycode = countrycode;
		this.startingYear = startingYear;
		this.endingYear = endingYear;

		ArrayList<ArrayList<StoredData>> retrievedData = new ArrayList<>();
		for (int j = 0; j < ind.length; j++) {	// iterates over the each countries data of the code

			ArrayList<Float> yearValues = new ArrayList();
			ArrayList<Integer> years = new ArrayList();
			ArrayList<StoredData> countryTemp = new ArrayList();


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
					System.out.println("jsonStore:" + jsonStore);

					if (jsonStore.isJsonNull()) {
						System.out.println("Null");
					} else if (jsonStore.size() > 1) {

						JsonProcess.ProcessJsonArray(jsonStore);

						/**
						 * we're passing the values of the arraylists tothe stored data object, where
						 */
						yearValues = JsonProcess.getYearValues(); // array list
						years = JsonProcess.getYears(); // arraylist
						String currentIndicator = ind[j];
						System.out.println("Data Parsed");
						// Adds the individual storedData objects into the retrieved Data list

						for (int i = 0; i < yearValues.size(); i++){
							StoredData yearData = new StoredData(currentIndicator, yearValues.get(i), years.get(i));
							countryTemp.add(yearData);
						}
						retrievedData.add(countryTemp);
					} else {

						System.out.println("Country not there");
					}

				} else {
					System.out.println("Catch all");
				}

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		setDataStorage(retrievedData);
	}

	// Stores the data retrieved from the API call
	public void setDataStorage(ArrayList<ArrayList<StoredData>> fullData){
		dataStorage = fullData;
	}

	public static ArrayList<ArrayList<StoredData>> getDataStorage(){
		if (dataStorage.isEmpty()){
			throw new IllegalArgumentException("Data Storage is empty.");
		}else{
			return dataStorage;
		}
	}

}
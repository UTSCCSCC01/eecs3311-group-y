package dataFetch;

import java.util.ArrayList;

public class StoredData {

	String seriesIndicator;
	ArrayList<Float> yearvalues;
	ArrayList<Integer> years;
	String country;

	public StoredData(String ind, ArrayList<Float> yearvalues, ArrayList<Integer> years) {
		this.seriesIndicator = ind;
		this.yearvalues = yearvalues;
		this.years = years;
	}


	public String toString() {
		String toReturn = "";
		for (int i = 0; i < years.size(); i++) {
			toReturn = toReturn + seriesIndicator + " " + years.get(i) + " " + yearvalues.get(i) + "\n";
		}
		return toReturn;
	}
}
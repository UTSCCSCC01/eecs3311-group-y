package dataFetch;

import java.util.ArrayList;

public class StoredData {

	String seriesIndicator;
	float yearValues;
	int years;
	String country;

	public StoredData(String ind, float yearValues, int years) {
		this.seriesIndicator = ind;
		this.yearValues = yearValues;
		this.years = years;
	}

	public String getSeriesIndicator(){
		return this.seriesIndicator;
	}

	public float getYearValues(){
		return this.yearValues;
	}

	public int getYears(){
		return this.years;
	}


//	public String toString() {
//		String toReturn = "";
//		for (int i = 0; i < years.size(); i++) {
//			toReturn = toReturn + seriesIndicator + " " + years.get(i) + " " + yearvalues.get(i) + "\n";
//		}
//		return toReturn;
//	}
}
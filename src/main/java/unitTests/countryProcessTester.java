package unitTests;

import dataFetch.DataAcquisition;
import dataFetch.StoredData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import countryProcess.CountryStorage;
import strategyAnalysis.AnalysisAnnual;
import strategyAnalysis.AnalysisAverage;
import strategyAnalysis.AnalysisContext;
import strategyAnalysis.AnalysisRatio;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class countryProcessTester {

    @Test
    void testCountryDatabaseSize() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().size(), 258);

    }

    @Test
    void testCountryDatabaseReadFile() throws Exception {
        String temp = CountryStorage.readFileAsString("src/assets/Countries.json");
        assert (temp.length() > 0);

    }

    @Test
    void testCountryStorageException() throws Exception {
        CountryStorage db = new CountryStorage("Countries");

        Exception thrown = assertThrows("Expected readFile to throw, but it didn't",
                Exception.class,
                () -> CountryStorage.readFileAsString("Countries.json")

        );

    }

    @Test
    void testCountryTestCountryExist() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().get(0).getCountryName(), "Afghanistan");
        assertEquals(db.getCountryStorageList().get(0).getCountryCode(), "AFG");
        assertEquals(db.getCountryStorageList().get(0).getStartYear(), 1962);
        assertEquals(db.getCountryStorageList().get(0).getEndYear(), 2021);
        
    }

    @Test
    void testCountryStorageNotExist() throws Exception {
        CountryStorage db = new CountryStorage("Countries");
        assertEquals(db.getCountryStorageList().get(4).getCountryName(), "American Samoa");
        assertEquals(db.getCountryStorageList().get(4).getCountryCode(), "NA");

    }
    
    

}

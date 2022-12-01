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

public class dataFetchTester {

    @Test
    void testDataFetcherValidYear() {
        DataAcquisition newData = new DataAcquisition(new String[] { "AG.LND.FRST.ZS" }, "CA", "2000", "2005");
        int expectedSize = 6;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        double endingValue = 38.766228;
        double startingValue = 38.79298;
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;
        double actualStart = DataAcquisition.dataStorage.get(0).getValues().get(0);
        assertEquals(startingValue, actualStart, 0.03);
        double actualEnd = DataAcquisition.dataStorage.get(0).getValues().get(actualSize - 1);
        assertEquals(endingValue, actualEnd, 0.03);

    }

    @Test
    void testDataFetcherValidYear2() {
        DataAcquisition newData = new DataAcquisition(new String[] { "SP.POP.TOTL" }, "BA", "2000", "2006");
        int expectedSize = 7;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        double startingValue = 3765422.0;
        double endingValue = 3751176.0;
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;
        double actualStart = DataAcquisition.dataStorage.get(0).getValues().get(0);
        assertEquals(startingValue, actualStart, 0.03);
        double actualEnd = DataAcquisition.dataStorage.get(0).getValues().get(actualSize - 1);
        assertEquals(endingValue, actualEnd, 0.03);

    }

    @Test
    void testDataFetcherNoIndicatorData() {
        DataAcquisition newData = new DataAcquisition(new String[] { "SH.ACS.MONY.Q1.ZS" }, "CA", "1999", "2005");
        int expectedSize = 0;
        int actualSize = DataAcquisition.dataStorage.get(0).getValues().size();
        assert (actualSize == expectedSize) : "Error with Size expected size is 6 actual is " + actualSize;

    }

    @Test
    void testDataFetcherDataExistsForYear() {
        boolean actualResultFalse = DataAcquisition
                .checkifValidYear(new String[] { "SP.POP.TOTL", "EG.USE.PCAP.KG.OE" }, "USA", "2014", "2019");
        boolean actualResultTrue = DataAcquisition.checkifValidYear(new String[] { "SP.POP.TOTL" }, "USA", "2014",
                "2019");
        assert (actualResultFalse == false && actualResultTrue == true);
    }

}

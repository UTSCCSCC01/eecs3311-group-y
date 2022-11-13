package dataFetch;

public class testFetcher {
    public static void main(String[] args) {
		String indicator = "AG.LND.IRIG.AG.ZS";
        System.out.println("hi");
		String country_code = "CA";
		DataFetcher dp = new DataFetcher(indicator, country_code, "2020", "2020");
		System.out.println(dp.dataStorage.toString());

	}
}

package dataFetch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WorldBankAPI {

	public static String getData(String urlToGet) {
		String parsedText = "";
		try {
			if (urlToGet.isEmpty()) {
				System.out.println("Empty URL");
			}

			URL urlConstruct = new URL(urlToGet);
			String s = null;

			HttpURLConnection connection = (HttpURLConnection) urlConstruct.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader textIn;
			connection.connect();

			textIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((s = textIn.readLine()) != null) {

				parsedText = parsedText + s;
			}

		}

		catch (Exception e) {
			System.out.println("Err");
		}
		return parsedText;
	}
}

package bytesource.placesearch.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author aleksandar
 * class for sending decoding api requests
 */
public class GeoCodingAPI {

	private static final String GEOCODING_API_BASE = "http://maps.googleapis.com/maps/api/geocode/json";

	HttpURLConnection conn = null;
	StringBuilder jsonResults = new StringBuilder();

	/**
	 * method for sending geocoding api request
	 * @param address
	 * @return json object which contains latitude and longitude
	 */
	public JSONObject findLatLng(String address) {
		String newAddress = address.replace(' ', '+');
		StringBuilder request = new StringBuilder(GEOCODING_API_BASE);
		request.append("?address=" + newAddress);
		request.append("+Wien+AT");
		request.append("&sensor=false");
		System.out.println("Sending geocoding request to" + request.toString());

		StringBuilder result = new StringBuilder();

		try {
			URL url = new URL(request.toString());
			conn = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				result.append(inputLine);
			in.close();
			System.out.println("Returned json object:");
			System.out.println(result.toString());
			return new JSONObject(result.toString());
		} catch (MalformedURLException e) {
			System.err.println("URL file is not valid");
			;
		} catch (IOException e) {
			System.err.println("Can not read from the file");
		} catch (JSONException e) {
			System.err.println("Not valid JSON format");
		}

		return null;
	}

}

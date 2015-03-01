package bytesource.placesearch.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import bytesource.placesearch.Place;

/**
 * @author aleksandar
 * class for sending google places api requests
 */
public class PlacesAPI {

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place/";
	private static final String PLACES_API_KEY = "AIzaSyAzuPrHhwpXhJu7jjH485YD2Vl8BZSrBlY";

	HttpURLConnection conn = null;
	StringBuilder jsonResults = new StringBuilder();

	/**
	 * method for sending Google Places search request
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return json object which contains all places nearby
	 */
	public JSONObject search(double lat, double lng, int radius) {
		StringBuilder request = new StringBuilder(PLACES_API_BASE);
		request.append("nearbysearch/json");
		request.append("?location=" + String.valueOf(lat) + ","
				+ String.valueOf(lng));
		request.append("&radius=" + String.valueOf(radius));
		request.append("&types=bar|cafe");
		request.append("&sensor=false");
		request.append("&rankby");
		request.append("&key=" + PLACES_API_KEY);
		
		System.out.println("Sending Google Places API search request");
		System.out.println(request.toString());

		try {
			URL url = new URL(request.toString());
			conn = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder result = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				result.append(inputLine);
			}
			in.close();
			System.out.println("JSON Object retrieved:");
			System.out.println(result.toString());
			return new JSONObject(result.toString());
		} catch (MalformedURLException e) {
			System.out.println("Error processing Place URL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error connectiong to Place URL");
		} catch (JSONException e) {
			System.err.println("Not a valid JSON format");
		}
		return null;
	}
	
	/**
	 * method for sending Google Places Details request
	 * @param place
	 * @return json object that contains info about the place
	 */
	public JSONObject placeDetails(Place place){
		StringBuilder request = new StringBuilder(PLACES_API_BASE);
		request.append("details/json");
		request.append("?reference="+place.getGoogle_id());
		request.append("&key=" + PLACES_API_KEY);
		System.out.println("Sent Google Places Details request to: "+request.toString());
		try {
			URL url = new URL(request.toString());
			conn = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder result = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				result.append(inputLine);
			}
			in.close();
			System.out.println("JSON object retrieved: ");
			System.out.println(result.toString());
			return new JSONObject(result.toString());
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL for Google Places Details");
		} catch (IOException e) {
			System.err.println("Coould not read from the URL");
		} catch (JSONException e) {
			System.err.println("Not a valid JSON format");
		}
		
		return null;
	}

}

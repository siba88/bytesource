package bytesource.placesearch;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author aleksandar
 * class that extracts infos from json objects and saves them
 */

public class JsonHandler {

	/**
	 * method for processing google places search json
	 * @param json objects
	 * @return list of places
	 */
	public ArrayList<Place> processGoogleSearchJson(JSONObject json) {
		ArrayList<Place> places = new ArrayList<Place>();
		try {
			System.out.println("Processing Google Places Search Json");
			if (json.getString("status").equals("OK")) {
				JSONArray results = json.getJSONArray("results");
				for (int i = 0; i < results.length(); i++) {
					Place place = new Place();
					JSONObject result = results.getJSONObject(i);
					JSONObject location = result.getJSONObject("geometry")
							.getJSONObject("location");
					place.setLatitude(location.getDouble("lat"));
					place.setLongitude(location.getDouble("lng"));
					place.setName(result.getString("name"));
					place.setGoogle_id(result.getString("reference"));
					place.setIconLink(result.getString("icon"));
					place.setAddress(result.getString("vicinity"));
					if (result.has("rating")) {
						place.setGoogleRating(String.valueOf(result
								.getDouble("rating")));
					}
					System.out.println("Found place: "+place.getName());
					places.add(place);
				}

			}
			else{
				System.out.println("Status message of the JSON file is not OK");
			}
		} catch (JSONException e) {
			System.err.println("Object is not valid JSON format");
		}

		return places;
	}

	/**
	 * method for processing google place details json
	 * @param json object
	 * @param place where additional infos should be added
	 * @return place with additional infos
	 */
	public Place processGoogleDetailsJson(JSONObject json, Place place) {
		ArrayList<GoogleReview> reviews = new ArrayList<GoogleReview>();
		try {
			System.out.println("Processing Google Places Details Json");
			if (json.getString("status").equals("OK")) {
				JSONObject resultsArray = json.getJSONObject("result");
				if (resultsArray.has("reviews")) {
					JSONArray results = resultsArray.getJSONArray("reviews");
					for (int i = 0; i < results.length(); i++) {
						GoogleReview review = new GoogleReview();
						JSONObject result = results.getJSONObject(i);
						review.setName(result.getString("author_name"));
						review.setRating(String.valueOf(result
								.getDouble("rating")));
						review.setText(result.getString("text"));
						review.setDate(new Date(result.getLong("time")));
						reviews.add(review);
					}
				}
				
				place.setReviews(reviews);
				if (json.has("url")) {
					place.setGoogleMapLink(json.getString("url"));
				}
			}
			else{
				System.out.println("Status message of the JSON file is invalid");
			}
		} catch (JSONException e) {
			System.err.println("Object is not valid JSON format");
		}

		return place;
	}

	/**
	 * method for processing yelp json
	 * @param json
	 * @param place
	 * @return place with additional yelp infos
	 */
	public Place processYelpJSON(JSONObject json, Place place) {

		System.out.println("Processing Yelp Json");
		try {
			JSONArray results = json.getJSONArray("businesses");
			System.out.println(results.toString());
			if (!results.isNull(0)) {
				JSONObject placeJson = results.getJSONObject(0);
				if (placeJson.has("snippet_text")) {
					place.setSnippetText(placeJson.getString("snippet_text"));
				}
				place.setYelpRatingImg(placeJson
						.getString("rating_img_url_small"));
				if (placeJson.has("image_url"))
					place.setYelpLink(placeJson.getString("image_url"));
				place.setYelpRating(String.valueOf(placeJson
						.getDouble("rating")));
				place.setOpened(!placeJson.getBoolean("is_closed"));
				place.setYelpReviewsCount(placeJson.getInt("review_count"));
			}
		} catch (JSONException e) {
			System.err.println("Object is not valid JSON format");
		}

		return place;
	}

	/**
	 * method for processing geocoding json
	 * @param json
	 * @return latitude and longitude in double array
	 */
	public double[] processGeocodingJson(JSONObject json) {
		System.out.println("Processing Geocoding Json");
		double[] latLng = new double[2];
		try {
			if (json != null) {
				if (json.getString("status").equals("OK")) {
					JSONArray results = json.getJSONArray("results");
					JSONObject result = results.getJSONObject(0);
					System.out.println(result.toString());
					JSONObject location = result.getJSONObject("geometry")
							.getJSONObject("location");
					latLng[0] = location.getDouble("lat");
					latLng[1] = location.getDouble("lng");

				}
			}
		} catch (JSONException e) {
			System.err.println("Object is not valid JSON format");
		}
		return latLng;
	}

}

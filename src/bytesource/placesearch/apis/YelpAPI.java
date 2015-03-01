package bytesource.placesearch.apis;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import bytesource.placesearch.Place;

/**
 * @author aleksandar
 * class for sending yelp api request
 */
public class YelpAPI {

	private static final String YELP_API_BASE = "http://api.yelp.com/v2/search";

	OAuthService service = new ServiceBuilder().provider(YelpAPI2.class)
			.apiKey("jbwlVMINPukTzghUWGZjEg")
			.apiSecret("QCEZV8czFuxnoOXgbSkoIh4r2Dw").build();
	Token accessToken = new Token("lsAGesJNNxA6DY_TMsXRnmuBY1C54J6B",
			"pLVvvTxlGcfh1TEN7TLC_ag_XVI");

	HttpURLConnection conn = null;
	StringBuilder jsonResults = new StringBuilder();

	/**
	 * @param place
	 * @return
	 */
	public JSONObject searchYelp(Place place) {
		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, YELP_API_BASE);
			request.addQuerystringParameter("term", place.getName());
			request.addQuerystringParameter("ll", place.getLatitude() + "," + place.getLongitude());
			request.addQuerystringParameter("limit", "1");
			this.service.signRequest(this.accessToken, request);
			System.out.println("Sending yelp request to: "+request.toString());
			Response response = request.send();
			System.out.println("Yelp response: "+response.getBody());
			return new JSONObject(response.getBody().toString().getBytes("UTF-8"));
		} catch (JSONException e) {
			System.err.println("Not a valid JSON format");
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported encoding");
		}
		return null;
	}

}

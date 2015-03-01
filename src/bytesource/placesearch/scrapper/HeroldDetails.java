package bytesource.placesearch.scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bytesource.placesearch.Place;

public class HeroldDetails {

	public HeroldDetails() {

	}

	public Place getDetails(String link, Place place) {
		try {
			Document doc = Jsoup.connect(link).get();
			Element info = doc.select("div#dInfo").first();
			Element hours = doc.select("div#dataHoursInfo").first();
			Element extra = doc.select("div#dXtra").first();
			Element ratingBox = doc.select("div#ratingBox").first();
			Element ratingI = ratingBox.select("i").first();
			String rating = ratingI.attr("content");
			System.out.println(rating);
			if (info != null) {
				place.setHeroldInfo(info.toString());
			}
			if (hours != null) {
				place.setHeroldHours(hours.toString());
			}
			if (extra != null) {
				place.setHeroldExtra(extra.toString());
			}
			if (rating != null) {
				place.setHeroldRating(rating);
			}

		} catch (IOException e) {
			System.err.println("Error reading from herold.at");
		}
		return place;
	}

}

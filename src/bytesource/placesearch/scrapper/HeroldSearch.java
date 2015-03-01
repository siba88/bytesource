package bytesource.placesearch.scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bytesource.placesearch.Place;

public class HeroldSearch {
	
	private HeroldDetails heroldDetails = new HeroldDetails();
	
	public HeroldSearch(){
		
	}
	
	public Place search(Place place){
		String url = "http://www.herold.at/gelbe-seiten/wien/was_"+place.getName().replace(' ', '-').toLowerCase()+"/";
		try {
			Document doc = Jsoup.connect(url).get();
			Element resultDiv = doc.select("div.results").first();
			Element h2 = resultDiv.select("h2").first();
			Element linkAtt = h2.select("a[href]").first();
			String link = linkAtt.attr("href");
			System.out.println(linkAtt.attr("href"));
			if(link != null){
				heroldDetails.getDetails(link, place);
			}
		} catch (IOException e) {
			System.err.println("Error reading from Herold.at");
		}
		
		return place;


	}

}

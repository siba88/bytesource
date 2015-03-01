package bytesource.placesearch;

import java.util.Date;

/**
 * @author aleksandar
 * Google review model
 */
public class GoogleReview {
	
	private String name;
	private String link;
	private String rating;
	private String text;
	private Date date;
	
	//getters and setters for google review
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}

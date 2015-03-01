package bytesource.placesearch;

import java.util.ArrayList;

/**
 * @author aleksandar
 * objects that contains all informations about place
 */
public class Place {
	
	private String name;
	private double latitude;
	private double longitude;
	private String googleRating;
	private String address;
	private boolean opened;
	private String iconLink;
	private String google_id;
	private String googleImg;
	private String phone;
	private String snippetText;
	private String yelpRating;
	private String yelpLink;
	private String yelpRatingImg;
	private int yelpReviewsCount;
	private ArrayList<GoogleReview> reviews;
	private String googleMapLink;
	private String heroldInfo;
	private String heroldHours;
	private String heroldExtra;
	private String heroldRating;
	private int distance;

	public Place() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getGoogleRating() {
		return googleRating;
	}
	public void setGoogleRating(String googleRating) {
		this.googleRating = googleRating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public String getIconLink() {
		return iconLink;
	}
	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}
	public String getGoogle_id() {
		return google_id;
	}
	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSnippetText() {
		return snippetText;
	}
	public void setSnippetText(String snippetText) {
		this.snippetText = snippetText;
	}
	public String getYelpRating() {
		return yelpRating;
	}
	public void setYelpRating(String yelpRating) {
		this.yelpRating = yelpRating;
	}
	public String getYelpLink() {
		return yelpLink;
	}
	public void setYelpLink(String yelpLink) {
		this.yelpLink = yelpLink;
	}
	
	public String getYelpRatingImg() {
		return yelpRatingImg;
	}

	public void setYelpRatingImg(String yelpRatingImg) {
		this.yelpRatingImg = yelpRatingImg;
	}

	public int getYelpReviewsCount() {
		return yelpReviewsCount;
	}

	public void setYelpReviewsCount(int yelpReviewsCount) {
		this.yelpReviewsCount = yelpReviewsCount;
	}
	
	public String getGoogleImg() {
		return googleImg;
	}

	public void setGoogleImg(String googleImg) {
		this.googleImg = googleImg;
	}

	public ArrayList<GoogleReview> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<GoogleReview> reviews) {
		this.reviews = reviews;
	}

	public String getGoogleMapLink() {
		return googleMapLink;
	}

	public void setGoogleMapLink(String googleMapLink) {
		this.googleMapLink = googleMapLink;
	}

	public String getHeroldInfo() {
		return heroldInfo;
	}

	public void setHeroldInfo(String heroldInfo) {
		this.heroldInfo = heroldInfo;
	}

	public String getHeroldHours() {
		return heroldHours;
	}

	public void setHeroldHours(String heroldHours) {
		this.heroldHours = heroldHours;
	}

	public String getHeroldExtra() {
		return heroldExtra;
	}

	public void setHeroldExtra(String heroldExtra) {
		this.heroldExtra = heroldExtra;
	}

	public String getHeroldRating() {
		return heroldRating;
	}

	public void setHeroldRating(String heroldRating) {
		this.heroldRating = heroldRating;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	

	@Override
	public String toString() {
		return "Place [name=" + name + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", googleRating=" + googleRating
				+ ", address=" + address + ", opened=" + opened + ", iconLink="
				+ iconLink + ", google_id=" + google_id + ", googleImg="
				+ googleImg + ", phone=" + phone + ", snippetText="
				+ snippetText + ", yelpRating=" + yelpRating + ", yelpLink="
				+ yelpLink + ", yelpRatingImg=" + yelpRatingImg
				+ ", yelpReviewsCount=" + yelpReviewsCount + ", reviews="
				+ reviews + ", googleMapLink=" + googleMapLink
				+ ", heroldInfo=" + heroldInfo + ", heroldHours=" + heroldHours
				+ ", heroldExtra=" + heroldExtra + ", heroldRating="
				+ heroldRating + "]";
	}
	

}

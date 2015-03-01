package bytesource.placesearch.gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import bytesource.placesearch.GoogleReview;
import bytesource.placesearch.JsonHandler;
import bytesource.placesearch.Place;
import bytesource.placesearch.apis.PlacesAPI;
import bytesource.placesearch.apis.YelpAPI;
import bytesource.placesearch.scrapper.HeroldSearch;

public class PlaceDetailsGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Place place;
	JLabel icon;
	JLabel rating;
	JLabel heroldRating;
	JLabel name;
	JLabel address;
	JLabel phone;
	JLabel yelpIcon;
	JLabel yelpRating;
	JLabel yelpRatingIcon;
	JLabel yelpReviewsCount;
	JLabel img;
	JLabel distance;
	JTextPane yelpSnippet;
	JTextPane reviews;
	JScrollPane scrollPane;
	JTextPane heroldReviews;
	JScrollPane heroldScrollPane;
	
	PlacesAPI placesAPI = new PlacesAPI();
	YelpAPI yelpAPI = new YelpAPI();
	HeroldSearch heroldSearch = new HeroldSearch();
	JsonHandler jsonHandler = new JsonHandler();
	
	public PlaceDetailsGUI(Place place){
		this.place = place;
		initUI();
	}

	private void initUI() {
		setVisible(true);
		setTitle("Place Search");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		FlowLayout layout = new FlowLayout();
		layout.setVgap(10);
		panel.setLayout(layout);
		
		JPanel infoPanel = new JPanel();
		JPanel googlePanel = new JPanel();
		JPanel heroldPanel = new JPanel();
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		JPanel dataPanel = new JPanel();
		FlowLayout infoLayout = new FlowLayout();
		dataPanel.setLayout(infoLayout);
		icon = new JLabel();
		dataPanel.add(icon);
		JPanel dataInfoPanel = new JPanel();
		dataInfoPanel.setLayout(new BoxLayout(dataInfoPanel, BoxLayout.Y_AXIS));
		name = new JLabel();
		address = new JLabel();
		phone = new JLabel();
		distance = new JLabel();
		dataInfoPanel.add(name);
		dataInfoPanel.add(address);
		dataInfoPanel.add(phone);
		dataInfoPanel.add(distance);
		dataPanel.add(dataInfoPanel);
		JPanel yelpPanel = new JPanel();
		yelpPanel.setLayout(new BoxLayout(yelpPanel, BoxLayout.Y_AXIS));
		yelpIcon = new JLabel();
		yelpRating = new JLabel();
		yelpRatingIcon = new JLabel();
		yelpReviewsCount = new JLabel();
		yelpSnippet = new JTextPane();
		yelpSnippet.setPreferredSize(new Dimension(150, 50));
		yelpPanel.add(yelpIcon);
		yelpPanel.add(yelpRating);
		yelpPanel.add(yelpRatingIcon);
		yelpPanel.add(yelpReviewsCount);
		yelpPanel.add(yelpSnippet);
		img=new JLabel();
		infoPanel.add(dataPanel);
		infoPanel.add(yelpPanel);
		infoPanel.add(img);
		
		heroldPanel.setLayout(new BoxLayout(heroldPanel, BoxLayout.Y_AXIS));
		heroldRating = new JLabel();
		heroldReviews = new JTextPane();
		heroldReviews.setEditable(false);
		heroldReviews.setContentType("text/html");
		heroldScrollPane = new JScrollPane(heroldReviews);
		heroldScrollPane.setPreferredSize(new Dimension(300, 600));
		heroldPanel.add(heroldRating);
		heroldPanel.add(heroldScrollPane);
		
		googlePanel.setLayout(new BoxLayout(googlePanel, BoxLayout.Y_AXIS));
		rating = new JLabel();
		reviews = new JTextPane();
		reviews.setEditable(false);
		reviews.setContentType("text/html");
		scrollPane = new JScrollPane(reviews);
		scrollPane.setPreferredSize(new Dimension(300, 600));
		googlePanel.add(rating);
		googlePanel.add(scrollPane);
		
		addHeroldInfo();
		addGoogleInfo();
		addYelpInfo();
		
		panel.setVisible(true);
		panel.add(infoPanel);
		panel.add(heroldPanel);
		panel.add(googlePanel);
		
		add(panel);
		pack();
		
	}

	private void addHeroldInfo() {
		this.place = heroldSearch.search(place);
		
		heroldRating.setText("Rating: "+place.getHeroldRating()+"/5");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(place.getHeroldInfo());
		sb.append("<hr>");
		sb.append(place.getHeroldHours());
		sb.append("<hr>");
		sb.append(place.getHeroldExtra());
		sb.append("</html>");
		heroldReviews.setText(sb.toString());
		
	}

	private void addGoogleInfo() {
		
		this.place = jsonHandler.processGoogleDetailsJson(placesAPI.placeDetails(place), place);
		if(place.getGoogleRating() != null){
			rating.setText("Rating: "+place.getGoogleRating()+"/5");
		}
		else {
			rating.setText("Rating: N/A");
		}
		
		StringBuilder reviewsString = new StringBuilder();
		reviewsString.append("<html>");
		for(int i=0; i<place.getReviews().size(); i++){
			GoogleReview review = place.getReviews().get(i);
			reviewsString.append("<br>From: "+review.getName()+"</br>");
			reviewsString.append("<br>Rating: "+review.getRating()+"</br>");
			reviewsString.append("<br>"+review.getText()+"</br>");
			reviewsString.append("<hr>");
			
		}
		reviewsString.append("</html>");
		
		reviews.setText(reviewsString.toString());
		
		if(place.getIconLink() != null){
			try {
				System.out.println(place.getIconLink());
				URL url = new URL(place.getIconLink());
				ImageIcon img = new ImageIcon(ImageIO.read(url));
				icon.setIcon(img);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(place.getGoogleMapLink() != null){
			try {
				URL url = new URL(place.getGoogleMapLink());
				ImageIcon img = new ImageIcon(ImageIO.read(url));
				this.img.setIcon(img);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		name.setText(place.getName());
		if(place.getAddress() != null){
			address.setText(place.getAddress());
		}
		if(place.getPhone() != null){
			phone.setText(place.getPhone());
		}
		if(place.getDistance() != 0){
			distance.setText("Distance: "+place.getDistance()+" meters");
		}
		
	}
	
	private void addYelpInfo() {
		this.place= jsonHandler.processYelpJSON(yelpAPI.searchYelp(place), place);
		
		try {
			URL url = new URL("https://loebig.files.wordpress.com/2013/10/yelp2.png");
			ImageIcon img = new ImageIcon(ImageIO.read(url));
			yelpIcon.setIcon(img);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(place.getYelpRatingImg() != null){
			try {
				URL url = new URL(place.getYelpRatingImg());
				ImageIcon img = new ImageIcon(ImageIO.read(url));
				yelpRatingIcon.setIcon(img);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		yelpRating.setText("Rating: "+place.getYelpRating());
		yelpReviewsCount.setText("Based on "+place.getYelpReviewsCount()+" reviews");
		yelpSnippet.setText(place.getSnippetText());
	}

}

package bytesource.placesearch.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bytesource.placesearch.DistanceCalculator;
import bytesource.placesearch.JsonHandler;
import bytesource.placesearch.Place;
import bytesource.placesearch.apis.GeoCodingAPI;
import bytesource.placesearch.apis.PlacesAPI;

public class PlaceSearchGUI extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	private JTextField addressField;
	private JButton searchButton;
	private JTextField radiusField;
	private JList placesList;
	private DefaultListModel model;
	ArrayList<Place> places = new ArrayList<Place>();
	private double[] latLng = new double[2];

	public PlaceSearchGUI() {
	}

	public void initUI() {

		// setResizable(false);
		setVisible(true);
		setTitle("Place Search");
		setSize(300, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		BorderLayout layout = new BorderLayout();
		BoxLayout layout2 = new BoxLayout(panel, BoxLayout.Y_AXIS);
		layout.setVgap(10);

		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		addressField = new JTextField("Address");
		addressField.addMouseListener(this);
		radiusField = new JTextField("Radius (meters)", 4);
		radiusField.addMouseListener(this);
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		model = new DefaultListModel();
		placesList = new JList(model);
		placesList.addMouseListener(this);
		JScrollPane listScroller = new JScrollPane(placesList);
		listScroller.setPreferredSize(new Dimension(300, 450));

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

		fieldPanel.add(addressField);
		fieldPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		fieldPanel.add(radiusField);
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(searchButton, BorderLayout.LINE_END);
		panel.add(listScroller, BorderLayout.SOUTH);

		// setLayout(layout);
		panel.setVisible(true);
		add(panel);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == this.searchButton) {
			
			GeoCodingAPI geoCoding = new GeoCodingAPI();
			PlacesAPI placeAPI = new PlacesAPI();
			JsonHandler jsonHandler = new JsonHandler();

			Integer radius = Integer.valueOf(radiusField.getText());

			if (!radius.equals(null) && radius >= 0 && radius <= 5000) {

				latLng = jsonHandler.processGeocodingJson(geoCoding
						.findLatLng(addressField.getText()));

				if (latLng[0] != 0.0 && latLng[1] != 0.0) {

					places = jsonHandler.processGoogleSearchJson(placeAPI
							.search(latLng[0], latLng[1], radius));
					model.clear();
					for (int i = 0; i < places.size(); i++) {
						DistanceCalculator dc = new DistanceCalculator(latLng, places.get(i));
						places.get(i).setDistance(dc.calculateDistance());
					}
					
					Collections.sort(places, new Comparator<Place>(){
						@Override
						public int compare(Place place1, Place place2) {
							return Double.compare(place1.getDistance(), place2.getDistance());
						}
						
					});
					
					for (int i = 0; i < places.size(); i++) {
						model.addElement(places.get(i).getName());
					}
				} else {
					System.out.println("Cannot find given address");
				}
			} else {
				System.out.println("Radius not a valid value");
			}

		}
		
		

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == placesList){
		int index = placesList.getSelectedIndex();
		//Place place = places.get(index);
		PlaceDetailsGUI detailsGUI = new PlaceDetailsGUI(places.get(index));
		}
		else if(arg0.getSource() == addressField){
			addressField.setText("");
		}
		else if(arg0.getSource() == radiusField){
			radiusField.setText("");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}

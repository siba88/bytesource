package bytesource.placesearch;

import bytesource.placesearch.gui.PlaceSearchGUI;

/**
 * @author aleksandar
 * main class which runs GUI
 */
public class PlaceSearch {

	public static void main(String[] args) {
		
		System.out.println("Running GUI...");
		PlaceSearchGUI placeGUI = new PlaceSearchGUI();
		placeGUI.initUI();

	}

}

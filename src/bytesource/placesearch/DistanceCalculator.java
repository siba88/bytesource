package bytesource.placesearch;

/**
 * @author aleksandar
 * class for calculating distance between two coordinates
 */
public class DistanceCalculator {
	
	private double[] latLng;
	private Place place;
	
	public DistanceCalculator(double[] latLng, Place place){
		this.latLng = latLng;
		this.place = place;
	}

	/**
	 * This method returns distance in meters between two coordinates
	 * @return int
	 */
	public int calculateDistance() {
		double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(latLng[0]-place.getLatitude());
	    double dLng = Math.toRadians(latLng[1]-place.getLongitude());
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(latLng[0])) * Math.cos(Math.toRadians(place.getLatitude())) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    int dist = (int) (earthRadius * c);

	    return dist;
	}

}

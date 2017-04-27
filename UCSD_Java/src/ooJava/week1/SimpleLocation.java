package ooJava.week1;

public class SimpleLocation {

	private double latitude;
	private double longitude;
	
	
	
	public SimpleLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public SimpleLocation() {
		this.latitude = 32.9;
		this.longitude = -117.2;
	}
	
	public double distance(SimpleLocation other){
		
//		return getDist(this.latitude,this.longitude,other.getLatitude,other.getLongitude);
		return 0.0;
	}

	public double distance(double otherLat,double otherLong){
		
//		return getDist(this.latitude,this.longitude,other.latitude,other.longitude);
		return 0.0;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
//	private double getDist(lat1,long1,lat2,long2){
//		
//	}
	
	
}

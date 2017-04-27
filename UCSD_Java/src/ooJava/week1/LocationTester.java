package ooJava.week1;

public class LocationTester {

	public static void main(String[] args){
		LocationTester L = new LocationTester();
		
	}
	
	public void testDistance(){
		SimpleLocation ucsd = new SimpleLocation(32.9,-117.2);
		SimpleLocation lima = new SimpleLocation(-12.0,-77.0);
		System.out.println(ucsd.distance(lima));
	}
	
}

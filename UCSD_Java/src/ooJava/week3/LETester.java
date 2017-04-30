package ooJava.week3;

import java.io.IOException;

public class LETester {

	public static void main(String[] args) throws IOException {
		LETester L = new LETester();
//		L.testMap();
		L.testGetMarkers();
	}
	
	public void testMap() throws IOException{
		LifeExpectancy LE = new LifeExpectancy();
//		String fName="C:/Users/cLennon/Documents/GitHub/Coursera/UCSD_Java/data/LifeExpectancyWorldBankModule3.csv";
//		Map<String, Float> le =LE.loadLifeExp(fName);
//		for(String K : le.keySet()){
//			System.out.println("key "+K+ " value "+le.get(K));
//		}
		
	}
	
		public void testGetMarkers(){
			String url ="https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
			EarthquakeCityAssign E=new EarthquakeCityAssign();
			E.getMarkers(url);
		
		}

}

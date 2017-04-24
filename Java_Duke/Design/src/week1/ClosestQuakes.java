package week1;

import java.util.ArrayList;

public class ClosestQuakes {

	public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakes, Location city, int numQuakes){
		ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
		int minIndex=0;
		ArrayList<QuakeEntry> quakeCopy = new ArrayList<QuakeEntry>(quakes);
		int num = numQuakes;
		if(quakes.size()<num){num=quakes.size();}
	for(int j=0;j<numQuakes;j++){
		for(int k=1; k<quakeCopy.size();k++){
			QuakeEntry quake = quakeCopy.get(k);
			float currentDist=quake.distanceTo(city);
			float minDist=quakeCopy.get(minIndex).distanceTo(city);
			if(currentDist<minDist){
				minIndex=k;
			}
		}
			ret.add(quakeCopy.get(minIndex));
			quakeCopy.remove(minIndex);
	}
			return ret;
	}
	
	public void findClosestQuakes(){
		EarthQuakeParser parser = new EarthQuakeParser();
       //String source = "C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println(" quakes : " + list);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = getClosest(list, city,10);
        System.out.println("list size = "+close.size());
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
	}
	
}

package week1;
import java.util.ArrayList;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();  
        for (QuakeEntry qe : quakeData) {
            if (qe.distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public  ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakes, double min, double max){
    	ArrayList<QuakeEntry> rightDepth = new ArrayList<QuakeEntry>();
    	for(QuakeEntry q : quakes){
    		if( q.getDepth()>=min && q.getDepth()<=max ){
    			rightDepth.add(q);
    		}
    	}
    	return rightDepth;
    }

    public void quakesOfDepth(){
    	EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> listDeep = filterByDepth(list, -10000,-5000);
        for (QuakeEntry qe : listDeep) {
           System.out.println(qe); 
        }
    }
    	
    public void dumpCSV(ArrayList<QuakeEntry> list){
    
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakes, String where, String phrase){
    	ArrayList<QuakeEntry> phraseList = new ArrayList<QuakeEntry>();
    	
    	//if(where.equals("start") && quake.in)
    	
    	return phraseList;
    	
    }
    
	public void bigQuakes() {
	    EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        float bigMag=5;
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, bigMag);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("number of quakes above magnitue "+ bigMag +" is "+listBig.size());
	}
	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
       // Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000, city);
        System.out.println("list size = "+close.size());
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }
}

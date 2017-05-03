package ooJava.week6;

import java.util.Comparator;

public class QuakeComparator implements Comparator<EarthquakeMarker> {

	public int compare(EarthquakeMarker e1, EarthquakeMarker e2){
		return Double.compare(e1.getDepth(), e2.getDepth());
	}
	
	
}

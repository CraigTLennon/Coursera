package week2;

import java.util.Comparator;

import week1.Location;
import week1.QuakeEntry;

public class DistanceComparator implements Comparator<QuakeEntry> {

	Location fromWhere;

	public DistanceComparator(Location fromWhere) {
		super();
		this.fromWhere = fromWhere;
	}
	
	public int compare(QuakeEntry qe1,QuakeEntry qe2){
		double dist1=qe1.distanceTo(fromWhere);
		double dist2=qe2.distanceTo(fromWhere);
		return Double.compare(dist1, dist2);
	}
	
	
	
}

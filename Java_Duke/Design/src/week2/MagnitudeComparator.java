package week2;

import java.util.Comparator;

import week1.QuakeEntry;

public class MagnitudeComparator implements Comparator<QuakeEntry> {

	public int compare(QuakeEntry qe1,QuakeEntry qe2){
		return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
	}
	
	
}

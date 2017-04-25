package week2;

import java.util.Comparator;

import week1.QuakeEntry;

public class ReverseMagnitudeComparator implements Comparator<QuakeEntry> {

	public int compare(QuakeEntry qe1,QuakeEntry qe2){
		return -1*Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
	}
}

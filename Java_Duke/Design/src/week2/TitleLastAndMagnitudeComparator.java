package week2;

import java.util.Comparator;

import week1.QuakeEntry;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

	public int compLast(QuakeEntry qe1,QuakeEntry qe2){
		
		String [] split1=qe1.getInfo().split(" ");
		String [] split2=qe2.getInfo().split(" ");
		String last1=split1[split1.length-1];
		String last2=split2[split2.length-1];
		return last1.compareTo(last2);
		}
	
	public int compMag(QuakeEntry qe1,QuakeEntry qe2){
		return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
	}
	
	public int compare( QuakeEntry qe1,QuakeEntry qe2){
		int title=compLast(qe1,qe2);
		if(title==0){return compMag(qe1,qe2);}
		return title;
	}
	
}

package week2;

import java.util.Comparator;

import week1.QuakeEntry;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

	public int compTitle(QuakeEntry qe1,QuakeEntry qe2){
		String title1=qe1.getInfo();
		String title2=qe2.getInfo();
		return title1.compareTo(title2);
	}

	public int compareDepth(QuakeEntry qe1,QuakeEntry qe2){
		return Double.compare( qe1.getDepth(),qe2.getDepth());
	}
	
	public int compare(QuakeEntry qe1,QuakeEntry qe2){
		int title=compTitle(qe1,qe2);
		if(title==0){return compareDepth(qe1,qe2);}
		return title;
	}



}

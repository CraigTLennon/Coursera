package week2;

import java.util.ArrayList;
import java.util.Collections;

import week1.EarthQuakeParser;
import week1.Location;
import week1.QuakeEntry;

public class Tester {

	public static void main(String[] args) {
		Tester T = new Tester();
//		T.test();
//		T.testDistanceSort();
		//T.testString();
//		T.testQuakeCompare();
		T.testDifferentSorters();
	}
	public void testDifferentSorters(){
		DifferentSorters D= new DifferentSorters();
		//D.sortWithCompareTo();
//		D.sortByTitleAndDepth();
		D.sortByLastWordInTitleThenByMagnitude();
	}
	
	public void test(){
		EarthQuakeParser parser = new EarthQuakeParser();
		SelectionSort S = new SelectionSort();
		 String source = "data/nov20quakedatasmall.atom";
	     ArrayList<QuakeEntry> list  = parser.read(source);         
	     System.out.println("read data for "+list.size()+" quakes");
//	     System.out.println(list);
//		 System.out.println(S.sortByMagnitude(list));
//	     S.sortByMagnitudeInPlace(list);
//	     System.out.println(list);
//	     System.out.println(S.sortByDepth(list));
//	     S.sortByDepthInPlace(list);
//	     System.out.println(list);
//	     BubbleSort B = new BubbleSort();
//	     B.sortByMagnitudeInPlace(list);
//	     Collections.sort(list);
	     
//	     Collections.sort(list,new MagnitudeComparator());
//	     System.out.println(list);
//	     Collections.sort(list,new ReverseMagnitudeComparator());
//	     System.out.println(list);


	}

	public void testDistanceSort(){
		EarthQuakeParser parser = new EarthQuakeParser();
		SelectionSort S = new SelectionSort();
		String source = "data/nov20quakedatasmall.atom";
	    ArrayList<QuakeEntry> list  = parser.read(source);         
	    System.out.println("read data for "+list.size()+" quakes");
//	    Location where = new Location(35.9886,-79.9072);    
//	    Collections.sort(list,new DistanceComparator(where));
	    Collections.sort(list);
	    for(QuakeEntry Q :list){
	    	System.out.println(Q);
	    }	
	}
	
	public void testString(){
		String[] list = {"lion","puma","cheetah","leopard","tiger"};
		SelectionSort.selectSort(list);
		for(String L : list){System.out.println(L);}
	}
	
	public void testQuakeCompare(){
		EarthQuakeParser parser = new EarthQuakeParser();
		SelectionSort S = new SelectionSort();
		String source = "data/nov20quakedatasmall.atom";
	    ArrayList<QuakeEntry> list  = parser.read(source);         
	    System.out.println("read data for "+list.size()+" quakes");
	    Collections.sort(list);
	    for(QuakeEntry Q :list){
	    	System.out.println(Q);
	    }
	    
	    
	}
}

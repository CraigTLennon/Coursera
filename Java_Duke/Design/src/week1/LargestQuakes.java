package week1;

import java.util.ArrayList;

public class LargestQuakes {

	public static void main(String[] args) {
		LargestQuakes L = new LargestQuakes();
		L.findLargestQuakes();
	}
	public void findLargestQuakes(){
		EarthQuakeParser parser = new EarthQuakeParser();
	       String source = "C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/data/nov20quakedata.atom";
//	        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	        ArrayList<QuakeEntry> list = parser.read(source);
//	        System.out.println(" quakes : " + list);
	        System.out.println("# quakes read: " + list.size());
	        QuakeEntry q = list.get(indexOfLargest(list));
	        System.out.println("Largest quake: "+ q.getInfo()+" magnitued "+q.getMagnitude());
	        System.out.println(getLargest(list,5));
	 }
	
	public int indexOfLargest(ArrayList<QuakeEntry> data){
		int largestIndex=0;
		
		for(int dex =1;dex<data.size();dex++){
			if(data.get(largestIndex).getMagnitude()<data.get(dex).getMagnitude()){
				largestIndex=dex;
			}
		}
		return largestIndex;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){ //With ties for max value it keeps the first <= howMany of the max value then proceeds to lower values.
		ArrayList<QuakeEntry> largestQuakes=new ArrayList<QuakeEntry>();
		int num = Math.min(howMany, quakeData.size());
		ArrayList<QuakeEntry> qCopy=new ArrayList<QuakeEntry>(quakeData);
		
		for(int j=0;j<num;j++){
			int minInd=-1;
			double minVal=0.0;
			for(QuakeEntry q: qCopy){
				if(q.getMagnitude()>minVal){
					minInd=qCopy.indexOf(q);
					minVal=q.getMagnitude();
				}
			}
			largestQuakes.add(qCopy.get(minInd));
		}
		 return largestQuakes;
	}
}

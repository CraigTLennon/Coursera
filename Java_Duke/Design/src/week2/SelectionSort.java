package week2;

import java.util.ArrayList;

import week1.QuakeEntry;

public class SelectionSort {

	public static void selectSort(String[] list){
		for(int k=0;k<list.length;k++){
			int mindex=k;
			for(int j=k+1;j<list.length;j++){
				if(list[j].compareTo(list[mindex]) <0){
					mindex=j;
				}
			}
		String temp=list[k];
		list[k]=list[mindex];
		list[mindex]=temp;
		}
	}
	
	public static void selectSort(ArrayList<String> list){
		for(int k=0;k<list.size();k++){
			int mindex=k;
			for(int j=k+1;j<list.size();j++){
				if(list.get(k).compareTo(list.get(mindex)) <0){
					mindex=j;
				}
			}
		String temp=list.get(k);
		list.set(k,list.get(mindex));
		list.set(mindex,temp);
		}
	}	
	
	private int indexLargestDepth(ArrayList<QuakeEntry> data,int from){
		if(!data.isEmpty()){
		int minInd=from;
		for(int k=from+1;k<data.size();k++){
			if(data.get(minInd).getDepth()<data.get(k).getDepth()){
				minInd=k;
			}
		}
		return minInd;
		}
		return -1;
	}

	private int indexMinMag(ArrayList<QuakeEntry> data,int from){
		if(!data.isEmpty()){
		int minInd=from;
		for(int k=from+1;k<data.size();k++){
			if(data.get(minInd).getMagnitude()>data.get(k).getMagnitude()){
				minInd=k;
			}
		}
		return minInd;
		}
		return -1;
	}
	
	public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
		ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
		while(!in.isEmpty()){
			int minInd=indexMinMag(in);
			out.add(in.get(minInd));
			in.remove(minInd);
		}
		return out;
	}

	public void sortByMagnitudeInPlace(ArrayList<QuakeEntry> in){//selection sort in place.
		for(int j=0;j<in.size();j++){
			int minInd=indexMinMag(in,j );
			QuakeEntry tempQuake=in.get(minInd);
			in.set(minInd, in.get(j));
			in.set(j, tempQuake);
		}
	}

	public void sortByDepthInPlace(ArrayList<QuakeEntry> in){//selection sort in place.
		for(int j=0;j<in.size();j++){
			int minInd=indexLargestDepth(in,j );
			QuakeEntry tempQuake=in.get(minInd);
			in.set(minInd, in.get(j));
			in.set(j, tempQuake);
		}
	}
	
	public ArrayList<QuakeEntry> sortByDepth(ArrayList<QuakeEntry> in){
		ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
		while(!in.isEmpty()){
			int minInd=indexLargestDepth(in,0);
			out.add(in.get(minInd));
			in.remove(minInd);
		}
		return out;
	}

	
		
	
	private int indexMinMag(ArrayList<QuakeEntry> data){
		return indexMinMag(data,0);
	}


}

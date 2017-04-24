package week2;

import java.util.ArrayList;
import java.util.Collections;

import week1.QuakeEntry;

public class BubbleSort {

	
//	public void sortByMagnitudeInPlace(ArrayList<QuakeEntry> data){
//		for(int numPass=0;numPass<data.size();numPass++){
//			onePass(data,numPass);
//		}
//	}

	public void sortByMagnitudeInPlace(ArrayList<QuakeEntry> data){
		data.removeAll(Collections.singleton(null));

		while(!isInOrderOfMagnitude(data) ){
		
			onePass(data,0);
		}
	}
	
	public void onePass(ArrayList<QuakeEntry> data,int numPassesDone){
		
		int limit=data.size()-numPassesDone;
		for(int j=0 ; j<limit-1;j++){
			if(data.get(j).getMagnitude()>data.get(j+1).getMagnitude() ){
				QuakeEntry oldJ=data.get(j);
				data.set(j, data.get(j+1));
				data.set(j+1, oldJ);
			}			
		}
	}
	
	public boolean isInOrderOfMagnitude(ArrayList<QuakeEntry> data){
		
		for(int j=0 ; j<data.size()-1; j++ ){
			if(data.get(j).getMagnitude() > data.get(j+1).getMagnitude()  ){
//				System.out.println("here "+data.get(j).getMagnitude()+" "+data.get(j+1).getMagnitude());
				return false;
				}
			}

		return true;

	}
	
	
	
}

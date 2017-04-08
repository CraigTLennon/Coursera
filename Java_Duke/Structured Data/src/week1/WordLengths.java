package week1;

import edu.duke.FileResource;

public class WordLengths {

	public static void main(String[] args) {
		WordLengths W = new WordLengths();
		W.test();
	}
	public void test(){
		FileResource fr = new FileResource();
		int[] counts=new int[10];
		countWordLengths(fr,counts);
		
	}
	public int indexOfMax(int[] counts){
		int ind=-1;
		int maxi=-1;
		for(int k =0;k<counts.length;k++){
			if(maxi<counts[k]){ind=k;maxi=counts[k];}
		}
		return ind;
	}
	
	public void countWordLengths(FileResource resource, int[] counts){
		
		for(String words : resource.words()){
			int wordLength=0;
			for(int k=0;k<words.length();k++){
				Character ch = words.charAt(k);
				if(Character.isLetter(ch) || ch=='-'){
					wordLength+=1;
				}
			}
			if(wordLength<counts.length){
				counts[wordLength]+=1;
			}else{
				counts[counts.length-1]+=1;
			}
		}
		for(int k=0;k<counts.length;k++){
			System.out.println("The # words of length "+k+" are :"+ counts[k]);
		}
		System.out.println("The largest # of words are of length :"+indexOfMax(counts));
		
	}
	

}

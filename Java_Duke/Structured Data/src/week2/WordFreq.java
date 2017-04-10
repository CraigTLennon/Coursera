package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFreq {

	public static void main(String[] args) {
		WordFreq W=new WordFreq();
		W.test();
	}
	
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFreq(){
		myWords=new ArrayList<String>();
		myFreqs=new ArrayList<Integer>();
	}
	
	public void findUnique(){
		
		myWords.clear();
		myFreqs.clear();
		FileResource resource = new FileResource();
		
		for(String s : resource.words()){
			s=s.toLowerCase();
			int index = myWords.indexOf(s);
			if(index== -1){
				myWords.add(s);
				myFreqs.add(1);
			}else{
				int value=myFreqs.get(index);
				myFreqs.set(index,value+1);
			}
		}}
	public int findIndexOfMax(){
		int biggestIndex=0;
		int bigValue=0;
		for(int k=0;k<myFreqs.size();k++){
			if(myFreqs.get(k)>bigValue){
				bigValue=myFreqs.get(k);
				biggestIndex=k;
			}
		}
		return biggestIndex;
	}
	
	public void test(){
		findUnique();
		System.out.println("Number of unique words is "+myWords.size());
		for(int k=0;k<myFreqs.size();k++){
			System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
		}
		int biggestInd=findIndexOfMax();
		int biggestValue=myFreqs.get(biggestInd);
		String commonWord=myWords.get(biggestInd);
		System.out.println(biggestInd);
		System.out.println("Most common word is '"+ commonWord+"' which appeared "+biggestValue +" times.");
	}

}

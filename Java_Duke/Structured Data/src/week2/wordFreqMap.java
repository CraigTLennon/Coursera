package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class wordFreqMap {

	public static void main(String[] args) {
		wordFreqMap W = new wordFreqMap();
		W.test();
	}
	
	public void countWords(){
		FileResource f=new FileResource();
		int total =0;
		HashMap<String,Integer> wordCountMap=new HashMap<String,Integer>(); 
		for(String w : f.words()){
			w=w.toLowerCase();
			if(!wordCountMap.containsKey(w)){
				wordCountMap.put(w, 1);
			}else{
				wordCountMap.put(w,wordCountMap.get(w)+1);
			}
			total=total+1;
		}
		System.out.println("total # words is "+total);
		System.out.println("# unique words is "+wordCountMap.size());
		printCommmonWords(wordCountMap);
	}
	
	public void printCommmonWords(HashMap<String,Integer> map){
		for(String w : map.keySet()){
			int occurences = map.get(w);
			if(occurences>500){
				System.out.println("The common word "+w+" occurs "+occurences +" times");
			}
		}
	}
	
	public void test(){
		countWords();
	}

}

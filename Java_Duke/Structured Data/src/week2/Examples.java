package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class Examples {

	
	//Hashmap example
	public void countWordsMap(){
		FileResource f = new FileResource();
		HashMap<String,Integer> map= new HashMap<String,Integer>(); 
		for(String w: f.words()){
			w=w.toLowerCase();
			
			if(!map.containsKey(w)){
				map.put(w, 1);
			}else{
				map.put(w, map.get(w)+1);
			}
		}
	}
	//the keyset method provides an iterable for looping over all keys and get returns the value associated with the key
	public void printWords(HashMap<String,Integer> map){
		for(String s : map.keySet()){
			System.out.println(map.get(s)+ "\t"+s); //prints value and then key
		}
	}
	

	
}

package week2;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class WordsArray {

	public static void main(String[] args) {
		WordsArray W=new WordsArray();
		W.test();
	}
	
	private StorageResource myWords;
	
	public WordsArray(){
		myWords=new StorageResource();
		
	}
	
	public void readWords(){
		myWords.clear();
		FileResource resource=new FileResource();
		for(String word : resource.words()){
			myWords.add(word.toLowerCase());
		}
	}
	
	public boolean contains(String[] list, String word,int number){
		for(int k=0;k<number;k++){
			if(list[k].equals(word)){
				return true;
			}}
		return false;
	}
	
	public int numberOfUniqueWords(){
		int numStored=0;
		String[] words=new String[myWords.size()];
		for(String s:myWords.data()){
			if(! contains(words,s,numStored)){
				words[numStored]=s;
				numStored++;
			}
		}
		return numStored;
	}

	public void test(){
		readWords();
		System.out.println("number of words read "+ myWords.size());
		int unique=numberOfUniqueWords();
		System.out.println("number of unique words is "+ unique);
	}
}
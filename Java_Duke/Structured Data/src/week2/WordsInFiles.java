package week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {

	public static void main(String[] args) {
		WordsInFiles W = new WordsInFiles();
		W.test();
	}
	
	private HashMap<String,ArrayList<String>> wordFileList;
	
	public WordsInFiles(){
		wordFileList=new HashMap<String,ArrayList<String>>();
		
	}
	
	private void addWordsFromFile(File F){
		FileResource fr=new FileResource(F);
		for(String word : fr.words()){
			if(wordFileList.containsKey(word)){
				wordFileList.get(word).add(F.getName());
			}else{
				ArrayList<String> arr= new ArrayList<String>();
				arr.add(F.getName());
				wordFileList.put(word , arr);
			}
		}
	}
	
	public void buildWordsFileMap(){
		wordFileList.clear();
		DirectoryResource dir=new DirectoryResource();
		for(File f : dir.selectedFiles()){
			addWordsFromFile(f);
		}
	}
	
	public int maxNumber(){
		int maxW = 0;
		for(String word : wordFileList.keySet()){
			if(wordFileList.get(word).size()>maxW){
				maxW=wordFileList.get(word).size();
			}
		}
		return maxW;
	}
	
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> selectWords=new ArrayList<String>(); 
		for(String word : wordFileList.keySet()){
			if(wordFileList.get(word).size()==number){
				selectWords.add(word);
			}
		}
		return selectWords;
	}

	
	public void printFileIn(String word){
		ArrayList<String> selectFiles= wordFileList.get(word);
		for(String fl : selectFiles){
			System.out.println(fl);
		}
	}
	
	public void test(){
		buildWordsFileMap();
		int mx=maxNumber();
		System.out.println("The max number of files a word is in is "+mx);
		ArrayList<String> someWords=wordsInNumFiles(mx);
		for(String wd : someWords){
			System.out.println(wd);
			printFileIn(wd);
		}
		
	}
	
}
	



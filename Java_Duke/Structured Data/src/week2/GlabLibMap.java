package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GlabLibMap {

	public static void main(String[] args) {
		GlabLibMap G=new GlabLibMap();
		G.makeStory();
		System.out.println("\n"+G.totalWordsInMap()+" total words in all lists");
		System.out.println(""+G.totalWordsConsidered()+" total words considered in GaldLib");
		
	}

	private HashMap<String, ArrayList<String>> myMap;
	private Random myRandom;
	private String dataSourceURL="";
	private String dataSourceDirectory="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/GladLibData/data";
	private ArrayList<String> used;
	
	public GlabLibMap(){
		myMap=new HashMap<String, ArrayList<String>>();
		myRandom = new Random();
		initializeFromSource(dataSourceDirectory);
		used = new ArrayList<String>();
	}
	
	public int totalWordsInMap(){
		int total =0;
		for(String subject : myMap.keySet()){
			 	total+= myMap.get(subject).size();
		}
		return total;
	}
	public int totalWordsConsidered(){
		int total =0;
		for(String subject : used){
			
			if(subject.equals("number")){
				
				total+=50;
			}else{
				
				total+= myMap.get(subject).size();
			}
			 	
		}
		return total;
	}
	
	
	private String processWord(String w){
		int first =w.indexOf("<");
		int last =w.indexOf(">",first);
		if(first==-1 ||last==-1){
			return w;
		}
		String subject =w.substring(first+1, last);
		if(!used.contains(subject)){
			used.add(subject);
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub=getSubstitute(w.substring(first+1, last));
		return prefix+sub+suffix;
	}
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate(dataSourceDirectory+"/madtemplate.txt");
		printOut(story, 60);
	}
	private String fromTemplate(String source){
		used.clear();
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	public String getSubstitute(String label){
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if(myMap.containsKey(label)){
			return randomFrom(myMap.get(label));
		}
		return "unknown";
	}
	
	private String randomFrom(ArrayList<String> arr){
		int index=myRandom.nextInt(arr.size());
		return arr.get(index);
	}
	
	private void initializeFromSource(String source){
		String[] labels = {"country","noun","animal","adjective","name","color","timeframe"};
		for(String s : labels){
			ArrayList<String> list = readIt(source+"/"+s+".txt");
			myMap.put(s, list);
		}
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
}

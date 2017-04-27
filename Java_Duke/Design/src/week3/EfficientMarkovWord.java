package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {

	private  String [] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram,ArrayList<String>> follows;
	
	public EfficientMarkovWord(int order){
		myOrder=order;
		myRandom=new Random();
		 follows= new HashMap<WordGram,ArrayList<String>>();
	}
	
	private void setFollows(){
		for(int pos =0; pos<myText.length-myOrder;pos++){
			WordGram first=new WordGram(myText,pos,myOrder);
			String follower=myText[pos+myOrder];
			if(follows.containsKey(first)){
				ArrayList<String> newList=follows.get(first);
				newList.add(follower);
				follows.put(first,newList);
			}else{
				ArrayList<String> newList= new ArrayList<String>();
				newList.add(follower);
				follows.put(first, newList);
		}}
	}
	
	@Override
	public void setTraining(String text) {
		myText=text.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
		setFollows();
	}

	private WordGram getStart(){
		int bound=myText.length-myOrder;
		int start=myRandom.nextInt(bound);
		WordGram first=new WordGram(myText,start,myOrder);
		return first;
	}
	
	public String getFollows(WordGram wg){
		ArrayList<String> nextWordList=follows.get(wg);
		int nextWordInd=myRandom.nextInt(nextWordList.size());
		String nextWord=nextWordList.get(nextWordInd);
		return nextWord;
	}
	@Override
	public String getRandomText(int numWords) {
		StringBuilder randomText= new StringBuilder();
		WordGram nextGram =getStart();
		randomText.append(nextGram.toString());
		
		for(int i=myOrder-1;i<numWords;i++){
			String nextWord=getFollows(nextGram);
			randomText.append(nextWord+" ");
			nextGram=nextGram.shiftAdd(nextWord);
		}
		return randomText.toString();
	}

	@Override
	public void setRandom(int seed) {
		myRandom.setSeed(seed);

	}
	

	
	

}

package week3;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordGram implements IMarkovModel {

	
	private  String [] myText;
	private Random myRandom;
	private int myOrder;
	
	public MarkovWordGram(int order){
		myOrder=order;
		myRandom=new Random();
		
	}
	
	@Override
	public void setTraining(String text) {
		myText=text.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
	}

	private WordGram getStart(){
		int bound=myText.length-myOrder;
		int start=myRandom.nextInt(bound);
		WordGram first=new WordGram(myText,start,myOrder);
		return first;
	}
	
	@Override
	public String getRandomText(int numWords) {
		StringBuilder randomText= new StringBuilder();
		WordGram nextGram =getStart();
		randomText.append(nextGram.toString());
		
		for(int i=myOrder-1;i<numWords;i++){
			ArrayList<String> nextWordList=getFollows(nextGram);
			int nextWordInd=myRandom.nextInt(nextWordList.size());
			String nextWord=nextWordList.get(nextWordInd);
			randomText.append(nextWord+" ");
			nextGram=nextGram.shiftAdd(nextWord);
		}
		return randomText.toString();
	}

	@Override
	public void setRandom(int seed) {
		myRandom.setSeed(seed);

	}
	
	public ArrayList<String> getFollows(WordGram kGram){
		ArrayList<String> followerWords=new ArrayList<String>();
		int index=0;
		int start=0;
		while(index!=-1){
			index =indexOf(myText,kGram,start);
			int nextWordInd=index+kGram.length();
			if(nextWordInd<myText.length && index!=-1){
//			System.out.println(nextWordInd);
			followerWords.add(myText[nextWordInd]);}
			start=index+1;
		}
		return followerWords;	
	}
		
	public int indexOf(String[] words,WordGram target, int start){
		for(int i = start;i<words.length-target.length()+1;i++){
			StringBuilder text = new StringBuilder();
			for(int j=i;j<i+target.length();j++){
				text.append(words[j]);
				text.append(" ");
			}
			if(text.toString().equals(target.toString())) return i;
		}
		return -1;
	}

}

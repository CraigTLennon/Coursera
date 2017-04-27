package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {

	private String[] myText;
	private Random myRandom;
	private HashMap<String,ArrayList<String>> follows;
	
	public MarkovWordTwo(){
		myRandom = new Random();
		follows=new HashMap<String,ArrayList<String>>();
	}
	
	private void setFollows(){
		for(int pos =0; pos<myText.length-2;pos++){
			String nextWord=myText[pos]+" "+myText[pos+1];
			String follower=myText[pos+2];
			if(follows.containsKey(nextWord)){
				ArrayList<String> newList=follows.get(nextWord);
				newList.add(follower);
				follows.put(nextWord,newList);
			}else{
				ArrayList<String> newList= new ArrayList<String>();
				newList.add(follower);
				follows.put(nextWord, newList);
		}}
		
	}
	
	
	@Override
	public void setTraining(String text) {
		myText=text.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
		String joined=String.join(" ", myText);
		System.out.println(joined);
		
	}

	@Override
	public String getRandomText(int numChars) {
		if(myText==null) return " ";
		setFollows();
//		System.out.println("Follows "+follows.size());
		if(follows==null) return " ";
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);
		String[] window =new String[3];
		window[0]=myText[index];
		window[1]=myText[index+1];
		String lastTwo = window[0]+" "+ window[1];
		window[2]="";
		sb.append(lastTwo);
		sb.append(" ");
		for(int i=0;i<numChars;i++){
			ArrayList<String> choices =follows.get(lastTwo); 		
			if(choices==null){
				System.out.println("Non-choice");
				window[2]=myText[myRandom.nextInt(myText.length-2)];
			}else{
//			System.out.println("choices "+choices.size());
			index = myRandom.nextInt(choices.size());
			window[2]=choices.get(index);
//			System.out.println("next word "+window[2]);
			}
			//Does not really work on alice b/c the two word combos are too sparse one punctuation and 
			String nextWord=window[2].toString();
			sb.append(nextWord);
			sb.append(" ");
//			System.out.println(sb.toString().trim());	
			window[0]=window[1];
			window[1]=window[2];
			window[2]="";
			lastTwo=window[0].toString()+" "+window[1].toString();
		}

		return sb.toString().trim();
	}
	
	
	
	@Override
	public void setRandom(int seed) {
		myRandom.setSeed(seed);
	}

}

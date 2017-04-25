package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovWordOne implements IMarkovModel {

	private String[] myText;
	private Random myRandom;
	private HashMap<String,ArrayList<String>> follows;
	
	public MarkovWordOne(){
		myRandom = new Random();
		follows=new HashMap<String,ArrayList<String>>();
	}
	
	private void setFollows(){
		for(int pos =0; pos<myText.length-1;pos++){
			String nextWord=myText[pos];
			String follower=myText[pos+1];
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
		myText=text.split("\\s+");
	}

	@Override
	public String getRandomText(int numChars) {
		if(myText==null) return " ";
		setFollows();
		if(follows==null) return " ";
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int i=0;i<numChars;i++){
			ArrayList<String> choices =follows.get(key); 
			key="";
			if(choices==null){key=" ";
			}else{
			index = myRandom.nextInt(choices.size());
			key=choices.get(index);}
			sb.append(key);
			sb.append(" ");
		}
		return sb.toString().trim();
	}
	
	
	
	@Override
	public void setRandom(int seed) {
		myRandom.setSeed(seed);
	}

}

package week3;

import java.util.Random;

public class MarkovZeroA implements IMarkovModel {

	private String myText;
	private Random myRandom;
	
	
	@Override
	public void setTraining(String s){
		myText=s;
	}




	@Override
	public void setRandom(int seed){
		myRandom=new Random(seed);
	}
	
	@Override	
	public String getRandomText(int numChar){
		if(myText==null) return " ";
		
		StringBuilder sb=new StringBuilder();
		for(int k=0;k<numChar;k++){
			int index = myRandom.nextInt(myText.length());
			char next=myText.charAt(index);
			sb.append(next);
		}
		return sb.toString();
	}
}

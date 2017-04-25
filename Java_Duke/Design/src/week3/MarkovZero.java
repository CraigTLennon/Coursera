package week3;

import java.util.Random;

public class MarkovZero {

	private String trainText=" ";
	private Random myRandom;
	
	public void setTraining(String s){
		trainText=s;
	}
	
	
	public MarkovZero() {
		super();
		this.myRandom =new Random();
	}

	public void setRandom(int seed){
		myRandom=new Random(seed);
	}


	public String getRandomText(int numChar){
		if(trainText==null) return " ";
		
		StringBuilder sb=new StringBuilder();
		for(int k=0;k<numChar;k++){
			int index = myRandom.nextInt(trainText.length());
			char next=trainText.charAt(index);
			sb.append(next);
		}
		return sb.toString();
	}
}

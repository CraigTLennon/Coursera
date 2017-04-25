package week3;

import java.util.Random;

public abstract class MarkovA {

	protected Random myRandom;
	protected String myText;
	
	public void setTraining(String s){
		myText=s;
	}
	
	public void setRandom(int seed){
		myRandom=new Random(seed);
	}
	
	abstract public String getRandomText(int numChars);
	
	
}

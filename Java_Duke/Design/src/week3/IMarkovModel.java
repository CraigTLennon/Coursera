package week3;

import java.util.Random;

public interface IMarkovModel {

	public void setTraining(String text);
	public String getRandomText(int numChars);
	public void setRandom(int seed);
	
	
}

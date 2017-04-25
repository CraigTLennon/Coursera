package week3;

import edu.duke.FileResource;

public class MarkovTest {
	public static void main(String[] args) {
		MarkovTest T = new MarkovTest();
//		T.zeroTest();
//		T.oneTest();
//		T.kTest();
//		T.abstractTest();
//		T.interfaceTest();
		T.markovWordTest();
	}
	
	public void markovWordTest(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		MarkovWordOne M = new MarkovWordOne();
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M.setRandom(2);
		String text=runModel(M,oneLine,100);
		System.out.println(text);
	}
	
	public String runModel(IMarkovModel M,String s, int numChar){
		M.setTraining(s);
		String randomText=M.getRandomText(1000);
		return randomText;
	}
	
	public void zeroTest(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		MarkovZero M = new MarkovZero();
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M.setTraining(oneLine);
		M.setRandom(2);
		String randomText=M.getRandomText(1000);
		System.out.println(randomText);
	}
	
	public void oneTest(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		MarkovOne M1 = new MarkovOne();
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M1.setTraining(oneLine);
		M1.setRandom(2);
		String randomText=M1.getRandomText(1000);
		System.out.println(randomText);
	}

	public void kTest(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		MarkovK M = new MarkovK(6);
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M.setTraining(oneLine);
		M.setRandom(2);
		String randomText=M.getRandomText(10000);
		System.out.println(randomText);
	}
	
	public void interfaceTest(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		MarkovZeroA M = new MarkovZeroA();
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M.setTraining(oneLine);
		M.setRandom(2);
		String randomText=M.getRandomText(10000);
		System.out.println(randomText);		
	}
	
	public void abstractTest(){
		MarkovN M = new MarkovN(5);
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		M.setTraining(oneLine);
		M.setRandom(2);
		String randomText=M.getRandomText(10000);
		System.out.println(randomText);
	}
}

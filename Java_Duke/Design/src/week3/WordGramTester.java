package week3;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordGramTester {

	public static void main(String[] args) {
		WordGramTester T = new WordGramTester();
//		T.testWordGram();
//		T.testEquals();
//		T.testShift();
//		T.testIndexOf();
//		T.testGetFollows();
//		T.testWordGramRandomText();
		T.testEfficientMarkov();
	}
	
	public void testWordGram(){
		String source = "this is a test this is a test this is not a real test";
		String[] words = source.split("\\s+");
		int size =4;
		
		for (int index = 0; index <words.length-size;index++){
			WordGram wg= new WordGram(words, index, size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	public void testEquals(){
		String source = "this is a test this is a test this is not a real test";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> wgs=new ArrayList<WordGram>();
		int size =4;
		
		for (int index = 0; index <words.length-size;index++){
			WordGram wg= new WordGram(words, index, size);
			wgs.add(wg);
//			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
		WordGram example=wgs.get(0);
		for(WordGram w : wgs){
			if(w.equals(example)){
				System.out.println("Match found");
				System.out.println(w);
				System.out.println(example);
			}
		}
	}
	public void testShift(){
		WordGram example = new WordGram(new String[] {"the","quick","brown","fox"},0,4);
		WordGram exampleShift=example.shiftAdd("jumped");
		System.out.println(example);
		System.out.println(exampleShift);
	}

	public void testIndexOf(){
		String [] text=new String[] {"the","start","is","at","the","start"};
		WordGram wg=new WordGram(new String[] {"the","start"},0,2);
		MarkovWordGram M = new  MarkovWordGram(2);
		int ind=M.indexOf(text, wg, 1);
		System.out.println(ind);
		System.out.println(wg);
	}

	public void testGetFollows(){
		String stringText="the start is at the start";
		String [] text=new String[] {"the","start","is","at","the","start"};
		WordGram wg=new WordGram(new String[] {"the","start"},0,2);
		MarkovWordGram M = new  MarkovWordGram(2);
		M.setTraining(stringText);
		ArrayList<String> f=M.getFollows(wg);
		for(int i=0;i<f.size();i++){
			System.out.println(f.get(i));
		}
//		System.out.println(wg);
	}
	
	public void testEfficientMarkov(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		EfficientMarkovWord E =new EfficientMarkovWord(2);
		E.setTraining(oneLine);
		E.setRandom(2);
		String text=E.getRandomText(100);
		System.out.println(text);
		
	}
	
	public void testWordGramRandomText(){
		String fname="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Design/src/week3/data/alice.txt";
		FileResource fl = new FileResource(fname);
		String oneLine=fl.asString().replace('\n', ' ');
		MarkovWordGram M = new  MarkovWordGram(5);
		M.setTraining(oneLine);
		M.setRandom(2);
		String text=M.getRandomText(100);
		System.out.println(text);
	}
}

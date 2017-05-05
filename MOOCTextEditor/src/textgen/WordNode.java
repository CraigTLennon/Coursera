package textgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordNode {

	private String word;
	private List<String> nextWords;
	
	public WordNode(String word){
		this.word=word;
		nextWords = new ArrayList<String>();
	}
	
	public String getWord(){
		return this.word;
	}
	
	public void addNextWord(String nextWord){
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random gen){
		int ind=gen.nextInt(nextWords.size());
		return nextWords.get(ind);
	}
	
	
}

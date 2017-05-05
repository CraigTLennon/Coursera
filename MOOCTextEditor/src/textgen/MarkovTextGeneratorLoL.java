package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import document.BasicDocument;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		sourceText=sourceText.toLowerCase();
		if(sourceText.length()==0) throw new NullPointerException("Text cannot be empty");
		BasicDocument text=new BasicDocument(sourceText);
		String[] words=text.getWords();
//		System.out.println(words.length+" is the number of words");
		if(words.length==0) throw new NullPointerException("Text must contain words");
		starter=words[0];
		for(int i=0;i<words.length-1;i++){
			int index = getIndex(words[i]);
//			System.out.println(words[i]+" is at index "+index+" and the next word is "+ words[i+1]);
			if(index>-1){
				wordList.get(index).addNextWord(words[i+1]);
			}else{
				ListNode newWord = new  ListNode(words[i]);
				newWord.addNextWord(words[i+1]);
				wordList.add(newWord);
			}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
//		System.out.println("starter "+starter);
		int ind=getIndex(starter);
//		System.out.println("ind starter "+ind);
	    ListNode prevWord=wordList.get(ind);
	    StringBuilder sb= new StringBuilder(prevWord.getWord());
	    sb.append(prevWord.getWord());
		for(int i=0;i<numWords;i++){
	    	String nextWord=prevWord.getRandomNextWord(rnGenerator);
	    	sb.append(" "+nextWord);
	    	System.out.println(nextWord);
	    	int index=getIndex(nextWord);
	    	if(index==-1) index=rnGenerator.nextInt(wordList.size());
	    		prevWord=wordList.get(index);
				}
		return sb.toString();
	}
	
	private int getIndex(String word){
		
		if(word==null) throw new NullPointerException("Word cannot be null");
		if(wordList.size()==0) return -1;
		for(int index=0;index<wordList.size();index++){
			String nextWord=wordList.get(index).getWord();
			if(word.equals(nextWord)) return index;
		}
		return -1;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		this.train(sourceText);
		
	}
	
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
//		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode 
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	public boolean equals(ListNode other){
		return other.getWord().equals(this.word);
	}
	
	public boolean equals(String other){
		return other.equals(this.word);
	}
	
	public int hashCode(){
		return this.word.hashCode();
	}
		
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		if(nextWords.size()==0)return word;
		int ind = generator.nextInt(nextWords.size());
	    return nextWords.get(ind);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



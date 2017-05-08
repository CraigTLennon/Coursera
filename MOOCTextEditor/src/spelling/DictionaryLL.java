package spelling;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    public DictionaryLL(){
    	dict=new LinkedList<String>();
    }
    
    public DictionaryLL(Collection<String> collect){
    	dict=new LinkedList<String>();
    	for(String s : collect){
    		addWord(s);
    	}
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	word=word.toLowerCase();
    	if(isWord(word))return false;
//    	String lowerBound="";
//    	int lowerBoundInd=-1;
//    	for(String s: dict){
//    		if(s.compareTo(lowerBound)>0 && s.compareTo(word)<0){
//    			lowerBound=s;
//    			lowerBoundInd=dict.indexOf(s);
//    		}
//    	}
//    	dict.add(lowerBoundInd+1, word);
    	dict.add(word);
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        
        return dict.contains(s.toLowerCase());
    }

    
}

package spelling;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
   public DictionaryBST(){
	   dict = new TreeSet<String>();
   }

   public DictionaryBST(String s){
	   dict = new TreeSet<String>();
	   addWord(s);
   }
   public DictionaryBST(Collection<String> c){
	   dict = new TreeSet<String>();
	   for(String s: c){
	   addWord(s);}
   }   
   

    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	word=word.toLowerCase();
    	if(isWord(word))return false;
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
    	String word=s.toLowerCase();
        return dict.contains(word);
    }

}

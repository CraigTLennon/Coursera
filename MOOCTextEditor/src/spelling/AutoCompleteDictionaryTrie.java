package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		word=word.toLowerCase();
		if(isWord(word)) return false;
	    //for each character, see if trieNode exists.  If not create.  When get to last letter change existing to is word or make new isword.
		TrieNode current = root;
		for(int charInd=0;charInd<word.length();charInd++){
			char ch = word.charAt(charInd);
			TrieNode chChild = current.getChild(ch);
			if(chChild==null){
				current =current.insert(ch);
			}else{
				current=chChild;
			}
		}
		
		current.setEndsWord(true);
		
		if(isWord(word)){size+=1;
	    return true;}else{
	    	System.out.println("error adding "+word);
	    	return false;}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		String word=s.toLowerCase();
		TrieNode current = root;
		for(int charInd=0;charInd<word.length();charInd++){
			char ch = word.charAt(charInd);
			TrieNode chChild = current.getChild(ch);
			if(chChild==null){
				return false;
			}else{
				current=chChild;
			}
		}
		
		return current.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 LinkedList<String >result=new LinkedList<String>();
    	 LinkedList<TrieNode> que =new LinkedList<TrieNode>();
    	 TrieNode stem=findStem(prefix);
    	 if(stem==null) return result;
    	 que.add(stem);
 		while(!que.isEmpty() && result.size()<numCompletions){
 			TrieNode current = que.remove();
 				String currentString=current.getText();
// 				System.out.println("current s is "+currentString);
 				if(isWord(currentString)) result.add(currentString);
 				Set<Character> childSet=current.getValidNextCharacters();
 				for(Character ch : childSet){
 					TrieNode nextNode= current.getChild(ch);
 					que.add(nextNode);
 				}	
 		}
        return result;
     }

     private TrieNode findStem(String prefix){
    	 prefix=prefix.toLowerCase();
    	 TrieNode current = root;
    	 for(int charInd=0;charInd<prefix.length();charInd++){
 			char ch = prefix.charAt(charInd);
 			TrieNode chChild = current.getChild(ch);
 			if(chChild==null){
 				return null;
 			}else{
 				current=chChild;
 			}
 		}
    	 return current;
     }
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
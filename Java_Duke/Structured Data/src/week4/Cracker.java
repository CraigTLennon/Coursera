package week4;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Cracker {

	public static void main(String[] args) {
		Cracker C = new Cracker();
		C.breakVigenere();
	}
	
	public String sliceString(String message, int whichSlice, int totalSlices){
		StringBuilder sb= new StringBuilder();
		int len = message.length();
		for(int k=whichSlice;k<len;k+=totalSlices){
			char ch=message.charAt(k);
			sb.append(ch);
		}
		return sb.toString();
	}
	
	public ArrayList<String> splitMessage(String message, int keyLength){
		ArrayList<String> splitMessages=new ArrayList<String>();
		for(int k=0;k<keyLength;k++){
			String nextMessage=sliceString(message,k,keyLength);
			splitMessages.add(nextMessage);
		}
		return splitMessages;
	}

	public int[] tryKeyLength(String encrypted, int klength,String mostCommon){
		CaesarCracker CC= new CaesarCracker(0,mostCommon);
		String[] messages=new String[klength];
		int[] keys = new int[klength];
		for(int k =0;k<klength;k++){
			messages[k]=sliceString(encrypted,k,klength);
			keys[k]=CC.getKey(messages[k]); //added - 5 must remove
		}		
		return keys;
	}
	
	public String combineStrings(ArrayList<String> stringParts){
		StringBuilder sb=new StringBuilder();
		
		for(int k=0;k<stringParts.get(0).length();k++){ //the length of the string in posn 0 of arraylist
			for(int j=0;j<stringParts.size();j++){ // the number of strings in the list
				String currentMessage=stringParts.get(j); //the message is posn j
				if( currentMessage.length()>k){ // make sure long enough
					sb.append(currentMessage.charAt(k));
				}
			}
		}
		return sb.toString();
	}
	
	public String decrypt(String message, int[] keys){
		
		ArrayList<String> splitMsg=splitMessage(message, keys.length);
		ArrayList<String> crypt=new ArrayList<String>();
		for(int k=0;k<keys.length;k++){
			int encKey=keys[k];
		//	System.out.println(encKey);
			CaesarCracker CC= new CaesarCracker(encKey,"e");
			String dec=CC.decrypt(splitMsg.get(k));
			crypt.add(k,dec);
		}
		String decMsg=combineStrings(crypt);
		//System.out.println(decMsg);
		return decMsg;
	}

	
	
	public HashSet<String> readDictionary(FileResource fr){
		HashSet<String> commonWords = new HashSet<String>();
		for(String line : fr.lines()){
			commonWords.add(line);
		}
		
		return commonWords;
	}
	public String breakForLanguage(String encrypted, HashSet<String> dictionary,String mostCommon){
		int[] len=new int[101];
		String[] decrypt=new String[101];
		int maxInd=0;
		int maxVal=0;
		for(int kLen=1;kLen<101;kLen++){
			int[] keys = tryKeyLength(encrypted,kLen,mostCommon);
			String decrypted=decrypt(encrypted,keys);
			len[kLen]=countWords(decrypted, dictionary);
			decrypt[kLen]=decrypted;
			if(len[kLen]>maxVal){
				maxInd=kLen;
				maxVal=len[kLen];
			}
		}	
	//	System.out.println(maxInd);
		return decrypt[maxInd];
	}
	
	public String breakForAllLanguages(String encrypted, ArrayList<HashSet<String>> allDicts){
		HashMap<String,Integer> decryptCount = new HashMap<String,Integer>();
		for(HashSet<String> dict : allDicts){
			String mostCommon= mostCommonLetter(dict);
			String decrypted = breakForLanguage(encrypted, dict, mostCommon);
			int count = countWords(decrypted, dict); 
			decryptCount.put(decrypted, count);
		}
		int maxCount=0;
		String maxDec="";
		for(String dec : decryptCount.keySet()){
			if(decryptCount.get(dec)>maxCount ){
				maxCount=decryptCount.get(dec);
				maxDec=dec;
			}
		}
		return maxDec;
	 }
	
	public int countWords(String message, HashSet<String> dictionary){
		String[] words=message.split("\\W+");
		int count=0;
		for(String w:words){
			if(dictionary.contains(w.toLowerCase())){
				count+=1;
			}
		}
	//	System.out.println(count);
		return count;
	}
	
	public String mostCommonLetter(HashSet<String> dictionary){
		HashMap<Character,Integer> letters = new HashMap<Character,Integer>();
		for(String word : dictionary){
			for(int k=0;k<word.length();k++){
				Character c = word.charAt(k);
				if(letters.containsKey(c)){
					letters.put(c, letters.get(c)+1);
				}else{
					letters.put(c, 1);
				}
			}}
		Character maxLetter = 'z';  //clearly wrong for debugging
		int maxValue=0;
		for(Character c : letters.keySet()){
			if(letters.get(c)>maxValue){
				maxValue=letters.get(c);
				maxLetter = c;
			}
		}
		System.out.println("most common letter is "+ maxLetter);
		return Character.toString(maxLetter);
	}
	
	
	
	public void breakVigenere(){
		ArrayList<HashSet<String>> allDicts=new ArrayList<HashSet<String>>();
		DirectoryResource dir=new DirectoryResource();
		FileResource frMsg = new FileResource();
		String message =frMsg.asString();
		for(File dict : dir.selectedFiles()){
			FileResource frDict = new FileResource(dict);
			HashSet<String> Dict=readDictionary(frDict);
			allDicts.add(Dict);
			}
		String decrypt = breakForAllLanguages(message,allDicts);
		
				System.out.print(decrypt);
	}
	public void breakVigenere(String mName,String dName){
		FileResource frMsg = new FileResource(mName);
		FileResource frDict = new FileResource(dName);
		String message =frMsg.asString();
		HashSet<String> Dict=readDictionary(frDict);
		String mostCommon=mostCommonLetter(Dict);
		String decrypt=breakForLanguage(message,Dict,mostCommon);
		System.out.print(decrypt);
	}

	
	
	public void breakVigenere(String mName){
		ArrayList<HashSet<String>> allDicts=new ArrayList<HashSet<String>>();
		DirectoryResource dir=new DirectoryResource();
		FileResource frMsg = new FileResource(mName);
		String message =frMsg.asString();
		for(File dict : dir.selectedFiles()){
			FileResource frDict = new FileResource(dict);
			HashSet<String> Dict=readDictionary(frDict);
			allDicts.add(Dict);
			}
		String decrypt = breakForAllLanguages(message,allDicts);
		
				System.out.print(decrypt);
	}
	
	
	public void breakVigenere(String mName,String dName,String mostCommon){
		FileResource frMsg = new FileResource(mName);
		FileResource frDict = new FileResource(dName);
		String message =frMsg.asString();
		HashSet<String> Dict=readDictionary(frDict);
		String decrypt=breakForLanguage(message,Dict,mostCommon);
		System.out.print(decrypt);
	}
	
}

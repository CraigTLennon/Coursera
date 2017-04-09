package week1;

import edu.duke.FileResource;
import week1.OOCaesar;

public class TestCipher {

	public static void main(String[] args) {
		TestCipher t=new TestCipher();
		t.simpleTests();

	}
	
	public int maxIndex(int[] vals){
		int maxDex =0;
		for(int k=0;k<vals.length;k++){
			if(vals[k]>vals[maxDex]){
				maxDex=k;
			}
		}
		return maxDex;
	}
	public int[] countLetters(String encrypted){
		int[] counts =new int[26];
		String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int k=0;k<encrypted.length();k++){
			String letter = encrypted.substring(k, k+1).toUpperCase();
			int index=alphabet.indexOf(letter);
			if(index !=-1){counts[index]+=1;}
			
		}
		return counts;
	}
	
	public String breakCaesarCipher(String input){
		
		int[] freqs=countLetters(input);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex-4;
		dkey=(26-(4-maxDex)) % 26;
		OOCaesar CC = new OOCaesar(dkey);
		String decrypt=CC.decrypt(input);
		return decrypt;
	}
	
	
	public void simpleTests(){
		FileResource Fl= new FileResource();
		String message=Fl.asString();
		OOCaesar CC=new OOCaesar(18);
		String encrypt=CC.encrypt(message);
		System.out.println(encrypt);
		String decrypt=CC.decrypt(encrypt);
		System.out.println(decrypt);
		String broke=breakCaesarCipher(encrypt);
		System.out.println(broke);
	}
	
}

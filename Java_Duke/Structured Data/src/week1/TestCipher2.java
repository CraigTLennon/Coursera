package week1;

import edu.duke.FileResource;
import week1.OOCipher2;


public class TestCipher2 {

	public static void main(String[] args) {
		TestCipher2 t=new TestCipher2();
		t.simpleTests();
	}
	
	public String halfOfString(String s, int start){
		StringBuilder st= new StringBuilder();
		for(int k=start;k<s.length();k=k+2 ){
			st.append(s.charAt(k));
		}
		return st.toString();
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
		String input0=halfOfString(input , 0);
		String input1=halfOfString(input , 1);
		int[] freqs0=countLetters(input0);
		int maxDex0 = maxIndex(freqs0);
		int dkey0 = maxDex0-4;
		int[] freqs1=countLetters(input1);
		int maxDex1 = maxIndex(freqs1);
		int dkey1 = maxDex1-4;
		OOCipher2 CC = new OOCipher2(dkey0,dkey1);
		String decrypt=CC.decryptTwoKeys(input);
		return decrypt;
	}
	
	public void simpleTests(){
		FileResource Fl= new FileResource();
		String message=Fl.asString();
		OOCipher2 CC=new OOCipher2(18,8);
		String encrypt=CC.encryptTwoKeys(message);
		System.out.println(encrypt);
		String decrypt=CC.decryptTwoKeys(encrypt);
		System.out.println(decrypt);
		String broke=breakCaesarCipher(encrypt);
		System.out.println(broke);
		}

}

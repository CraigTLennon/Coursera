package week4;


import week1.OOCaesar;

public class CaesarCracker extends OOCaesar{

	private String mostCommonLetter ="";
	
	public CaesarCracker(int key,String mostCommon){
		super(key);
		mostCommonLetter=mostCommon.toUpperCase();
	}
	
	public int getKey(String encrypted){
		int[] freqs=countLetters(encrypted);
		int maxDex = maxIndex(freqs);
	//	System.out.println(mostCommonLetter);
		int dkey=(26+(maxDex-alphabet.indexOf(mostCommonLetter))) % 26;
	//	System.out.println(maxDex+"  "+this.alphabet.indexOf(mostCommonLetter));
		return dkey;
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
				for(int k=0;k<encrypted.length();k++){
			String letter = encrypted.substring(k, k+1).toUpperCase();
			int index=alphabet.indexOf(letter);
			if(index !=-1){counts[index]+=1;}
			
		}
		return counts;
	}
	
}

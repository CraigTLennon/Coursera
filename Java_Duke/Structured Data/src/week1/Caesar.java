package week1;
import java.lang.Math.*;
public class Caesar {

	public static void main(String[] args) {
		Caesar C = new Caesar();
		C.test();
		C.testDecrypt();
	}
	
	public void testDecrypt(){
//		String half=halfOfString("abababababababab",1);
//		System.out.println(half);
//		half=halfOfString("abababababababab",2);
//		System.out.println(half);
		//String q = encrypt(" hey can you see me",8);
		//System.out.println(q);
		//System.out.println(getKey(q));
		String tk=encryptTwoKeys("Can you see me when I write this sentence: 'wheeee'",3,7);
		decryptTwoKeys(tk);
		
	}
	
	public void decryptTwoKeys(String encrypted){
		String half1=halfOfString(encrypted,1);
		String half0=halfOfString(encrypted,0);
		int key1 = (26-getKey(half1)) %26;
		int key0 = (26-getKey(half0)) % 26;
		System.out.println(key0);
		System.out.println(key1);
		String dec=encryptTwoKeys(encrypted,key0,key1);
		System.out.println(dec);
		
	}
	
	
	public String halfOfString(String s, int start){
		StringBuilder st= new StringBuilder();
		for(int k=start;k<s.length();k=k+2 ){
			st.append(s.charAt(k));
		}
		return st.toString();
	}
	
	public int getKey(String encrypted){
		int[] freqs=countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex-4;
		dkey=(26-(4-maxDex)) % 26;
		return dkey;
	}
	
	
	public String decrypt(String encrypted){
		Caesar CC = new Caesar();
		int[] freqs=countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex-4;
		dkey=(26-(4-maxDex)) % 26;
		return CC.encrypt(encrypted,26-dkey);
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
	
	public String encrypt(String input, int key){
		key = Math.abs(key % 26);
		StringBuilder encrypted=new StringBuilder(input);
		String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shifted =alphabet.substring(key)+alphabet.substring(0,key);
		for(int k =0;k<input.length();k++){
			Character clear=encrypted.charAt(k);
			Character enc=Crypt(clear,alphabet,shifted);
			encrypted.replace(k, k+1, enc.toString());
		}
		return encrypted.toString();
	}
	
	private Character Crypt(Character c, String alphabet, String cypher){
		Character E='!';
		if(Character.isLowerCase(c)){
			Character U =Character.toUpperCase(c);
			Character temp=CryptUpper(U,  alphabet,  cypher);
			E=Character.toLowerCase(temp);
		}else{
			E=CryptUpper(c, alphabet,  cypher);
		}
		return E;
	}
	
	private Character CryptUpper(Character c, String alphabet, String cypher){
		Character E='!';
		if(Character.isAlphabetic(c)){
		int place=alphabet.indexOf(c);
		 E= cypher.charAt(place);
		;}else{
			E = c;
		}
		return E;
	}
	
	public String encryptTwoKeys(String input, int key1,int key2){
		key1 = Math.abs(key1 % 26);
		key2 = Math.abs(key2 % 26);
		StringBuilder encrypted=new StringBuilder(input);
		String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shifted1 =alphabet.substring(key1)+alphabet.substring(0,key1);
		String shifted2 =alphabet.substring(key2)+alphabet.substring(0,key2);
		for(int k =0;k<input.length();k++){
			Character clear=encrypted.charAt(k);
			if((k % 2) ==0){
			Character enc=Crypt(clear,alphabet,shifted1);
			encrypted.replace(k, k+1, enc.toString());}else{
				Character enc=Crypt(clear,alphabet,shifted2);
				encrypted.replace(k, k+1, enc.toString());
			}
			
		}
		return encrypted.toString();
	}		

	
	public void test(){
//		String h=encrypt("abcDEFghIjklmno pQr!@#$stuV789,   \n \n \t funny",0);
//		System.out.println(h);
//		String b=encrypt("abcDEFghIjklmno pQr!@#$stuV789",103);
//		System.out.println(b);
//		String c=encrypt("abcDEFghIjklmno pQr!@#$stuV789",3);
//		System.out.println(c);
//		String a=encrypt("abcDEFghIjklmno pQr!@#$stuV789",-2); 
//		System.out.println(a);
//		String f=encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
//		System.out.println(f);
//		String g=encrypt("FIRST LEGION ATTACK EAST FLANK!", 4);
//		System.out.println(g);
//		String q=encryptTwoKeys("FIRST LEGION ATTACK EAST FLANK!", 23,4);
//		System.out.println(q);
//		String q = encrypt("make sure there are a lot of eeees",8);
//		System.out.println(q);
//		String d = decrypt(q);
//		System.out.println(d);
		String q = encrypt("nooooooooooooooooooooo",8);
		System.out.println(q);
		String d = decrypt(q);
		System.out.println(d);
	}
	

}

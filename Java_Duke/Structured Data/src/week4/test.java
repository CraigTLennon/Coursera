package week4;

import java.util.ArrayList;

import edu.duke.FileResource;

public class test {

	public static void main(String[] args) {
		test T = new test();
		//T.testCC();
		T.testSplit();
	}
	
	
	public void testCC(){
		String fi="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/VigenereTestData/athens.txt";
		FileResource fr=new FileResource(fi);
		String msg=fr.asString();
		CaesarCracker CC = new CaesarCracker(5,"e");
		String enc =CC.encrypt(msg);
		String dec=CC.decrypt(enc);
		//System.out.println(enc);
		//System.out.println(dec);
	}
	
	public void testSplit(){
		String fi="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/VigenereTestData/athens_keyflute.txt";
		String di="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/dictionaries/English";
//		FileResource fr=new FileResource(fi);
//		String msg=fr.asString();
		Cracker C = new Cracker();
		//System.out.println(msg);
		//String slice=C.sliceString(msg, 0, 2);
		//System.out.println(slice);
		//ArrayList<String> arr= C.splitMessage(msg, 2);
		//System.out.println(arr.get(0));
		//String m=C.combineStrings(arr);
		//System.out.println(m);
		//CaesarCracker CC = new CaesarCracker(5,"e");
	//	String enc =CC.encrypt(msg);
		//String dec=CC.decrypt(enc);
//		int[] keys=C.tryKeyLength(enc, 1, 'e');
//		for(int j=0;j<keys.length;j++){
//			System.out.println(keys[j]);
//		}
	//	int[] k={0,0};
	//	String decrypt=C.decrypt(msg, k);
	//	System.out.println(decrypt);
		C.breakVigenere();
		
	}
	
	

}

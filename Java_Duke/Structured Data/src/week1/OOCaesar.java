package week1;

public class OOCaesar {

	private String alphabet;
	private String shiftedAlphabet;
	
	public OOCaesar(int key){
	alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0, key);
	}
	
	public String encrypt(String input){
		StringBuilder sb = new StringBuilder(input);
		for(int i=0;i<sb.length();i++){
			char c = sb.charAt(i);
			int ind=alphabet.indexOf(c);
			if(ind!=-1){
			sb.setCharAt(i, shiftedAlphabet.charAt(ind));}
		}
		return sb.toString();
	}
	
	public String decrypt(String input){
	StringBuilder sb = new StringBuilder(input);
	for(int i=0;i<sb.length();i++){
		char c = sb.charAt(i);
		int ind=shiftedAlphabet.indexOf(c);
		if(ind!=-1){
		sb.setCharAt(i, alphabet.charAt(ind));}
	}
	return sb.toString();
} 

}

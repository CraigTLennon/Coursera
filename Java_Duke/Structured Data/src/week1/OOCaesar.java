package week1;

public class OOCaesar {

	protected String alphabet;
	protected String shiftedAlphabet;
	
	public OOCaesar(int key){
	alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0, key);
	}
	
	public String encrypt(String input){
		StringBuilder sb = new StringBuilder(input);
		for(int i=0;i<sb.length();i++){
			Character c = sb.charAt(i);
			c=Character.toUpperCase(c);
			int ind=alphabet.indexOf(c);
			if(ind!=-1){
			sb.setCharAt(i, shiftedAlphabet.charAt(ind));}
		}
		return sb.toString();
	}
	
	public String decrypt(String input){
	StringBuilder sb = new StringBuilder(input);
	for(int i=0;i<sb.length();i++){
		Character c = sb.charAt(i);
		c=Character.toUpperCase(c);
		int ind=shiftedAlphabet.indexOf(c);
		if(ind!=-1){
		sb.setCharAt(i, alphabet.charAt(ind));}
	}
	return sb.toString();
} 

}

package week1;

public class OOCipher2 {

	private String alpha;
	private String shift1;
	private String shift0;
	private int key0;
	private int key1;
	
	public OOCipher2(int key0,int key1){
		alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shift0=alpha.substring(key0)+alpha.substring(0, key0);
		shift1=alpha.substring(key1)+alpha.substring(0, key1);
		this.key0=key0;
		this.key1=key1;
	}
	
	public String decryptTwoKeys(String encrypted){
		int keyB = (26 -key1) %26;
		int keyA = (26-key0) % 26;
		OOCipher2 OO=new OOCipher2(keyA,keyB);
		String dec=OO.encryptTwoKeys(encrypted);
		System.out.println(dec);
		return dec;
	}
	
	public String encryptTwoKeys(String input){

		StringBuilder encrypted=new StringBuilder(input);
		for(int k =0;k<input.length();k++){
			Character clear=encrypted.charAt(k);
			if((k % 2) ==0){
			Character enc=Crypt(clear,shift0);
			encrypted.replace(k, k+1, enc.toString());}else{
				Character enc=Crypt(clear,shift1);
				encrypted.replace(k, k+1, enc.toString());
			}
			
		}
		return encrypted.toString();
	}
	
	private Character Crypt(Character c, String cypher){
		Character E='!';
		if(Character.isLowerCase(c)){
			Character U =Character.toUpperCase(c);
			Character temp=CryptUpper(U,   cypher);
			E=Character.toLowerCase(temp);
		}else{
			E=CryptUpper(c, cypher);
		}
		return E;
	}
	
	private Character CryptUpper(Character c,String cipher){
		Character E='!';
		if(Character.isAlphabetic(c)){
		int place=alpha.indexOf(c);
		 E= cipher.charAt(place);
		;}else{
			E = c;
		}
		return E;
	}
	
}

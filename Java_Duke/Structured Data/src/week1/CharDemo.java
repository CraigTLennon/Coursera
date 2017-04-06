package week1;

public class CharDemo {

	public static void main(String[] args) {
		CharDemo C = new CharDemo();
		C.digitTest();
		C.ConversionTest();
	}
	
	public void digitTest(){
		String test = "ABCabc0123';#!'";
		for(int k =0; k<test.length();k++){
			char ch=test.charAt(k);
			if(Character.isDigit(ch)){
				System.out.println(ch+" is a  digit");
			}else if(Character.isAlphabetic(ch)){
				System.out.println(ch+" is a  alphabetic");
			}else{
				System.out.println(ch+" is messy" );
			}
			
		}
	}
	public void ConversionTest(){
		String test = "ABCabc0123';#!'";
		for(int k =0; k<test.length();k++){
			char ch=test.charAt(k);
			char uch = Character.toUpperCase(ch);
			char lch = Character.toLowerCase(ch);
			System.out.println(ch+" "+uch+" "+lch );
		}
	}

}

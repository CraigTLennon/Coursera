package week2;

public class Part3 {

	public static void main(String[] args) {
		Part3 P=new Part3();
		P.testing();
		P.testPart();

	}
	
	
	public boolean twoOccurances(String stringa, String stringb){
		int start = stringb.indexOf(stringa);
		if( start ==-1){return false;}
		start=start+stringa.length();
		String stringc = stringb.substring(start);
		int second = stringc.indexOf(stringa);
		if(second ==-1){return false;}
		return true;
	}
	
	public void testing(){
		System.out.println(twoOccurances("A","ASSDFDA"));
		System.out.println(twoOccurances("A","ASSDFD"));
		System.out.println(twoOccurances("A","WWWASSDFDA"));
		System.out.println(twoOccurances("A","DDASSDFDAWEW"));
		System.out.println(twoOccurances("A","DDSSDFDWEW"));
		
	}
	
	public String lastPart(String stringa,String stringb){
		
		int start = stringb.indexOf(stringa);
		if(start==-1){return stringb;}
		
		return stringb.substring(start+stringa.length());
	}
	
	public void testPart(){
		System.out.println(lastPart("an","banana"));
		System.out.println(lastPart("zoo","forest"));
		System.out.println(lastPart("the","the"));		
	}

}

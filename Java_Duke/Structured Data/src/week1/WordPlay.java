package week1;

public class WordPlay {

	public static void main(String[] args) {
		WordPlay W= new WordPlay();
		W.test();

	}
	
	public boolean isVowel(Character ch){
		ch=Character.toLowerCase(ch);
		boolean result=false;
		if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
			result = true;
	}
		return result;
	}
	
	public String replaceVowels(String phrase, Character ch){
		
		StringBuilder deVoweled=new StringBuilder(phrase);
		for(int k =0;k<phrase.length();k++){
			Character letter=deVoweled.charAt(k);
			if(isVowel(letter)){
				deVoweled.replace(k, k+1, ch.toString());
			}				
		}
		return deVoweled.toString();
	}

	
	public String emphasize(String phrase, Character ch){
		
		StringBuilder empha=new StringBuilder(phrase);
		for(int k =0;k<phrase.length();k++){
			Character letter=empha.charAt(k);
			if(letter == ch && (k % 2)==0){
				empha.replace(k, k+1, "*");
			}
			if(letter == ch && (k % 2==1)){
				empha.replace(k, k+1, "+");
			}
		}
		return empha.toString();
	}
	
	public void test(){
		String s1=emphasize("a doggy -- how cute!!!",'o');
		System.out.println(s1);
		String s2=replaceVowels("a doggy -- how cute!!!",'X');
		System.out.println(s2);
	}
	
	
}

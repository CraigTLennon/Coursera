package week2;

import edu.duke.*;

public class Part4 {

	
	public static void main(String[] args) {
		Part4 P=new Part4();
		String url="http://www.dukelearntoprogram.com/course2/data/manylinks.html";
		P.checkAllWords("youtube.com",url);		
	}
	
	public boolean check4Name(String name,String word){
		int start =	word.indexOf(name);
		if(start ==-1){return false;}
		return true;
	}
	
	public String getURL(String containsQuotedURL){
		int startQuote =containsQuotedURL.indexOf("\"");
		assert startQuote >-1;		
		int stopQuote = containsQuotedURL.lastIndexOf("\"");
		assert stopQuote >-1;
		return containsQuotedURL.substring(startQuote+1, stopQuote);
	}
	
	
	
	
	
	
	public void checkAllWords(String name, String url){
		URLResource U = new URLResource(url);
		String lower=name.toLowerCase();
		String upper = name.toUpperCase();
		for(String word : U.words()){
			boolean inURL=false;
				
			if(check4Name(lower,word.toLowerCase())){
				inURL=true;
			}
			if(check4Name(upper,word.toUpperCase())){
				inURL=true;
			}
			if(inURL){System.out.println(getURL(word));
			}
		}
		
	}
	
	
	
	
	public void testWebGet(String url){
		URLResource U = new URLResource(url);
		for(String word : U.words()){
			System.out.println(word);
		}
		
	}
}

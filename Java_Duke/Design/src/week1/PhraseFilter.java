package week1;

public class PhraseFilter implements Filter {

	private String request;
	private String phrase;
	
	
	public PhraseFilter(String request, String phrase) {
		super();
		this.request = request;
		this.phrase = phrase;
	}

	public String getName(){return "PhraseFilter";}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
    	int strLength=phrase.length();
    		int fromEnd=qe.getInfo().length()-strLength;
    		if(request.equals("start") && qe.getInfo().indexOf(phrase)==0){
    			return true;
    		}else if(request.equals("end") && qe.getInfo().lastIndexOf(phrase)==fromEnd){
    			return true;
    		}else if(request.equals("any") && qe.getInfo().indexOf(phrase)>-1){
    			return true;
    		}
		
		return false;
	}

}

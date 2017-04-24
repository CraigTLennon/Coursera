package week1;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

	private ArrayList<Filter> filters;
	
	public MatchAllFilter(){
		filters=new ArrayList<Filter>();
	}
	public void addFilter(Filter f){
		filters.add(f);
	}
	public String getName(){
		String str="";
		for(Filter fil:filters){
			str=str+ " "+ fil.getName();
		}
		return str;}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		for(Filter f :filters){
			if(!f.satisfies(qe)){return false;}
		}
		
		return true;
	}

}

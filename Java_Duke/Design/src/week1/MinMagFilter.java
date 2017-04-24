package week1;
/*
Use as Filter f= new MinMagFilter(3.0);
ArrayList<QuakeEntry> answer = filter(list,f); 
*/

public class MinMagFilter implements Filter {

	private double minMag;
	public MinMagFilter(double min){
		minMag=min;
	}
	
	public String getName(){return "MinMagFilter";}
	@Override
	public boolean satisfies(QuakeEntry qe) {
		return qe.getMagnitude()>=minMag;
	}

}

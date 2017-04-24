package week1;

public class MagnitudeFilter implements Filter {


	private double minimum;
	private double maximum;
	
	
	public MagnitudeFilter(double minimum, double maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}
	public String getName(){return "MagnitudeFilter";}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.getMagnitude() <= maximum && qe.getMagnitude()>= minimum){
			return true;
		}
		return false;
	}

}

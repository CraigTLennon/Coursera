package week1;

public class DepthFilter implements Filter {

	private double minimum;
	private double maximum;
	
	
	public DepthFilter(double minimum, double maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public String getName(){return "DepthFilter";}
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.getDepth() >= minimum && qe.getDepth()<=maximum){return true;}
		return false;
	}

}

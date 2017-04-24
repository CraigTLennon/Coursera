package week1;

public class DistanceFilter implements Filter {

	private Location loc;
	private double maximum;
	
	
	public DistanceFilter(Location loc, double maximum) {
		super();
		this.loc = loc;
		this.maximum = maximum;
	}


	public String getName(){return "DistanceFilter";}
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.distanceTo(loc)<=maximum){
			return true;
		}
		return false;
	}

}

package roadgraph;

import geography.GeographicPoint;

public class Edge {

	private GeographicPoint from,to; 
	private String roadName,roadType;
	private double length;
	private double fromStartLength; // distance from start to the "to" of this edge
	public GeographicPoint getFrom() {
		return from;
	}
	public double totalDistance(){
		return fromStartLength;
	}
	public void setTotalDistance(double d){
		fromStartLength=d;
	}
	public void setFrom(GeographicPoint from) {
		this.from = from;
	}
	public GeographicPoint getTo() {
		return to;
	}
	public void setTo(GeographicPoint to) {
		this.to = to;
	}
	 
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	@Override
	public String toString(){
		return "From: "+this.from+", to: "+this.to+", roadType: "+roadType+", road name: "+roadName+", length: "+length;
	}
	
	public Edge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length) {
		super();
		this.from = from;
		this.to = to;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
		this.fromStartLength=Double.POSITIVE_INFINITY;
	}
	public int hashCode(){
		return this.toString().hashCode();
	}
	public boolean equals(Edge other){
		return this.toString().equals(other.toString());
	}

	
}

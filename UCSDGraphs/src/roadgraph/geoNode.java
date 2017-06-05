package roadgraph;

import java.util.HashSet;

import geography.GeographicPoint;

public class geoNode {

	private GeographicPoint location;
	private double distanceFromStart;
	private  HashSet<Edge> edges;
	
	public geoNode(GeographicPoint loc){
		location = loc;
		distanceFromStart=Double.POSITIVE_INFINITY;
		edges=new HashSet<Edge>();
	}
	
	public void addEdge(Edge e){
		edges.add(e);
	}
	

	
	public void setDistance(double d){
		distanceFromStart=d;
	}
	
	public double getdistance(){
		return distanceFromStart;
	}
	
	public GeographicPoint loc(){
		return location;
	}
	
	@Override
	public String toString(){
		return this.location.toString();
	}
	
	@Override
	public boolean equals(Object oth){
		geoNode other =(geoNode) oth;
		return other.loc().toString().equals(this.location.toString());
	}

	@Override
	public int hashCode(){
		return location.toString().hashCode();
	}
}

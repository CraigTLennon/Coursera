package roadgraph;

import java.util.Comparator;

public class astarCompNode implements Comparator<MapNode> {

	private MapNode goal;
	
	public astarCompNode(MapNode g){
		this.goal=g;
	}
	
	public int compare(MapNode g1,MapNode g2){
		return Double.compare(g1.getAStarDistance(goal), g2.getAStarDistance(goal));
	}
}

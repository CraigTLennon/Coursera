package roadgraph;

import java.util.Comparator;

public class compNode implements Comparator<MapNode> {

	public int compare(MapNode g1,MapNode g2){
		return Double.compare(g1.getDistance(), g2.getDistance());
	}
}

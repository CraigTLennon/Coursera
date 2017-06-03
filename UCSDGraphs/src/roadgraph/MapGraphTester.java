package roadgraph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;

public class MapGraphTester {

	private MapGraph MG;
	private GeographicPoint start,first,second,secondAlt,goal;
	private ArrayList<GeographicPoint> shortest;
	@Before
	public void setUp() throws Exception {
		MG = new MapGraph();
		 start = new GeographicPoint(0.0,0.0);
		 first = new GeographicPoint(1.0,0.0);
		 second = new GeographicPoint(1.0,1.0);
		 secondAlt = new GeographicPoint(1.0,-1.5);
		 goal = new GeographicPoint(2.0,2.0);
		MG.addVertex(start);
		MG.addVertex(first);
		MG.addVertex(second);
		MG.addVertex(secondAlt);
		MG.addVertex(goal);
		
		MG.addEdge(start, first, "Road one", "highway", 1.0);
		MG.addEdge(first, second, "Road two", "highway", 1.0);
		MG.addEdge(first, secondAlt, "Road three", "highway", 2.0);
		MG.addEdge(second,goal, "Road four", "highway", 1.0);
		MG.addEdge(secondAlt,goal, "Road five", "highway", 2.1);
//		MG.addEdge(start, goal, "long road", "gravel", 100.0);
		
		shortest =new ArrayList<GeographicPoint>();
		
		shortest.add(start);
		shortest.add(first);
		shortest.add(second);
		shortest.add(goal);
	}

	@Test
	public void testSize() {
		assertEquals( MG.getNumVertices(), 5 );
		assertEquals( MG.getNumEdges(), 5 );
		MG.print();
	}

	@Test
	public void testbfs() {

		assertEquals(4, shortest.size() );
		ArrayList<GeographicPoint> bf = (ArrayList<GeographicPoint>) MG.bfs(start, goal);
		assertEquals( bf, shortest );
		
	}
	
}

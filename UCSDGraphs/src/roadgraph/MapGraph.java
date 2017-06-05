/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */


package roadgraph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import geography.GeographicPoint;
import util.GraphLoader;
public class MapGraph {
	// Maintain both nodes and edges as you will need to
		// be able to look up nodes by lat/lon or by roads
		// that contain those nodes.
		private HashMap<GeographicPoint,MapNode> pointNodeMap;
		private HashSet<MapEdge> edges;

		
		/** 
		 * Create a new empty MapGraph 
		 */
		public MapGraph()
		{
			pointNodeMap = new HashMap<GeographicPoint,MapNode>();
			edges = new HashSet<MapEdge>();
		}
		
		/**
		 * Get the number of vertices (road intersections) in the graph
		 * @return The number of vertices in the graph.
		 */
		public int getNumVertices()
		{
			return pointNodeMap.values().size();
		}
		
		/**
		 * Return the intersections, which are the vertices in this graph.
		 * @return The vertices in this graph as GeographicPoints
		 */
		public Set<GeographicPoint> getVertices()
		{
			return pointNodeMap.keySet();
		}
		public void print(){
			for(MapEdge e : edges){
				System.out.println(e);
			}
		}
		/**
		 * Get the number of road segments in the graph
		 * @return The number of edges in the graph.
		 */
		public int getNumEdges()
		{
			return edges.size();
		}

		
		
		/** Add a node corresponding to an intersection at a Geographic Point
		 * If the location is already in the graph or null, this method does 
		 * not change the graph.
		 * @param location  The location of the intersection
		 * @return true if a node was added, false if it was not (the node
		 * was already in the graph, or the parameter is null).
		 */
		public boolean addVertex(GeographicPoint location)
		{
			if (location == null) {
				return false;
			}
			MapNode n = pointNodeMap.get(location);
			if (n == null) {
				n = new MapNode(location);
				pointNodeMap.put(location, n);
				return true;
			}
			else {
				return false;
			}
		}
		
		/**
		 * Adds a directed edge to the graph from pt1 to pt2.  
		 * Precondition: Both GeographicPoints have already been added to the graph
		 * @param from The starting point of the edge
		 * @param to The ending point of the edge
		 * @param roadName The name of the road
		 * @param roadType The type of the road
		 * @param length The length of the road, in km
		 * @throws IllegalArgumentException If the points have not already been
		 *   added as nodes to the graph, if any of the arguments is null,
		 *   or if the length is less than 0.
		 */
		public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
				String roadType, double length) throws IllegalArgumentException {

			MapNode n1 = pointNodeMap.get(from);
			MapNode n2 = pointNodeMap.get(to);

			// check nodes are valid
			if (n1 == null)
				throw new NullPointerException("addEdge: pt1:"+from+"is not in graph");
			if (n2 == null)
				throw new NullPointerException("addEdge: pt2:"+to+"is not in graph");

			MapEdge edge = new MapEdge(roadName, roadType, n1, n2, length);
			edges.add(edge);
			n1.addEdge(edge);
			
		}
			
		/** 
		 * Get a set of neighbor nodes from a mapNode
		 * @param node  The node to get the neighbors from
		 * @return A set containing the MapNode objects that are the neighbors 
		 * 	of node
		 */
		private Set<MapNode> getNeighbors(MapNode node) {
			return node.getNeighbors();
		}
		
		/** Find the path from start to goal using breadth first search
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @return The list of intersections that form the shortest (unweighted)
		 *   path from start to goal (including both start and goal).
		 */
		public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
			// Dummy variable for calling the search algorithms
	        Consumer<GeographicPoint> temp = (x) -> {};
	        return bfs(start, goal, temp);
		}
		
		/** Find the path from start to goal using breadth first search
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
		 * @return The list of intersections that form the shortest (unweighted)
		 *   path from start to goal (including both start and goal).
		 */
		public List<GeographicPoint> bfs(GeographicPoint start, 
				 					     GeographicPoint goal, 
				 					     Consumer<GeographicPoint> nodeSearched)
		{
			/* Note that this method is a little long and we might think
			 * about refactoring it to break it into shorter methods as we 
			 * did in the Maze search code in week 2 */
			
			// Setup - check validity of inputs
			if (start == null || goal == null)
				throw new NullPointerException("Cannot find route from or to null node");
			MapNode startNode = pointNodeMap.get(start);
			MapNode endNode = pointNodeMap.get(goal);
			if (startNode == null) {
				System.err.println("Start node " + start + " does not exist");
				return null;
			}
			if (endNode == null) {
				System.err.println("End node " + goal + " does not exist");
				return null;
			}

			// setup to begin BFS
			HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
			Queue<MapNode> toExplore = new LinkedList<MapNode>();
			HashSet<MapNode> visited = new HashSet<MapNode>();
			toExplore.add(startNode);
			MapNode next = null;

			while (!toExplore.isEmpty()) {
				next = toExplore.remove();
				
				 // hook for visualization
				nodeSearched.accept(next.getLocation());
				
				if (next.equals(endNode)) break;
				Set<MapNode> neighbors = getNeighbors(next);
				for (MapNode neighbor : neighbors) {
					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						parentMap.put(neighbor, next);
						toExplore.add(neighbor);
					}
				}
			}
			if (!next.equals(endNode)) {
				System.out.println("No path found from " +start+ " to " + goal);
				return null;
			}
			// Reconstruct the parent path
			List<GeographicPoint> path =
					reconstructPath(parentMap, startNode, endNode);

			return path;
		
		}
		


		/** Reconstruct a path from start to goal using the parentMap
		 *
		 * @param parentMap the HashNode map of children and their parents
		 * @param start The starting location
		 * @param goal The goal location
		 * @return The list of intersections that form the shortest path from
		 *   start to goal (including both start and goal).
		 */
		private List<GeographicPoint>
		reconstructPath(HashMap<MapNode,MapNode> parentMap,
						MapNode start, MapNode goal)
		{
			LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
			MapNode current = goal;

			while (!current.equals(start)) {
				path.addFirst(current.getLocation());
				current = parentMap.get(current);
			}

			// add start
			path.addFirst(start.getLocation());
			return path;
		}


		/** Find the path from start to goal using Dijkstra's algorithm
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @return The list of intersections that form the shortest path from 
		 *   start to goal (including both start and goal).
		 */
		public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
			// Dummy variable for calling the search algorithms
			// You do not need to change this method.
	        Consumer<GeographicPoint> temp = (x) -> {};
	        return dijkstra(start, goal, temp);
		}
		
		/** Find the path from start to goal using Dijkstra's algorithm
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
		 * @return The list of intersections that form the shortest path from 
		 *   start to goal (including both start and goal).
		 */
		public List<GeographicPoint> dijkstra(GeographicPoint start, 
											  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
		{
			MapNode startNode = pointNodeMap.get(start);
			startNode.setDistance(0.0);
			MapNode goalNode = pointNodeMap.get(goal);
			
			HashSet<MapNode> visited = new HashSet<MapNode>();
			HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>(); 
			PriorityQueue<MapNode> pq = new PriorityQueue<MapNode>(1,new compNode());
			pq.add(startNode);
			
			while(!pq.isEmpty()){
				MapNode next = pq.remove();
				if(!visited.contains(next)){
					visited.add(next);
					if(next.equals(goalNode)) return reconstructPath(parentMap,startNode,goalNode);
					
					Set<MapNode> neighbors = next.getNeighbors().stream().filter(x->!visited.contains(x)).collect(Collectors.toSet());
					for(MapNode n : neighbors){
						double distance = next.getDistance()+n.distanceFrom(next);
						if(distance<n.getDistance()){
							n.setDistance(distance);
							parentMap.put(n, next);
							pq.add(n);
//							System.out.println("added "+n.toString()+" cost "+n.getDistance());
							
						}
					}
				}
			
			

			// Hook for visualization.  See writeup.
			 nodeSearched.accept(next.getLocation());
			}
			return null;
		}

		/** Find the path from start to goal using A-Star search
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @return The list of intersections that form the shortest path from 
		 *   start to goal (including both start and goal).
		 */
		public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
			// Dummy variable for calling the search algorithms
	        Consumer<GeographicPoint> temp = (x) -> {};
	        return aStarSearch(start, goal, temp);
		}
		
		/** Find the path from start to goal using A-Star search
		 * 
		 * @param start The starting location
		 * @param goal The goal location
		 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
		 * @return The list of intersections that form the shortest path from 
		 *   start to goal (including both start and goal).
		 */
		public List<GeographicPoint> aStarSearch(GeographicPoint start, 
												 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
		{
			// TODO: Implement this method in WEEK 3
			
			// Hook for visualization.  See writeup.
			//nodeSearched.accept(next.getLocation());
			
			return null;
		}

		
		
		public static void main(String[] args)
		{
//			System.out.print("Making a new map...");
//			MapGraph2 theMap = new MapGraph2();
//			System.out.print("DONE. \nLoading the map...");
//			GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
//			System.out.println("DONE.");
			
			
			MapGraph simpleTestMap = new MapGraph();
			GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
			
			GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
			GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);

			System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
			List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
//			List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
			System.out.println("findished");
			for(GeographicPoint g : testroute){
				System.out.println(g);
			}
		}		
			
			// You can use this method for testing.  
			
			/* Use this code in Week 3 End of Week Quiz
			MapGraph theMap = new MapGraph();
			System.out.print("DONE. \nLoading the map...");
			GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
			System.out.println("DONE.");

			GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
			GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
			
			
			List<GeographicPoint> route = theMap.dijkstra(start,end);
			List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

			*/
			
		}
		


//package roadgraph;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.Set;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
//import geography.GeographicPoint;
//import util.GraphLoader;
//
///**
// * @author UCSD MOOC development team and YOU
// * 
// * A class which represents a graph of geographic locations
// * Nodes in the graph are intersections between 
// *
// */
//public class MapGraph {
//
//	
//	
//	private HashMap<GeographicPoint,ArrayList<Edge>> adjListsMap;
//	private int numVert;
//	private int numEdge;
//	private HashMap<geoNode,ArrayList<Edge>> geoLists;
//	
//	/** 
//	 * Create a new empty MapGraph 
//	 */
//	public MapGraph()
//	{
//		adjListsMap = new HashMap<GeographicPoint,ArrayList<Edge>>();
//		numVert=0;
//		numEdge=0;
//	}
//	
//	
//	
//	
//	/**
//	 * Get the number of vertices (road intersections) in the graph
//	 * @return The number of vertices in the graph.
//	 */
//	public int getNumVertices()
//	{
//		
//		return numVert;
//	}
//	
//	/**
//	 * Return the intersections, which are the vertices in this graph.
//	 * @return The vertices in this graph as GeographicPoints
//	 */
//	public Set<GeographicPoint> getVertices()
//	{
//		
//		return adjListsMap.keySet();
//	}
//	
//	/**
//	 * Get the number of road segments in the graph
//	 * @return The number of edges in the graph.
//	 */
//	public int getNumEdges()
//	{
//		
//		return numEdge;
//	}
//	
//	
//	
//	/** Add a node corresponding to an intersection at a Geographic Point
//	 * If the location is already in the graph or null, this method does 
//	 * not change the graph.
//	 * @param location  The location of the intersection
//	 * @return true if a node was added, false if it was not (the node
//	 * was already in the graph, or the parameter is null).
//	 */
//	public boolean addVertex(GeographicPoint location)
//	{
//		if(adjListsMap.containsKey(location)){return false;}
//		adjListsMap.put(location, new ArrayList<Edge>());
//		numVert+=1;
//		return true;
//	}
//	
//	public void print(){
//		for(GeographicPoint g:adjListsMap.keySet()){
//			System.out.println("Vertex" +g);
//			for(Edge e : adjListsMap.get(g)){
//				System.out.println(e);
//			}
//		}
//	}
//	
//	/**
//	 * Adds a directed edge to the graph from pt1 to pt2.  
//	 * Precondition: Both GeographicPoints have already been added to the graph
//	 * @param from The starting point of the edge
//	 * @param to The ending point of the edge
//	 * @param roadName The name of the road
//	 * @param roadType The type of the road
//	 * @param length The length of the road, in km
//	 * @throws IllegalArgumentException If the points have not already been
//	 *   added as nodes to the graph, if any of the arguments is null,
//	 *   or if the length is less than 0.
//	 */
//	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
//			String roadType, double length) throws IllegalArgumentException {
//
//		if(!adjListsMap.containsKey(from) || !adjListsMap.containsKey(to)) throw  new IllegalArgumentException("Both endpoints must be in the graph: "+ from+" "+to);
//		if(roadType.equals(null)) throw  new IllegalArgumentException("road type must have value");
//		if(roadName.equals(null)) throw  new IllegalArgumentException("road Name must have value");
//		if(length<0.0) throw  new IllegalArgumentException("distance cannot be negative");
//		Edge e = new Edge(from,to,roadName,roadType,length);
//		adjListsMap.get(from).add(e);
//		numEdge +=1;
//	}
//	
//
//	
//	
//	/** Find the path from start to goal using breadth first search
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @return The list of intersections that form the shortest (unweighted)
//	 *   path from start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
//		// Dummy variable for calling the search algorithms
//        Consumer<GeographicPoint> temp = (x) -> {};
//        return bfs(start, goal, temp);
//	}
//	
//	/** Find the path from start to goal using breadth first search
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
//	 * @return The list of intersections that form the shortest (unweighted)
//	 *   path from start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> getNeighbors(GeographicPoint point){
//		List<GeographicPoint> neigh = adjListsMap.get(point).stream().map(x->x.getTo()).collect(Collectors.toList()) ;
//		
//		return neigh;
//	}
//
//	public List<Edge> getNeighbors(Edge current){
//		GeographicPoint to = current.getTo();
//		List<Edge> neigh = adjListsMap.get(to);
//		return neigh;
//	}
//	
//	public List<GeographicPoint> bfs(GeographicPoint start, 
//			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
//	{
//		if(start == null || goal ==null) return null;
//		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
//		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>(); 
//		Queue<GeographicPoint> toExplore = new LinkedList<GeographicPoint>();
//		toExplore.add(start);
//		visited.add(start);
//		boolean found = false;
//		
//		while(!toExplore.isEmpty()){
//			GeographicPoint current = toExplore.remove();
//			if (current.toString().equals(goal.toString())) {
//				found = true;
//				break;
//				}
//		List<GeographicPoint> neighbors = getNeighbors(current);
//		List<GeographicPoint> newNeighbors = neighbors.stream().filter(x->!visited.contains(x)).collect(Collectors.toList());
//		System.out.print("");
//		for(GeographicPoint g: newNeighbors){
//			visited.add(g);
////			System.out.println(g);
//			parentMap.put(g, current);
//			toExplore.add(g);
//		}
//		
//		// Hook for visualization.  See writeup.
//		//nodeSearched.accept(next.getLocation());
//		nodeSearched.accept(current);
//		}
//		
//		if(!found)return new ArrayList<GeographicPoint>();
//		
//		return getPath(parentMap, goal, start);
//	}
//	
//	
//	private List<GeographicPoint> getPath(HashMap<GeographicPoint, GeographicPoint> parentMap,GeographicPoint goal,GeographicPoint start){
//		GeographicPoint current = goal;
//		System.out.println("current "+current+" start "+start);
//		if(goal==null || start==null || current==null)System.out.print("null error here");
//		ArrayList<GeographicPoint> path = new ArrayList<GeographicPoint>(); 
//		while(!(current.toString().equals(start.toString()) )){
//			path.add(0,current);
//			current=parentMap.get(current);
////			System.out.println("current "+current+" start "+start);
//		}
//		path.add(0,start);
//		
//		return path;
//	}
//	
//	
//
//	/** Find the path from start to goal using Dijkstra's algorithm
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @return The list of intersections that form the shortest path from 
//	 *   start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
//		// Dummy variable for calling the search algorithms
//		// You do not need to change this method.
//        Consumer<GeographicPoint> temp = (x) -> {};
//        return dijkstra(start, goal, temp);
//	}
//	
//	/** Find the path from start to goal using Dijkstra's algorithm
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
//	 * @return The list of intersections that form the shortest path from 
//	 *   start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> dijkstra(GeographicPoint start, 
//			  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
//{
//		
//		PriorityQueue<Edge> pq= new PriorityQueue<Edge>(  );
//		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>(); 
//		Edge startEdge=new Edge(start,start,"none","none",0.0);
//		startEdge.setTotalDistance(0.0);
//		pq.add(startEdge);
//		HashMap<GeographicPoint,GeographicPoint> parentMap = new HashMap<GeographicPoint,GeographicPoint>(); 
//		while(!pq.isEmpty()){
//			Edge current=pq.remove();
//			//System.out.println("visited contains surrnet"+visited.contains(current));
//			if(!visited.contains(current.getTo())){
//				visited.add(current.getTo());
////				System.out.println("added "+current.toString()+" with code "+current.hashCode());
////				System.out.println("visitedsize is "+visited.size()+" and pq size is "+pq.size());
//			if(current.getTo().equals(goal)){ 
//				
//				System.out.print("done!!");
//				return getPath(parentMap,goal,start);} 
//			List<Edge> neighbors=getNeighbors(current);
//			List<Edge> newNodeNeighbors=neighbors.stream().filter(x->!visited.contains(x.getTo())).collect(Collectors.toList());
//			for(Edge g: newNodeNeighbors){
//			double oldDistToG=g.totalDistance();
////			System.out.println("Considering location " +g.toString()+" cost "+ g.totalDistance()+" and hash "+g.hashCode());
//			double newDistToG=current.totalDistance()+g.getLength();
//			System.out.println("old distance "+oldDistToG+" new distance "+newDistToG);
//			if(oldDistToG>newDistToG ){
//				g.setTotalDistance(newDistToG);}
//
//				if(!parentMap.containsKey(g.getTo())){
//					parentMap.put(g.getTo(),current.getTo());
//				}
//				//This is where I gave up and looked into refactoring to get a better encapsulating class for geographic points.
//				pq.add(g);
//				System.out.println("added " +g.toString()+" cost "+ g.totalDistance());
//			}
//		}
//		nodeSearched.accept(current.getTo());
//	}
//		return null;
//}
//	
//
//	/** Find the path from start to goal using A-Star search
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @return The list of intersections that form the shortest path from 
//	 *   start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
//		// Dummy variable for calling the search algorithms
//        Consumer<GeographicPoint> temp = (x) -> {};
//        return aStarSearch(start, goal, temp);
//	}
//	
//	/** Find the path from start to goal using A-Star search
//	 * 
//	 * @param start The starting location
//	 * @param goal The goal location
//	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
//	 * @return The list of intersections that form the shortest path from 
//	 *   start to goal (including both start and goal).
//	 */
//	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
//											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
//	{
//		// TODO: Implement this method in WEEK 4
//		
//		// Hook for visualization.  See writeup.
//		//nodeSearched.accept(next.getLocation());
//		
//		return null;
//	}
//
//	
//	
//	public static void main(String[] args)
//	{
//		System.out.print("Making a new map...");
//		MapGraph firstMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
//		System.out.println("DONE.");
//		
//		// You can use this method for testing.  
//		
//		
//		/* Here are some test cases you should try before you attempt 
//		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
//		 * programming assignment.
//		 */
//		
//		MapGraph simpleTestMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
//		
//		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
//		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
//
//		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
//		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
////		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
//		System.out.println("findished");
//		for(GeographicPoint g : testroute){
//			System.out.println(g);
//		}
//		
//		/*
//		MapGraph testMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
//		
//		// A very simple test using real data
//		testStart = new GeographicPoint(32.869423, -117.220917);
//		testEnd = new GeographicPoint(32.869255, -117.216927);
//		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
//		
//		
//		// A slightly more complex test using real data
//		testStart = new GeographicPoint(32.8674388, -117.2190213);
//		testEnd = new GeographicPoint(32.8697828, -117.2244506);
//		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
//		*/
//		
//		
//		/* Use this code in Week 3 End of Week Quiz */
//		/*MapGraph theMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
//		System.out.println("DONE.");
//
//		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
//		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
//		
//		
//		List<GeographicPoint> route = theMap.dijkstra(start,end);
//		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
//
//		*/
//		
//	}
//	
//}

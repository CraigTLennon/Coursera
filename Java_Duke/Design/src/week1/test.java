package week1;

import edu.duke.FileResource;

public class test {

	public static void main(String[] args) {
		test T = new test();
		T.tester();
	}
	
	
	public void tester(){
//		EarthQuakeClient E = new EarthQuakeClient();
		//E.createCSV();
		//E.bigQuakes();
//		E.closeToMe();
//		ClosestQuakes C = new ClosestQuakes();
	//	E.quakesOfDepth();
		//C.findClosestQuakes();
//		E.quakesByPhrase();
//		LargestQuakes L = new LargestQuakes();
//		L.findLargestQuakes();
		EarthQuakeClient2 E2 = new EarthQuakeClient2();
		E2.quakesWithFilter();
	}

}

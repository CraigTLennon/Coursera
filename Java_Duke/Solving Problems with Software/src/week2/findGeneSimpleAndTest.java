/**
 * 
 */
package week2;

/**
 * @author cLennon
 *
 */
public class findGeneSimpleAndTest {


	public static void main(String[] args) {
		findGeneSimpleAndTest G = new findGeneSimpleAndTest();
		String dna="ADDDFDDESASDATGDFDEFEDDFTRHTFDFDEEFFTAAQQQQQQQQ";
		String xna="rewrewrewrewrwerwew";
		System.out.println(G.findGeneSimple(dna));
		System.out.println(G.findGeneSimple(xna));
	}

	public String findGeneSimple(String dna){
		
		int start=dna.indexOf("ATG");
		assert start >-1 : ""; //Assertions do not seem to stop the program, but I guess they throw an error
		if(start==-1){return "";}
		int stop =dna.indexOf("TAA", start+2);
		assert stop >-1 : "";
		if(stop==-1){return "";}
		String gene= dna.substring(start, stop+3);
		
		return gene;
	}
	
	
	
	
}

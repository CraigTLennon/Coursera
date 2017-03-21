package week2;

public class Part2 {

	public static void main(String[] args) {
		Part1 P= new Part1();
		P.testSimpleGene();
	}

	
	public String findGeneSimple(String dna, String startC,String stopC){
		System.out.println(dna);
		int start=dna.indexOf(startC);
		assert start >-1 : ""; //Assertions do not seem to stop the program, but I guess they throw an error
		if(start==-1){return "";}
		int stop =dna.indexOf(stopC, start+2);
		assert stop >-1 : "";
		if(stop==-1){return "";}
		String gene= dna.substring(start, stop+3);	
		if(gene.length() % 3 ==0){return gene;}else{System.out.print(gene.length() % 3 );
		
		return "";}
	}
	
	public void testSimpleGene(){
		String t1="TERERERDSDFSDFDFSTAA";
		String t2="TERERERDSDFSDFDFS";
		String t3="ATGTERERErRDSDFSDFDFSTAA";
		String t12="TERRERDSDFSDFDFSTAA";
		String t22="TERRERDSDFSDFDFS";
		String t32="ATGERERERrDSDFSDFDFSTAA";		
		System.out.println(findGeneSimple(t1,"ATG","TAA"));
		System.out.println(findGeneSimple(t2,"ATG","TAA"));
		System.out.println(findGeneSimple(t3,"ATG","TAA"));
		System.out.println(findGeneSimple(t12,"ATG","TAA"));
		System.out.println(findGeneSimple(t22,"ATG","TAA"));
		System.out.println(findGeneSimple(t32,"ATG","TAA"));
	}
}

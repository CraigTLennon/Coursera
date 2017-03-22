package week2;

public class AllGenes {

	public static void main(String[] args) {
		AllGenes P = new AllGenes();
				P.testOn();

	}
	
	public void printAG(String dna){
		int start=0;
		
		while(true){
			String gene=findNextGene(dna,start);
			if(gene.isEmpty()){break;}
			System.out.println(gene);
			start=dna.indexOf(gene,start)+gene.length();
		}
	}
	
	public String findNextGene(String dna,int startIndex){
		int start = dna.indexOf("ATG",startIndex);
		if(start ==-1){return "";}
		int taaIndex=findStopCodon(dna,start,"TAA");
		int tagIndex=findStopCodon(dna,start,"TAG");
		int tgaIndex=findStopCodon(dna,start,"TGA");
		int minIndex=Math.min(tgaIndex, Math.min(taaIndex,tagIndex));
		if(minIndex==dna.length()){return"";}
		
		
		return dna.substring(start,minIndex+3);
	}

	public int findStopCodon(String dna,int startIndex, String stopCodon){
		int currIndex = dna.indexOf(stopCodon,startIndex+3);
		while(currIndex!=-1){
			if((currIndex-startIndex) %3==0){return currIndex;}
			currIndex = dna.indexOf(stopCodon,currIndex+1);
			
		}
		return dna.length();
	}
	
	
	
	public void testOn(){
		String G="ACAAGATGCCATTGTCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCC"
				+ "CCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGC"
				+ "CTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCCAGGCCAGTGCCGGGCCCCTCATAGGAGAGG"
				+ "AAGCTCGGGAGGTGGCCAGGCGGCAGGAAGGCGCACCCCCCCAGCAATCCGCGCGCCGGGACAGAATGCC"
				+ "CTGCAGGAACTTCTTCTGGAAGACCTTCTCCTCCTGCAAATAAAACCTCACCCATGAATGCTCACGCAAG"
				+ "TTTAATTACAGACCTGAA";
		String F=G+G+G;
		printAG(F);
		
	}
	
	
	

}

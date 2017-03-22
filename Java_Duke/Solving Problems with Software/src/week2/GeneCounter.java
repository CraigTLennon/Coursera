package week2;

public class GeneCounter extends AllGenes {
	
	public static void main(String[] args) {
		GeneCounter P = new GeneCounter();
		P.testOn();
	}
	
	public void printCount(String dna){
		int start=0;
		int count=0;
		while(true){
			String gene=findNextGene(dna,start);
			if(gene.isEmpty()){
				System.out.println(count);
				break;}
			start=dna.indexOf(gene,start)+gene.length();
			count=1+count;
		}
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
		printCount(F);
	}

}

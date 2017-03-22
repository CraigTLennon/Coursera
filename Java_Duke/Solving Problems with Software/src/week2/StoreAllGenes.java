package week2;
import edu.duke.*;
public class StoreAllGenes {

	public static void main(String[] args) {
		StoreAllGenes G = new StoreAllGenes();
		G.testOn();

	}

	
	public StorageResource storeAG(String dna){
		int start=0;
		StorageResource store=new StorageResource();
		while(true){
			String gene=findNextGene(dna,start);
			if(gene.isEmpty()){break;}
			store.add(gene);;
			start=dna.indexOf(gene,start)+gene.length();
		}
		return store;
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

	public void processGenes(StorageResource sr){
		String longest="";
		int nines=0;
		int cg=0;
		for(String s : sr.data()){
			if(s.length()>longest.length()){longest=s;}
			if(s.length()>9){
				nines=nines+1;
				System.out.println(s);
			}
			if(cgRatio(s)>9.0){
				cg=cg+1;
				System.out.println(s);
			}}			
			System.out.println(nines);
			System.out.println(cg);
			System.out.println(longest);
		
		
	}
	public int findStopCodon(String dna,int startIndex, String stopCodon){
		int currIndex = dna.indexOf(stopCodon,startIndex+3);
		while(currIndex!=-1){
			if((currIndex-startIndex) %3==0){return currIndex;}
			currIndex = dna.indexOf(stopCodon,currIndex+1);
			
		}
		return dna.length();
	}
	
	public double cgRatio(String dna){
		double c=0.0;
		double g=0.0;
		int i =0;
		while( i <dna.length()){
			
			if(dna.charAt(i)=='C'){c=c+1;}
			if(dna.charAt(i)=='G'){g=g+1;}
			i=i+1;
		}
		if(g>0){return c/g;}else{return -1.0;}
		
		
	}
	
	public void testOn(){
		String G="ACAAGATGCCATTGTCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCC"
				+ "CCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGC"
				+ "CTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCCAGGCCAGTGCCGGGCCCCTCATAGGAGAGG"
				+ "AAGCTCGGGAGGTGGCCAGGCGGCAGGAAGGCGCACCCCCCCAGCAATCCGCGCGCCGGGACAGAATGCC"
				+ "CTGCAGGAACTTCTTCTGGAAGACCTTCTCCTCCTGCAAATAAAACCTCACCCATGAATGCTCACGCAAG"
				+ "TTTAATTACAGACCTGAA";
		String F=G+G+G;
		StorageResource store =storeAG(F);
		System.out.println(store.size());
		for( String s :store.data()){
			System.out.println(s);
		}
		System.out.println(cgRatio(F));
		System.out.println(cgRatio(G));
		processGenes(store);
	}
	
	
}

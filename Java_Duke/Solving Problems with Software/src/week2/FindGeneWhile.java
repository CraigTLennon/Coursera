package week2;

public class FindGeneWhile {

	public static void main(String[] args) {
		FindGeneWhile F = new FindGeneWhile();
		F.testFind();
	}

	public String findGene(String dna){
		int start = dna.indexOf("ATG");
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
			
	
	
	public void testFind(){
		System.out.println(findGene("ATGEREREFDFDFEATGEREFDETAAEREFTAA"));
		System.out.println(findGene("ATGEREREFDFDEATGEREFDETAAEREFTAA"));
		System.out.println(findGene("ATGEREREFDFDATGEREFDETAAEREFTAA"));
		System.out.println(findGene("ATGEREREFDFDFEATGEREFDETAAEREFTGA"));
		System.out.println(findGene("ATGEREREFDFDEATGEREFDETAAEREFTAG"));
		System.out.println(findGene("ATGEREREFDFDATGEREFDETAAEREFTAF"));
	}
		
		
	}

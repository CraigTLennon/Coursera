package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class CodonCount {

	public static void main(String[] args) {
		CodonCount C=new CodonCount();
		C.test();

	}

	private HashMap<String,Integer> counter;
	
		
	public CodonCount(){
		counter = new HashMap<String,Integer>();
	}
	
	public void buildCodonMap(int start,String dna){
		counter.clear();
		for(int k=start;k< dna.length()-2;k+=3){
			String codon=dna.substring(k, k+3);
			if(counter.containsKey(codon)){
				int current =counter.get(codon);
				counter.put(codon, current+1);
			}else{
				counter.put(codon, 1);
			}
		}
	}
	
	public String getMostCommonCodon(){
		int largestNumber=0;
		String freqCodon="";
		for(String codon : counter.keySet()){
			int value = counter.get(codon);
			if(value>largestNumber){
				largestNumber=value;
				freqCodon=codon;
			}
		}
		return freqCodon;
	}
	
	public void printCodonCounts(int start, int end){
		for(String codon : counter.keySet()){
			int value = counter.get(codon);
			if(value>=start & value<=end){
				System.out.println(""+value+" occurances of "+codon);
			}
		}
	}
	
	public void test(){
		FileResource fl=new FileResource();
		String dna=fl.asString();
		for(int k=0;k<3;k++){
			buildCodonMap(k,dna);
			String common =getMostCommonCodon();
			System.out.println(common);
			printCodonCounts(1,5);
		}
	}
	
	
}

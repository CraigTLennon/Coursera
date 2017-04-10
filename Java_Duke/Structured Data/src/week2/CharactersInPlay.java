package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {

	public static void main(String[] args) {
		CharactersInPlay C= new CharactersInPlay();
		C.test();
	}

	private ArrayList<String> names;
	private ArrayList<Integer> nameFreq;
	
	public CharactersInPlay(){
		names=new ArrayList<String>();
		nameFreq=new ArrayList<Integer>();
	}
	

	public void update(String person){
		if(! names.contains(person)){
			names.add(person);
			nameFreq.add(1);
		}else{
			int index=names.indexOf(person);
			int value= nameFreq.get(index);
			nameFreq.set(index,value+1);
		}
		
	}
	
	public void findAllCharacters(){
		names.clear();
		nameFreq.clear();
		FileResource fl=new FileResource();
		for(String line : fl.lines()){
			if(line.contains(".")){
				int period=line.indexOf(".");
				String speaker=line.substring(0, period);
				update(speaker);
			}
		}
	}
	
	public void charactersWithNumParts(int num1,int num2){
		for(int k=0;k<names.size();k++){
			int numLines=nameFreq.get(k);
			if(numLines>=num1 & numLines<=num2){
				System.out.println(nameFreq.get(k)+" lines by "+names.get(k));
			}
		}
	}
	
	public void test(){
		findAllCharacters();
		for(int k=0;k<names.size();k++){
			System.out.println(nameFreq.get(k)+" lines by "+names.get(k));
		}
		charactersWithNumParts(10,1000);
	}
	
	
}

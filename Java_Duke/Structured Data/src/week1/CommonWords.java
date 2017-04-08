package week1;
import edu.duke.*;
public class CommonWords {

	String pth="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/";
	public static void main(String[] args) {
		CommonWords C = new CommonWords();
		C.test();
		}
	public void test(){
		countShakespeare();
	}
	public void countShakespeare(){
		String[] plays = {"caesar.txt","errors.txt","hamlet.txt","likeit.txt",
						  "macbeth.txt","romeo.txt"};
		String[] common = getCommon();
		int[] counts =new int[common.length];
		for(int k=0;k<plays.length;k++){
			FileResource resource =new FileResource(pth+plays[k]);
			countWords(resource,common,counts);
		}
		for(int k=0;k<common.length;k++){
			System.out.println(common[k]+"\t"+counts[k]);
		}
		
	}
	public int indexOf(String[] list, String word){
		for(int k=0; k<list.length;k++){
			if(list[k].equals(word)){
				return k;
			}
		}
		return -1;
	}
	
	public void countWords(FileResource resource,String[] common,int[] counts){
		for(String word : resource.words()){
			word=word.toLowerCase();
			int index =   indexOf(common,word);
			if(index != -1){
				counts[index]+=1;
				
			}
		}
	}
	
	
	public String[] getCommon(){
		FileResource resource =new FileResource(pth+"common.txt");
		String[] common = new String[20];
		int index = 0;
		for(String s : resource.words()){
			common[index]=s;
			index+=1;
		}
		return common;
	}
	

}

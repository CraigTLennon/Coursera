package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovK {

	

	private int K;
	private String trainText=" ";
	private Random myRandom;
	private HashMap<String,ArrayList<String>> follows;
	
	public void setTraining(String s){
		trainText=s;
		setFollows();
	}
	
	private void setFollows(){
		for(int pos =0; pos<trainText.length()-K;pos++){
			String nextChar=trainText.substring(pos,pos+K);
			String follower=trainText.substring(pos+K,pos+K+1);
			if(follows.containsKey(nextChar)){
				ArrayList<String> newList=follows.get(nextChar);
				newList.add(follower);
				follows.put(nextChar,newList);
			}else{
				ArrayList<String> newList= new ArrayList<String>();
				newList.add(follower);
				follows.put(nextChar, newList);
		}}
	}
	
	public String getRandomText(int numChar){
		if(trainText==null) return " ";
		if(follows==null) return " ";
		StringBuilder sb=new StringBuilder();
		for(int j=0;j<K;j++){
			int index = myRandom.nextInt(trainText.length());
			String next=Character.toString(trainText.charAt(index));
			sb.append(next);
		}
		for(int k=0;k<numChar;k++){
			String lastLetter=sb.substring(k, k+K);
			ArrayList<String> choices =follows.get(lastLetter); 
			String next="";
			if(choices==null){next=" ";
			}else{
			int index = myRandom.nextInt(choices.size());
			next=choices.get(index);}
			sb.append(next);
		}
		return sb.toString();
	}
	
	public MarkovK(int k) {
		super();
		this.K=k;
		this.myRandom =new Random();
		this.follows=new HashMap<String,ArrayList<String>>();
	}

	public void setRandom(int seed){
		myRandom=new Random(seed);
	}

}

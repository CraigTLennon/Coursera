package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovN extends MarkovA {

	
	private int K;
	private HashMap<String,ArrayList<String>> follows;
	
	public  MarkovN(int K){
		this.K=K;
		this.myRandom =new Random();
		this.follows=new HashMap<String,ArrayList<String>>();
	}
	
	private void setFollows(){
		for(int pos =0; pos<myText.length()-K;pos++){
			String nextChar=myText.substring(pos,pos+K);
			String follower=myText.substring(pos+K,pos+K+1);
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
	
	
	@Override
	public String getRandomText(int numChar){
		if(myText==null) return " ";
		setFollows();
		if(follows==null) return " ";
		StringBuilder sb=new StringBuilder();
		for(int j=0;j<K;j++){
			int index = myRandom.nextInt(myText.length());
			String next=Character.toString(myText.charAt(index));
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
	
	

}

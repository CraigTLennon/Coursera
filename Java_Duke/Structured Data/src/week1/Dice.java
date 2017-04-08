package week1;

import java.util.Random;

public class Dice {

	public static void main(String[] args) {
		Dice D = new Dice();
		D.test();
	}
	
	
	public void SimpleSimulate(int rolls){
		Random rand = new Random();
		int twos=0;
		int twelves=0;
		
		for(int k=0;k<rolls;k++){
			int d1 = rand.nextInt(6)+1;
			int d2 = rand.nextInt(6)+1;
			if(d1+d2==2){twos+=1;}
			if(d1+d2==12){twelves+=1;}
		}
		System.out.println("% 2's is "+ twos+ "\t"+100.0*twos/rolls);
	}
	public void Simulate(int rolls){
		Random rand = new Random();
		int[] counts=new int [13];
		
		for(int k=0;k<rolls;k++){
			int d1 = rand.nextInt(6)+1;
			int d2 = rand.nextInt(6)+1;
			counts[d1+d2]+=1;
		}
		for( int k=2;k<counts.length;k++){
			System.out.println("% "+ k +"'s is "+ 100.0*counts[k]/rolls);	
		}
		
	}
	public void test(){
		SimpleSimulate(100000);
		Simulate(1000000);
	}
	
	

	

}

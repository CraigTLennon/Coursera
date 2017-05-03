package ooJava.week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class XtraPractice {

	public static void main(String[] args) throws FileNotFoundException {
		XtraPractice X = new XtraPractice();
		X.readCSV();
	}

	public void readCSV() throws FileNotFoundException{
		String fName="C:/Users/cLennon/Documents/GitHub/Coursera/UCSD_Java/data/LifeExpectancyWorldBankModule3.csv";
		File f = new File(fName);
		Scanner scan = new Scanner(f);
		while(scan.hasNext()){
			String nextLine = scan.nextLine();
			System.out.println(nextLine);
		}
		
		scan.close();
	}
	
}

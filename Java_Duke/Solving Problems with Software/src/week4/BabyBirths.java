package week4;
import edu.duke.*;
import week3.Pair;

import java.io.File;

import org.apache.commons.csv.*;


public class BabyBirths {

	public static void main(String[] args) {
		BabyBirths baby = new BabyBirths();
		baby.test();
	}

	private String fileName(int year){
		return "C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Solving Problems with Software/babydata/yob"+Integer.toString(year)+".csv";
	}
	
	public void totalBirths(FileResource FR){
		int mB=0;
		int fB=0;
		for(CSVRecord rec : FR.getCSVParser(false)){
			int numB=Integer.parseInt(rec.get(2));
			String sex = rec.get(1);
			//System.out.println(sex);
			if(rec.get(1).equals("M")){mB=mB+numB;
		    }else if(rec.get(1).equals("F")){fB=fB+numB;
			}else{System.out.println("Error");}
		}
		int total = mB+fB;
		System.out.println("Boy births: "+mB+" Girl births: "+fB+ " Total Names: "+total);
	}
	
	
	public int getRank(CSVParser par,String Name,String sex){
		int femaleLine=-1;
		int nameLine=-1;
		Boolean stillFemale=true;
		for(CSVRecord rec : par){
			if(rec.get(0).equals(Name) &&rec.get(1).equals(sex)){ nameLine=(int) par.getCurrentLineNumber();}
			if(rec.get(1).equals("M")){ stillFemale=false;}
			if(stillFemale){ femaleLine=(int) par.getCurrentLineNumber();}
			
		}
		if(nameLine==-1){return -1;
		}else if(sex=="F" ){return nameLine;}else if(sex=="M"){
			return nameLine-femaleLine;
		}else{
			System.out.println("ERROR in getRank");
		return -1;}
	}	
	public int getRank(CSVParser par, int year,String Name,String sex){
		int femaleLine=-1;
		int nameLine=-1;
		Boolean stillFemale=true;
		for(CSVRecord rec : par){
			if(rec.get(0).equals(Name) &&rec.get(1).equals(sex)){ nameLine=(int) par.getCurrentLineNumber();}
			if(rec.get(1).equals("M")){ stillFemale=false;}
			if(stillFemale){ femaleLine=(int) par.getCurrentLineNumber();}
			
		}
		if(nameLine==-1){return -1;
		}else if(sex=="F" ){return nameLine;}else if(sex=="M"){
			return nameLine-femaleLine;
		}else{
			System.out.println("ERROR in getRank");
		return -1;}
	}
	
	public int getRank(int year,String Name,String sex){
		DirectoryResource D = new DirectoryResource();
		int rank=-1;
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVParser p=fr.getCSVParser(false);
			rank=getRank(p,year,Name,sex);
		}
		return rank;
	}

	public int getRank(String Name,String sex){
		DirectoryResource D = new DirectoryResource();
		int rank=-1;
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVParser p=fr.getCSVParser(false);
			rank=getRank(p,Name,sex);
		}
		return rank;
	}
	
	
	public int getRank(int year,String Name,String sex,String fileName){
		int rank=-1;	
		FileResource fr = new FileResource(fileName);
		CSVParser p=fr.getCSVParser(false);
		rank=getRank(p,year,Name,sex);
		System.out.println(Name+rank);
		return rank;
	}
	
	public String getName(CSVParser par,int year, int rank,String sex){
		int femaleLine=-1;
		int nameLine=-1;
		String name ="";
		Boolean stillFemale=true;
		int modRank=-1;
		for(CSVRecord rec : par){
			nameLine=(int) par.getCurrentLineNumber();
			if(sex=="F" && nameLine==rank){name=rec.get(0);}
			if(rec.get(1).equals("M")){ stillFemale=false;modRank=rank+femaleLine;}
			if(stillFemale){ femaleLine=(int) par.getCurrentLineNumber();}
			if(sex=="M" && nameLine==modRank){name=rec.get(0);}
		}
		return name;
	}
	
	public String getName(int year, int rank,String sex,String fileName){
		FileResource fr = new FileResource(fileName);
		CSVParser p=fr.getCSVParser(false);
		
		String nename=getName(p,year,rank,sex);
		System.out.println(nename);
		return nename;
	}
	
	public String getName(int year,int rank,String sex){
		DirectoryResource D = new DirectoryResource();
		String name="";
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVParser p=fr.getCSVParser(false);
			name=getName(p,year,rank,sex);
		}
		return name;
	}
	
	public void whatIsNameInYear(String name,int year, int newYear,String sex){
		String nameYear=fileName(year);
		String newYearFile=fileName(newYear);
		int rank=getRank(year, name,sex,nameYear);
		String newName=getName(year,rank,sex,newYearFile);
		System.out.println(name+" born in "+year+" would be "+newName+" if born in "+newYear);
	}
	
	public int yearOfHighestRank(String name, String sex){
		DirectoryResource D = new DirectoryResource();
		
		int highestRank=1000000;
		String highestFile="";
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVParser p=fr.getCSVParser(false);
			int currentRank=getRank(p,name,sex);
			
			if(currentRank>-1 && currentRank<highestRank){
			highestRank=currentRank;
			highestFile=fl.getName();
		//	System.out.println(currentRank+" "+highestRank+" "+highestFile);
		}}
		
		String year = 	highestFile.replace("yob", "");
		//System.out.println(year);
		year = 	year.replace(".csv", "");
		//System.out.println(year);
		return Integer.parseInt(year);
	
	}

	public double getAverageRank(String name, String sex){
		DirectoryResource D = new DirectoryResource();
		
		Double total=0.0;
		Double count=0.0;
		
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVParser p=fr.getCSVParser(false);
			int currentRank=getRank(p,name,sex);
			
			if(currentRank>-1){
			total=total+(double) currentRank;
			count=count+1.0;
			System.out.println(total+" "+count);
		}}
		
		return total/count;
	
	}

	public double getBirthsHigher(int year,String name, String sex){
		
		String fileName=fileName(year);
		FileResource fr = new FileResource(fileName);
		CSVParser par=fr.getCSVParser(false);
		int maleTotal=0;
		int femTotal=0;
		for(CSVRecord rec : par){
			if(rec.get(0).equals(name) &&rec.get(1).equals(sex)){ 
				if(sex=="M"){return maleTotal;}else{return femTotal;}
			}
			if(rec.get(1).equals("M")){ maleTotal=maleTotal+Integer.parseInt(rec.get(2));
			}else{femTotal=femTotal+Integer.parseInt(rec.get(2));	}
			}
				
		return -1;
	}	
	
	
	public void test(){
		//System.out.println(fileName(1974));
		String testFile="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Solving Problems with Software/babydata/yob1880.csv";
		//FileResource F= new FileResource(testFile);
		//totalBirths(F);
//		getRank(1880,"John","M",testFile);
//		getName(1880,1,"M",testFile);
//		getRank(1880,"John","F",testFile);
//		getName(1880,229,"F",testFile);
//		getRank(1880,"Mary","F",testFile);
//		getName(1880,1,"F",testFile);
//		getRank(1880,"asddsc","M",testFile);
//		getName(1880,343437,"M",testFile);
//		whatIsNameInYear("Mary",1880,1996,"F");
		//System.out.println("Year was "+yearOfHighestRank("John","M"));
		//System.out.println("Average rank of Della was "+getAverageRank("Della","F"));
		System.out.println("Total births ranked higer was "+getBirthsHigher(1880,"John","M"));
	}
	
	
	
	
}

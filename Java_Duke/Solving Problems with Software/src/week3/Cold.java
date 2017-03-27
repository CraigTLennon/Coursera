package week3;
import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.*;


public class Cold {

	public static void main(String[] args) {
		Cold C= new Cold();
		C.test();
		
	}
	
	public CSVRecord hour(CSVParser par){
		CSVRecord cold =null;
		for(CSVRecord rec : par){
			cold=getExtreme(cold,rec,"TemperatureF");
		}
		System.out.println(cold.get("TemperatureF")+"  " + cold.get("DateUTC"));
		return cold;
	}
	
	public CSVRecord lowHOneFile(CSVParser par){
		CSVRecord cold =null;
		for(CSVRecord rec : par){
			cold=getExtreme(cold,rec,"Humidity");
		}
		System.out.println(cold.get("Humidity")+"  " + cold.get("DateUTC"));
		return cold;
	}
	public CSVRecord getExtreme(CSVRecord r1, CSVRecord r2,String field){

		Double naV=-9999.0;
		if(r1==null || r1.get(field)=="N/A"){return r2;}
		if(r2==null || r2.get(field)=="N/A"){return r1;}
		Double t1=Double.parseDouble(r1.get(field));
		Double t2=Double.parseDouble(r2.get(field));		
		if((t2<t1 && t2!=naV)|| t1==naV){return r2;}else{return r1;} 
	}
	
	public Pair<CSVRecord,String> getExtreme(CSVRecord r1,String f1, CSVRecord r2,String f2,String field){
		Pair<CSVRecord,String> p1 = new Pair<CSVRecord,String>(r1,f1);
		Pair<CSVRecord,String> p2 = new Pair<CSVRecord,String>(r2,f2);
        
		Double naV=-9999.0;
		if(r1==null || r1.get(field)=="N/A"){return p2;}
		if(r2==null || r2.get(field)=="N/A"){return p1;}
		Double t1=Double.parseDouble(r1.get(field));
		Double t2=Double.parseDouble(r2.get(field));
		if((t2<t1 && t2!=naV)|| t1==naV){
			System.out.println(p2.first().get(field)+"  " + p2.first().get("DateUTC"));
			return p2;}else{
				System.out.println(p1.first().get(field)+"  " + p1.first().get("DateUTC"));
				return p1;} 
	}
	
	public CSVRecord lowestHumid(CSVParser parse){
		CSVRecord lowH =null;
		for(CSVRecord rec : parse){
			lowH=getExtreme(lowH,rec,"Humidity");
		}
		System.out.println(lowH.get("Humidity")+"  " + lowH.get("DateUTC"));
		return lowH;
	}
	public void lowHFile(){
		DirectoryResource D = new DirectoryResource();
		CSVRecord lowest=null;
		
		Pair<CSVRecord,String> par= null;
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVRecord temp=lowHOneFile(fr.getCSVParser());
			par = getExtreme(lowest," ",temp,fl.getName(),"Humidity");
			lowest=par.first();
			System.out.println(par.second()+ "   "+par.first().get("Humidity"));
		}
		System.out.println(par.second());
	}
	
	
	public void coldFile(){
		DirectoryResource D = new DirectoryResource();
		CSVRecord coldest=null;
		
		Pair<CSVRecord,String> par= null;
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			CSVRecord temp=hour(fr.getCSVParser());
			par = getExtreme(coldest," ",temp,fl.getName(),"TemperatureF");
			coldest=par.first();
			System.out.println(par.second()+ "   "+par.first().get("TemperatureF"));
		}
		System.out.println(par.second());
	}
	
	public Double avgTemp(CSVParser parse){
		Double total=0.0;
		Double count =0.0;
		Double naV=-9999.0;
		Double temp =naV;
		for(CSVRecord rec : parse){
			temp=Double.parseDouble(rec.get("TemperatureF"));
			if(temp!=naV){
			count=count+1.0;
			total=total+temp;
			}
		}
		return total/count;
	}
	public void testavgTemp(){
		DirectoryResource D = new DirectoryResource();
		
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			Double avg = avgTemp(fr.getCSVParser());
			System.out.println("Average temperature in file is "+avg);
		}	
	}
	
		
	public Double avgWithHighHumid(CSVParser parse, int value){
		Double total=0.0;
		Double count =0.0;
		Double naV=-9999.0;
		Double temp =naV;
		Boolean highEnough=false;
		Double floor =(double) value;
		String humid="";
		
		for(CSVRecord rec : parse){
			temp=Double.parseDouble(rec.get("TemperatureF"));
			humid=rec.get("Humidity");
			if(temp!=naV && humid !="N/A" && Double.parseDouble(humid)>=floor){
			count=count+1.0;
			total=total+temp;
			highEnough=true;
			}
		}
		if(highEnough){	return total/count;}
		else{
			System.out.println("No temperatures with that humidity");
			return 0.0; //Really this should be an exception or something
		}
	}
	
	public void testHighHumid(){
		DirectoryResource D = new DirectoryResource();
		for(File fl : D.selectedFiles()){
			FileResource fr = new FileResource(fl);
			Double avg = avgWithHighHumid(fr.getCSVParser(),80);
			System.out.println("Average temperature in file is "+avg);
		}	
	}	
	public void test(){
		//coldFile();
		//lowHFile();
		//testavgTemp();
		testHighHumid();
	}

}

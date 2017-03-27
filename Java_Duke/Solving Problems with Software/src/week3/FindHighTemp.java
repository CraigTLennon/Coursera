package week3;
import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class FindHighTemp {

	public static void main(String[] args) {
		FindHighTemp F = new FindHighTemp();
		F.test();
	}
	
	public CSVRecord dayHigh(CSVParser p){
		CSVRecord largest=null;
		for(CSVRecord r : p){
		if(largest ==null){
			largest=r;
		}else{
			double temp=Double.parseDouble(r.get("TemperatureF"));
			double largeTemp=Double.parseDouble(largest.get("TemperatureF"));
			if(temp>largeTemp){
				largest=r;
			}
		}}
		return largest;
	}
	public CSVRecord manyDayHigh(){
		CSVRecord largest = null;
		DirectoryResource dr=new DirectoryResource();
		for(File r : dr.selectedFiles()){
			FileResource fl=new FileResource(r);
			CSVRecord current = dayHigh(fl.getCSVParser());
			if(largest ==null){
				largest=current;
			}else{
				double temp=Double.parseDouble(current.get("TemperatureF"));
				double largeTemp=Double.parseDouble(largest.get("TemperatureF"));
				if(temp>largeTemp){
					largest=current;
				}//above can be refactored by making a method for comparing 
				//two records.
		}}	return largest;
	}
	
	
	public void test(){
		//FileResource fl=new FileResource();
		CSVRecord largest=manyDayHigh();			//dayHigh(fl.getCSVParser());
		System.out.println("hottest "+largest.get("TemperatureF"));
}
	
	
	
	

}

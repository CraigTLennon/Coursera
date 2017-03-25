package week3;

import edu.duke.*; 
import org.apache.commons.csv.*;
public class Exporter {

	public static void main(String[] args) {
		Exporter E = new Exporter();
		E.test();

	}
public void listExporters(CSVParser p, String export){
	for(CSVRecord r : p){
		String exports=r.get("Exports");
		if(exports.indexOf(export)>-1){ //Better: exports.contains(export)
			System.out.println(r.get("Country"));
		}
	}
	}

	public String countryInfo(CSVParser p,String country){
		
		for(CSVRecord r : p){
		String cty=r.get("Country");
		System.out.println(cty);
		if(cty.contains(country)){
		System.out.print(country +": ");
		System.out.print(r.get("Exports")+": ");
		System.out.print(r.get("Value (dollars)")+": ");
		return country +": "+r.get("Exports")+": "+r.get("Value (dollars)")+": ";}}
		return "NOT FOUND";
	}

	public void list2Exporters(CSVParser p, String export1,String export2){
		for(CSVRecord r : p){
			String exports=r.get("Exports");
			if(exports.contains(export1)&&exports.contains(export2)){ 
				System.out.println(r.get("Country"));
			}
		}}
	public void bigExporters(CSVParser p ,String money){
		for(CSVRecord r : p){
			String value=r.get("Value (dollars)");
			if(value.length()>money.length()){ 
				System.out.println(r.get("Country")+ " "+value);
			}
		}}
	
	public void test(){
		FileResource fl = new FileResource();
		CSVParser parser = fl.getCSVParser();
		//listExporters(parser,"coffee");
		//String info=countryInfo(parser,"Yemen");
		//list2Exporters(parser,"gold","diamonds");
		bigExporters(parser,"400,000,000");
	}
}

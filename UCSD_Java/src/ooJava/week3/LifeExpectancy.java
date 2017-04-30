package ooJava.week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet {

	private UnfoldingMap map;
	private Map<String, Float>  lifeExpByCountry;
	private List<Feature> countries;
	private List<Marker> countryMarkers;
	
	
	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		try {
			String fName="C:/Users/cLennon/Documents/GitHub/Coursera/UCSD_Java/data/LifeExpectancyWorldBankModule3.csv";
			lifeExpByCountry=loadLifeExp(fName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String dataName="C:/Users/cLennon/Documents/GitHub/Coursera/UCSD_Java/data/countries.geo.json";
		countries = GeoJSONReader.loadData(this, dataName);
		countryMarkers=MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	
	private void shadeCountries(){
		for(Marker mark:countryMarkers){
			String countryId=mark.getId();
//			System.out.println(countryId);
			if(lifeExpByCountry.containsKey(countryId)){
				float lifeExp=lifeExpByCountry.get(countryId);
				int colorLevel=(int) map(lifeExp,40,90,10,255);
				mark.setColor(color(255-colorLevel,100,colorLevel));
			}else{
				mark.setColor(color(150,150,150));
			}
		}
	}
	
	private Map<String, Float> loadLifeExp(String fName) throws IOException{
		lifeExpByCountry = new HashMap<String,Float>();
		
		try{Reader r=new FileReader(fName);
		BufferedReader br=new BufferedReader(r);
		String newline=br.readLine();
		newline=br.readLine();
		String country="";
		Float le=0.0f;
		while(newline!=null){
			String[] nextLine = newline.split(",");
			if(nextLine.length>0){
//				System.out.println(nextLine[5]);
				if(!nextLine[5].equals("..")){
				 country = nextLine[4];
				try{ le=Float.parseFloat(nextLine[5]);}catch(NumberFormatException n){le=-1.0f;}finally{}
//				System.out.println(country + " "+le);
				}
				lifeExpByCountry.put(country, le);
			}
			
			 newline=br.readLine();
			
		}
		
		}catch(FileNotFoundException f){}
		
//		for(String s:lifeExpByCountry.keySet()){System.out.println(s+" "+lifeExpByCountry.get(s));}
		return lifeExpByCountry;
	}
	
	public void draw(){
		map.draw();
	}
}

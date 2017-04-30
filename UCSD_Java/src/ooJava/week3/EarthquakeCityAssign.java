package ooJava.week3;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import ooJava.ParseFeed;
import processing.core.PApplet;

public class EarthquakeCityAssign extends PApplet {

	private static final long serialVersionUID = 1L;
	
	private UnfoldingMap map;
	private List<Marker> markers;
	private List<PointFeature> ptFeatures;
	
	public void setup(){
	
	AbstractMapProvider provider = new OpenStreetMap.OpenStreetMapProvider();//Google.GoogleMapProvider();	
	
	size(1000,800,OPENGL);
	map = new UnfoldingMap(this,325,25,650,750,provider);
	MapUtils.createDefaultEventDispatcher(this, map);
	String url ="https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	getMarkers(url);
	   
	    map.addMarkers(markers);
	}
	public void legend(){
		
		fill(255);
		rect(25,25,225,425);
		fill(0);
		text("Legend",50,50);
		int red=color(255,0,0);
		int blue=color(0,0,255);
		int yellow=color(255,255,0);
		text("5.0+ Magnitude",100,105);
		text("4.0+ Magniude",100,155);
		text("Below 4.0 Magnitude",100,205);
		fill(red);
		ellipse(50,100,15,15);
		fill(yellow);
		ellipse(50,150,11,11);
		fill(blue);
		ellipse(50,200,8,8);
		
	}
	public SimplePointMarker setMarkerSize(SimplePointMarker m){
			double mag=Double.parseDouble(m.getProperty("magnitude").toString());
			if(mag>=5.0){m.setRadius(15.0f);}
			else if(mag>=4.0){m.setRadius(11.0f);}
			else{m.setRadius(8.0f);}
		return m;
		}	
	
	public SimplePointMarker setMarkerColor(SimplePointMarker m){
		int red=color(255,0,0);
		int blue=color(0,0,255);
		int yellow=color(255,255,0);
			double mag=Double.parseDouble(m.getProperty("magnitude").toString());
			if(mag>=5.0){m.setColor(red);}
			else if(mag>=4.0){m.setColor(yellow);}
			else{m.setColor(blue);}
		return m;
		}

	
	public List<Marker> getMarkers(String url){
		
		ptFeatures =ParseFeed.parseEarthquake(this,url);
		 markers = new ArrayList<Marker>(); 
		    for(PointFeature p : ptFeatures){
		    	SimplePointMarker s = new SimplePointMarker(p.getLocation(),p.getProperties());
		    	s=setMarkerColor(s);
		    	s=setMarkerSize(s);
		    	markers.add(s);
//		    	System.out.println(p.getProperty("depth"));
		       }
		    System.out.println("Test");
		    return markers;
	}
	
	
	public void draw(){
		map.draw();
		legend();
	}
	
	
	
	
	}
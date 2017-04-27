package ooJava.week3;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthQuakeCityMap extends PApplet {

	private UnfoldingMap map;
	
	public void setup(){
		size(1000,600,OPENGL); //specifies renderer
		AbstractMapProvider provider = new Microsoft.RoadProvider();//OpenStreetMap.OpenStreetMapProvider();//Google.GoogleTerrainProvider(); //map provider
//		AbstractMapProvider provider = new Google.GoogleMapProvider();
		map =new UnfoldingMap(this,25,25,900,700,provider);
		map.zoom(1);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		//adding a marker at a location
		Location valLoc = new Location(-38.14f,-73.03f);
		//we can also define features, which have other types of information
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdivia, Chile");
		valEq.addProperty("magnitude", 9.5);
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", 1960);
					
		//SimplePointMarker val = new SimplePointMarker(valLoc);
		//alternatively  we could use a more generic type
		Marker val = new SimplePointMarker(valLoc,valEq.getProperties());//variable of type marker assigned to simple point marker, but could be reassigned to different type marker.
		map.addMarker(val);
		
//		Adding more (copied) and creating a list for them
		 PointFeature valdiviaEq = new PointFeature(new Location(-38.14f,-73.03f));
		    valdiviaEq.addProperty("title", "Valdivia, Chile");
		    valdiviaEq.addProperty("magnitude", "9.5");
		    valdiviaEq.addProperty("date", "March 22, 1960");
		    valdiviaEq.addProperty("year", 1960);
		    
		    PointFeature alaskaEq = new PointFeature(new Location(61.02f,-147.65f));
		    alaskaEq.addProperty("title", "1964 Great Alaska Earthquake");
		    alaskaEq.addProperty("magnitude", "9.2");
		    alaskaEq.addProperty("date", "March 28, 1964"); 
		    alaskaEq.addProperty("year", 1964);

		    PointFeature sumatraEq = new PointFeature(new Location(3.30f,95.78f));
		    sumatraEq.addProperty("title", "Off the West Coast of Northern Sumatra");
		    sumatraEq.addProperty("magnitude", "9.1");
		    sumatraEq.addProperty("date", "February 26, 2004");
		    sumatraEq.addProperty("year", 2004);

		    
		    PointFeature japanEq = new PointFeature(new Location(38.322f,142.369f));
		    japanEq.addProperty("title", "Near the East Coast of Honshu, Japan");
		    japanEq.addProperty("magnitude", "9.0");
		    japanEq.addProperty("date", "March 11, 2011");
		    japanEq.addProperty("year", 2011);

		    
		    PointFeature kamchatkaEq = new PointFeature(new Location(52.76f,160.06f));
		    kamchatkaEq.addProperty("title", "Kamchatka");
		    kamchatkaEq.addProperty("magnitude", "9.0");
		    kamchatkaEq.addProperty("date", "November 4, 1952");
		    kamchatkaEq.addProperty("year", 1952);

		    
		    List<PointFeature> bigEarthquakes = new ArrayList<PointFeature>();
		    bigEarthquakes.add(valdiviaEq);
		    bigEarthquakes.add(alaskaEq);
		    bigEarthquakes.add(sumatraEq);
		    bigEarthquakes.add(japanEq);
		    bigEarthquakes.add(kamchatkaEq);
		
		
		    List<Marker> marks = new ArrayList<Marker>(); //Note list is interface, as is Marker, so we our variables are of generic type.
		    for(PointFeature b : bigEarthquakes){
		    	marks.add(new SimplePointMarker(b.getLocation(),b.getProperties()));
		       }
		    int yellow=color(255,255,0);
		    int gray=color(150,150,150);
		   for(Marker mk: marks){
			   if((int) mk.getProperty("year")>2000){
		    		mk.setColor(yellow);
		    	}else{
		    		mk.setColor(gray);
		    	}
		   }
		    
		    map.addMarkers(marks);
	}
	
	
	public void draw(){
		background(200,1);//canvas color
		map.draw();
//		addKey();
	}
	
	
}

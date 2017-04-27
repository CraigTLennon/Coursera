package ooJava.week1;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class HelloWorld extends PApplet {
	
	public static String mbTilesString = "blankLight-1-3.mbtiles"; // local tiles when no internet (ucsd)
	private static final boolean offline = false;  //if working offline


	
	UnfoldingMap map1;
	UnfoldingMap  map2;
	
	public void setup(){
		
		size(800,600,P2D);
		this.background(200,200,200);
		
		AbstractMapProvider provider = new Microsoft.RoadProvider();//OpenStreetMap.OpenStreetMapProvider();//Google.GoogleTerrainProvider(); //map provider
		
		int zoomLevel=10;
		
		if(offline){
			provider = new MBTilesMapProvider(mbTilesString);
			zoomLevel=3;
		}
		
		map1=new UnfoldingMap(this, 450,50,300,500,provider); //
		map1.zoomAndPanTo(zoomLevel, new Location(39.2f,-117.2f));
		MapUtils.createDefaultEventDispatcher(this,map1);		
		
		map2=new UnfoldingMap(this,50,50,300,500,provider); //
		map2.zoomAndPanTo(zoomLevel+1, new Location(39.2f,-117.2f));
		MapUtils.createDefaultEventDispatcher(this,map2);	
	}
	
	public void draw(){
		map1.draw();
		map2.draw();
	}
	
	
	
}

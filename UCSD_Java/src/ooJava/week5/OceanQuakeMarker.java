package ooJava.week5;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	
	public void drawMarker(PGraphics pg, float x, float y){
		
		
		drawEarthquake(pg,x,y);
	}
	public void showTitle(PGraphics pg, float x, float y){
		pg.text(this.getMagnitude()+", "+this.getRadius(), x, y);
	}
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.

		pg.pushStyle();
		
		// determine color of marker from depth
		super.colorDetermine(pg);
//		super.drawThreatCircle(pg, x, y);
		float rad=this.getRadius();
		pg.ellipse(x, y, 2*rad, 2*rad);
		pg.popStyle();
	}
	
	


	

}

package week5;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
	}
	
	public void drawMarker(PGraphics pg, float x, float y){
		drawEarthquake(pg,x,y);
	}
	public void showTitle(PGraphics pg, float x, float y){
		pg.text(this.getMagnitude()+", "+this.getRadius(), x, y);
	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		pg.pushStyle();
		
		// determine color of marker from depth
		super.colorDetermine(pg);

		// call abstract method implemented in child class to draw marker shape
		 

		super.drawX(pg, x, y);
		// reset to previous styling
//		super.drawThreatCircle(pg, x, y);
		float rad=this.getRadius();
		pg.rect(x-rad, y-rad, 2*rad, 2*rad);
		pg.popStyle();
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}
package ooJava.week5;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EventExample extends PApplet {

	private UnfoldingMap map;
	
	public void setup(){
		size(800,600,OPENGL);
		AbstractMapProvider provider =new OpenStreetMap.OpenStreetMapProvider();//Google.GoogleMapProvider();
		map = new UnfoldingMap(this,50,50,500,500,provider);
		MapUtils.createDefaultEventDispatcher(this, map);
	}
	
	public void goBlack(){
		if(key=='w'){background(0,0,0);}
	}
	
	public void draw(){
		map.draw();
		drawButtons();
		
	}
	
	private void drawButtons(){
		fill(255,255,255);
		rect(100,100,25,25);
		fill(100,100,100);
		rect(100,150,25,25);
	}
	@Override
	public void mouseReleased(){
		if(mouseX>100 && mouseX >100 && mouseY>100 && mouseY<125) background(255,255,255);
		else if(mouseX>100 && mouseX >100 && mouseY>150 && mouseY<175) background(100,100,100);
		else super.mouseReleased();
	}
	
}

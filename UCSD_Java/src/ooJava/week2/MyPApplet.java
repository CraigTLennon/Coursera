package ooJava.week2;

import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet {

	
	PImage backGround;
	
	public void setup(){//configure the canvas
		background(255);
		stroke(0);
		size(800,800);
		String url="https://static.pexels.com/photos/88212/pexels-photo-88212.jpeg";
		backGround=loadImage(url,"png");
		
	}
	
	public void draw(){ //display content
//		backGround.resize(800,700); disregards aspect
//		backGround.resize(0, 800); //means do not specify that aspect
		
		backGround.resize(0, height); //gets canvas height
		//note stays blurry after resizing, i guess bc it interpolates from the last image
		image(backGround,0,0);
		int[] color=sunColor(second());
		
		fill(color[0],color[1],color[2]);
		//use width and height to draw circle that scales with the window
		ellipse(width/4,height/4,width/5,height/5);
	}
	
	public int[] sunColor(float seconds){
		int[] rgb = new int[3];
		float diff30=Math.abs(30-seconds);
		float ratio = diff30/30;
		
		rgb[0]=(int) (255*ratio);
		rgb[1]=(int) (255*ratio);
		rgb[2]=0;
		return rgb;
		
	}
	
	
}

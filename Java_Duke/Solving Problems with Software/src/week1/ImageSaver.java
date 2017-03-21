package week1;

import java.io.File;
import edu.duke.*;

public class ImageSaver {

	public static void main(String[] args){
		ImageSaver I =new ImageSaver();
		I.doSave()		;
	}
	
	public void doSave(){
		DirectoryResource  dr=new DirectoryResource();
		for(File f : dr.selectedFiles()){
			ImageResource image = new ImageResource(f);
			String nme=image.getFileName();
			String grayName="gray-"+nme;
			image.setFileName(grayName);
			image.save();
			image.draw();
			
		}
			
		
	}
	
	
}

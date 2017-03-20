package Images;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class BatchInversions {

	public static void main(String[] args){
		BatchInversions D =new BatchInversions();
		D.selectConvertSave()		;
	}
	
	public ImageResource makeInversion(ImageResource img){
		ImageResource outImage = new ImageResource(img.getWidth(), img.getHeight());
		for(Pixel pix : outImage.pixels()){
			Pixel inPix = img.getPixel(pix.getX(), pix.getY());
			pix.setRed(255-inPix.getRed());
			pix.setBlue(255-inPix.getBlue());
			pix.setGreen(255-inPix.getGreen());
			
		}
		return outImage;
	}
	

	public void selectConvertSave(){
	DirectoryResource  dr=new DirectoryResource();
	for(File f : dr.selectedFiles()){
		ImageResource inImage = new ImageResource(f);
		ImageResource g = makeInversion(inImage);
		String nme=f.getName();
		String dir=f.getParent();
		String invName="inverted-"+nme;
		System.out.println(dir+"\\"+invName);
		g.setFileName(dir+"\\"+invName);
		g.save();
		g.draw();
	
}}

	//public void funConvertSave(String append, Function fun)
	//Easy to make functional in scala, java not sure
	
//	public void testInv(){
//		ImageResource ir = new ImageResource();
//		ImageResource gray = makeInversion(ir);
//		gray.draw();
//	}
//	
	
	
}

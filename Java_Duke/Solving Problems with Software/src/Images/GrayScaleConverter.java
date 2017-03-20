package Images;
import java.io.File;

import edu.duke.*;

public class GrayScaleConverter {

	public static void main(String[] args){
		GrayScaleConverter D =new GrayScaleConverter();
		D.selectConvertSave()		;
	}

	public ImageResource makeGray(ImageResource inImage){
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for(Pixel pixel : outImage.pixels()){
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			int avg = (inPixel.getRed()+inPixel.getBlue()+inPixel.getGreen())/3;
			pixel.setRed(avg);
			pixel.setBlue(avg);
			pixel.setGreen(avg);
			
			}
		return outImage;
	}
	public void testGray(){
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}
//	public void selectConvert(){
//		DirectoryResource  dr=new DirectoryResource();
//		for(File f : dr.selectedFiles()){
//			ImageResource inImage = new ImageResource(f);
//			ImageResource g = makeGray(inImage);
//			g.draw();
//		}}

	
		public void selectConvertSave(){
		DirectoryResource  dr=new DirectoryResource();
		for(File f : dr.selectedFiles()){
			ImageResource inImage = new ImageResource(f);
			ImageResource g = makeGray(inImage);
			String nme=f.getName();
			String dir=f.getParent();
			String grayName="gray-"+nme;
			System.out.println(dir+"\\"+grayName);
			g.setFileName(dir+"\\"+grayName);
			g.save();
			g.draw();
		//If I were not using the library, I think I would add a method  to directory resource to return the dir name. 
		
	}}
	
}

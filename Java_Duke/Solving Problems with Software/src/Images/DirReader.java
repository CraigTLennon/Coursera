package Images;
import edu.duke.*;
import java.io.File;




public class DirReader {

	public static void main(String[] args){
		DirReader D =new DirReader();
		D.checkDir()		;
	}
	
	
	public void checkDir(){
		DirectoryResource  dr=new DirectoryResource();
		for(File f : dr.selectedFiles()){
			System.out.println(f);
		}
		
	}
}

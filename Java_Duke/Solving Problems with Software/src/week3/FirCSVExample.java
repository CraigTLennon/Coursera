package week3;
import edu.duke.*; 
import org.apache.commons.csv.*;
public class FirCSVExample {



	public static void main(String[] args) {
		FirCSVExample F=new FirCSVExample();
		F.readFood();

	}
	public void readFood(){
		FileResource fr = new FileResource(); // with no filename you select dir
		CSVParser parser =fr.getCSVParser();
		for(CSVRecord rec : parser){
			System.out.print(rec.get("Name")+" ");
			System.out.println(rec.get("Favorite Food"));
		}
		
		
	}
}

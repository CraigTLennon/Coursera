import edu.duke.*;

public class HelloWorld {
	public static void main(String[] args){
		HelloWorld H =new HelloWorld();
		H.sayHello()		;
	}
	
	
	public void sayHello(){
		FileResource resource = new FileResource("C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Solving Problems with Software/src/hello_unicode.txt");
		for (String line : resource.lines()) {
			System.out.println(line);
		}
				
	}
}

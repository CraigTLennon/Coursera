package week3;

public class WordGram {

	private String[] myWords;
	private int size;
	public WordGram(String[] source,int start, int size){
		myWords = new String[size];
		this.size=size;
		System.arraycopy(source,start,myWords,0,size);
	}
	
	public String wordAt(int index){
		if(index<0 || index>myWords.length){
			throw new IndexOutOfBoundsException("bad index "+index);
		}
		return myWords[index];
	}
	
	public int length(){
		return myWords.length;
	}
	public int hashCode(){
		return toString().hashCode();
	}
	
	public boolean equals(Object o){
		WordGram other = (WordGram) o;
		if(length() !=other.length() ) return false;
		return this.toString().equals(other.toString());
	}
	
	public String toString(){
		StringBuilder result = new  StringBuilder();
		for(int i=0;i<myWords.length;i++){
			result.append(myWords[i]);
			result.append(" ");
		}
		return result.toString();
	}
	public WordGram shiftAdd(String word){
		String[] newSource = new String[size];
		System.arraycopy(myWords, 1, newSource, 0, size-1);
		newSource[size-1]=word;
		return new WordGram(newSource,0,size);
	}
	
}

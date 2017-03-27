package week3;

public class Pair<U,V> {
	private U first;
	private V second;
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
    public U first(){return first;}
    public V second(){return second;}

}

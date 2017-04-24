/**
 * 
 */
package week1;

/**
 * @author cLennon
 *Notes:  Interface  is a type which promises certain methods.  Classes implementing them  
 */
/**
 * @author cLennon
 *
 */
public interface Filter {

	public String getName();
	
	public boolean satisfies (QuakeEntry qe); 
}

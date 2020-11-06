package cs2321;

import java.util.Comparator;


public class MaxComparator<K> implements Comparator<K> {
	
	// This compare method simply calls the compareTo method of the argument. 
	// If the argument is not a Comparable object, and therefore there is no compareTo method,
	// it will throw ClassCastException. 
	public int compare(K a, K b) throws ClassCastException {
		return ((Comparable <K>) b).compareTo(a);
	}
}

package cs2321;

import java.util.Comparator;

public class MaxComparator<T> implements Comparator<T>{
		// This compare method simply calls the compareTo method of the argument. 
		// If the argument is not a Comparable object, and therefore there is no compareTo method,
		// it will throw ClassCastException. 
		public int compare(T a, T b) throws ClassCastException {
			return ((Comparable <T>) b).compareTo(a);
		}

}

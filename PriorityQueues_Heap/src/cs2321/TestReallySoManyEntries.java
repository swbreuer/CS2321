package cs2321;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.Entry;

public class TestReallySoManyEntries {

	HeapPQ<Integer, String> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<Integer, String>();
	}
	
	
	@Test
	public void testinsert() {
		for(int k = 100; k<=4000; k= k+10) {
			heappq = new HeapPQ<Integer, String>();
			long start = System.nanoTime();
			for(int i = 1; i<=k; i++) {
				heappq.insert(i, "A");
			}
			long stop = System.nanoTime();
			System.out.printf("%d,%d%n", k , stop-start);
		}
	}
}

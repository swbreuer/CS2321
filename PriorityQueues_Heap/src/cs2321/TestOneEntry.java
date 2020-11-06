package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.AdaptablePriorityQueue;
import net.datastructures.Entry;

public class TestOneEntry {

	HeapPQ<Integer, String> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<Integer, String>();
		heappq.insert(50, "A");
	}
	
	
	@Test
	public void testInsert1() {
		assert(heappq.removeMin() instanceof Entry<?,?>);
	}
	@Test
	public void testkey() {
		//assertEquals(heappq.removeMin().getKey(), 50);
	}
	@Test
	public void testvalue() {
		assertEquals(heappq.removeMin().getValue(), "A");
	}
	@Test
	public void testsize() {
		heappq.removeMin();
		assert( heappq.size()==0);
	}
	@Test
	public void testisempty() {
		heappq.removeMin();
		assert( heappq.isEmpty());
	}
}
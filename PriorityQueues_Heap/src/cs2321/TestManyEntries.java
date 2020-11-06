package cs2321;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.Entry;

public class TestManyEntries {

	HeapPQ<Integer, String> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<Integer, String>();
		for(int i = 1; i<=500; i++) {
			heappq.insert(i, "A");
		}
	}
	
	
	@Test
	public void testInsert1() {
		assert(heappq.removeMin() instanceof Entry<?,?>);
	}
	@Test
	public void testkey() {
		for(int i = 1; i<=500; i++) {
			if (i == 490) {
				i = 490;
			}
			int key = heappq.removeMin().getKey();
			assertEquals(key, i);
			System.out.println(i);
		}
	}
	@Test
	public void testvalue() {
		for(int i = 1; i<=500;i++) {
			assertEquals(heappq.removeMin().getValue(), "A");
		}
	}
	@Test
	public void testsize() {
		heappq.removeMin();
		assert( heappq.size()==499);
	}
	@Test
	public void testisempty() {
		heappq.removeMin();
		assert( heappq.isEmpty());
	}

}

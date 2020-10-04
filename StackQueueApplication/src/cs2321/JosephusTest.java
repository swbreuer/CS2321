package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JosephusTest {
	private String[] input;
	private DoublyLinkedList<String> expected;
	private DoublyLinkedList<String> output;
	private Josephus josephus;

	@Before
	public void setUp() throws Exception {
		josephus = new Josephus();
		expected = new DoublyLinkedList();
	}
	
	private void inputSetup(int num) {
		input = new String[num];
		for(int i = 0; i < num; i++) {
			input[i]=Character.toString((char)i+65);
		}
	}

	private void outputSetup(String chars) {
		String[] characters = chars.split("");
		for(int i = 0; i < characters.length; i++) {
			expected.addLast(characters[i]);
		}
	}
	
	@Test
	public void testOrder1() {
		inputSetup(1);
		outputSetup("A");
		output = josephus.order(input, 5);
		for(String person: output) {
			String correct = expected.removeFirst();
			org.junit.Assert.assertEquals(correct,person);
		}
	}

	@Test
	public void testOrder2() {
		inputSetup(20);
		outputSetup("EJOTFLRDMAISKCQPBHNG");
		output = josephus.order(input, 5);
		for(String person: output) {
			String correct = expected.removeFirst();
			org.junit.Assert.assertEquals(correct,person);
		}
	}

	@Test
	public void testOrder3() {
		inputSetup(20);
		outputSetup("CFILORAEJNSDKQGPHBMT");
		output = josephus.order(input, 3);
		for(String person: output) {
			String correct = expected.removeFirst();
			org.junit.Assert.assertEquals(correct,person);
		}
	}

	@Test
	public void testOrder4() {
		inputSetup(20);
		outputSetup("ABCDEFGHIJKLMNOPQRST");
		output = josephus.order(input, 1);
		for(String person: output) {
			String correct = expected.removeFirst();
			org.junit.Assert.assertEquals(correct,person);
		}
	}

}

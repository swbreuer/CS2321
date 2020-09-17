import org.junit.*;
import jug.*;
import net.datastructures.*;
import cs2321.*;

@jug.SuiteName("Test CircularArrayQueue Interface {\"A\",\"B\",\"C\"}")
public class CircularArrayExceptions {

	private CircularArrayQueue TARGET = init();
	private CircularArrayQueue T = init();

	public CircularArrayQueue init() {

		return new CircularArrayQueue<String>(3);
	
	}

	@Before
	public void setup() throws Throwable {
		/* insert "A", "B", "C" */
			T.enqueue("A");
			T.enqueue("B");
			T.enqueue("C");
	}

	@org.junit.Test(timeout=12000)
	@jug.TestName("size() is 3")
	public void Test1() throws Throwable {

		{ boolean thrown = false;
			try {
				T.enqueue("X");
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("add(3, \"X\") throws IndexOutOfBoundsException", t, org.hamcrest.CoreMatchers.instanceOf(IllegalStateException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("add(3, \"X\") throws IndexOutOfBoundsException: Expected Throwable IndexOutOfBoundsException");
			}
		}
	}


}

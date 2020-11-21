import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("Large Item List: 10 items [n[i],i], where n[0] is \"1234\" and n[i]=(721*x(n-1)+51)%10 (as a string)")
public class smallItemListRetrieve {

	private HashMap<String, String> TARGET = init();
	private HashMap<String, String> T = init();

	public HashMap<String, String> init() {
		return new HashMap<String, String>(2);
	}

	@org.junit.Test()
	@jug.TestName("Verifying get(i) for i = \"1\"...\"10000\"")
	public void Test1() throws Throwable {
		Integer [] value2key = new Integer[10];
		Integer [] key2value = new Integer[10];
		int val = 1;
		for(int i=0; i<10; i++)
			{
				value2key[i]=val;
				key2value[val]=i;
				TARGET.put(""+val, ""+i);
				val = (721 * val + 51) % 10;
			}
		
		for(int i=0; i<key2value.length; i++) {
			org.junit.Assert.assertEquals("Verifying get(i) for i = \"1\"...\"10000\"", (Object)(true), (Object)((TARGET.get(""+i).equals(""+key2value[i]))));
		}
	}

}
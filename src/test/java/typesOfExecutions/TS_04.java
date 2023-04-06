package typesOfExecutions;

import org.testng.annotations.Test;

public class TS_04 extends BaseC {

	@Test
	public void test_07() {
		System.out.println("test script 07");
	}
	
	@Test(groups = {"smoke","regression"})
	public void test_08() {
		System.out.println("test script 08");
	}
}

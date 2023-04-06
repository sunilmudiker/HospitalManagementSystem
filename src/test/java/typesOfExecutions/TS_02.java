package typesOfExecutions;

import org.testng.annotations.Test;

public class TS_02 extends BaseC{

	@Test
	public void test_03() {
		System.out.println("test script 03");
	}
	
	@Test(groups = "regression")
	public void test_04() {
		System.out.println("test script 04");
	}
}

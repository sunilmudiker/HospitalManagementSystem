package typesOfExecutions;

import org.testng.annotations.Test;

public class TS_01 extends BaseC {

	@Test(groups = "smoke")
	public void test_01() {
		System.out.println("test script 01");
	}
	
	@Test
	public void test_02() {
		System.out.println("test script 02");
	}
}

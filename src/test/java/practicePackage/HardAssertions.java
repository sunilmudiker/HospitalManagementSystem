package practicePackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertions {

	@Test(retryAnalyzer = com.GenericUtilities.RetryAnalyzerImpClass.class)
	public void assertions() {
		System.out.println("testScripts 1");
		System.out.println("testScripts 2");
		System.out.println("testScripts 3");
		Assert.assertEquals("Hello", "Hell");
		System.out.println("testScripts 4");
	}
	
	@Test
	public void assertions1() {
		
		System.out.println("testScripts 5");
		System.out.println("testScripts 6");
		System.out.println("testScripts 7");
		int a = 10;
		Assert.assertNotNull(a);
		System.out.println("testScripts 8");
	}
	
}

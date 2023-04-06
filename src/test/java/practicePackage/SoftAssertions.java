package practicePackage;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertions {

	@Test
	public void softyAssertions() {
		SoftAssert sf = new SoftAssert();
		System.out.println("testScripts 1");
		System.out.println("testScripts 2");
		System.out.println("testScripts 3");
		sf.assertEquals("Hello", "Helo");
		System.out.println("testScripts 4");
		sf.assertAll();
		System.out.println("testSciptsssss");
	}
	
	@Test
	public void soffyAssertions() {
		SoftAssert sf = new SoftAssert();
		System.out.println("testScripts 5");
		System.out.println("testScripts 6");
		System.out.println("testScripts 7");
		sf.assertEquals("Hello", "Hello");
		System.out.println("testScripts 8");
		sf.assertAll();
	}
}

package practicePackage;

import org.testng.annotations.Test;

public class TestngClass1 {
	
	@Test(dependsOnMethods = "delete")
	public void create() {
		System.out.println("create data");
	}
	
	@Test(priority = 0)
	public void edit() {
		System.out.println("edit data");
	}
	
	@Test
	public void delete() {
		System.out.println("delete data");
	}

}

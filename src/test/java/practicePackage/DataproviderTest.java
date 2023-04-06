package practicePackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataproviderTest {

	@Test(dataProvider = "createData")
	public void readData(String from , String to) {
		System.out.println("From : "+from+"\t"+"To : "+to);
	}
	
	@DataProvider
	public Object[][] createData() {
		Object obj[][] = new Object[3][2];
		
		obj[0][0] = "Banglore";
		obj[0][1] = "Hydrabad";
		
		obj[1][0] = "Mumbai";
		obj[1][1] = "Delhi";
		
		obj[2][0] = "Pune";
		obj[2][1] = "Kolkata";
		
		return obj;
	}
}

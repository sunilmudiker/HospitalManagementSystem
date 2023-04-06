package practicePackage;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider
	public Object[][] createData() {
		Object obj[][] = new Object[2][3];
		obj[0][0] = "Bangalore";
		obj[0][1] = "Mangalore";
		obj[0][2] = "20 seats";
		
		obj[1][0] = "Bangalore";
		obj[1][1] = "Pune";
		obj[1][2] = "30 seats";
		
		return obj;
		
	}
}

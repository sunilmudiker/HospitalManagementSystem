package practicePackage;

import org.testng.annotations.Test;

public class DataProviderReadClass {

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "createData")
	public void readData(String from, String to, String bookedSeats) {
		System.out.println("From : "+from+"\t"+" to : "+"\t"+" BookedSeats : "+bookedSeats);
	}
}

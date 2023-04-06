package practicePackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.GenericUtilities.ExcelUtilities;

public class DataProviderUsingGenericLibraries {

	@Test(dataProvider = "readData")
	public void printData(String from, String to) {
		System.out.println("From : "+from+"\t"+"To : "+to);
	}
	
	@DataProvider
	public Object[][] readData() {
		ExcelUtilities excelUtil = new ExcelUtilities();
		excelUtil.initializeExcelFile();
		Object[][] value = excelUtil.readSetOfData("dataProvider");
		return value;
	}
}

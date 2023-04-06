package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderFromExcel {

	@Test(dataProvider = "readData")
	public void displayData(String from , String to) {
		System.out.println("From : "+from+"\t"+"To : "+to);
	}
	
	@DataProvider
	public Object[][] readData() {
		Object obj[][] = null;
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\practicePackageResources\\dataProvider.xlsx");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Sheet sh = wb.getSheet("Sheet1");
		obj =new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		
		for(int i=1; i<=sh.getLastRowNum();i++) {
			
			for(int j=0 ; j<sh.getRow(i).getLastCellNum();j++) {
				obj[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
}

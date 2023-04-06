package practicePackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {
	public static void writeData() throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet("Sheet1").createRow(2).createCell(0).setCellValue("qspider");
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");
		wb.write(fos);
		wb.close();
	}

	public static void writeMultipleData() throws Throwable {
		ArrayList<String> org = new ArrayList<String>();
		org.add("pyspider");
		org.add("tyspider");
		ArrayList<String> loc = new ArrayList<String>();
		loc.add("hydrabad");
		loc.add("delhi");
		Workbook wb = null;

		for (int i = 0; i < org.size(); i++) {
			FileInputStream fis = new FileInputStream(
					".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");
			 wb= WorkbookFactory.create(fis);
			FileOutputStream fos = new FileOutputStream(
					".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");

			wb.getSheet("Sheet1").createRow(i + 2).createCell(0).setCellValue(org.get(i));
			wb.write(fos);
			wb.close();
			fis = new FileInputStream(
					".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");
			 wb = WorkbookFactory.create(fis);
			fos = new FileOutputStream(
					".\\src\\test\\resources\\practicePackageResources\\practice.xlsx");

			wb.getSheet("Sheet1").getRow(i + 2).createCell(1).setCellValue(loc.get(i));
			wb.write(fos);
			wb.close();
			
		}
		
		

	}

	public static void main(String[] args) throws Throwable {
		writeMultipleData();
	}
}

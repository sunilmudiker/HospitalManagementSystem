package com.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class has all the reusable methods of excel file.
 * @author sunil
 *
 */
public class ExcelUtilities {
	
	Sheet sh;
	Workbook wb;
	/**
	 * This method is used to initialize excel.
	 * @param sheetName
	 */
	public void initializeExcelFile() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(IPathConstants.EXCELPATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This method is used to read single data from excel.
	 * @param rownum
	 * @param cellnum
	 * @return
	 */
	public String readSingleData(int rownum, int cellnum ,String sheetName) {
		sh = wb.getSheet(sheetName);
		String value = sh.getRow(rownum).getCell(cellnum).getStringCellValue();
		return value;
	}
	/**
	 * This method is used to write singel data into excel.
	 * @param rownum
	 * @param cellnum
	 * @param value
	 */
	public void writeSingleData(int rownum, int cellnum, String value,String sheetName ) {
		sh = wb.getSheet(sheetName);
		sh.createRow(rownum).createCell(cellnum).setCellValue(value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(IPathConstants.EXCELPATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to read multiple data from excel.
	 * @param cellnumForKey
	 * @param cellnumForValue
	 * @return
	 */
	public Map<String, String> readMultipleData (int cellnumForKey, int cellnumForValue,String sheetName) {
		sh = wb.getSheet(sheetName);
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0; i<=sh.getLastRowNum();i++) {
			String key = sh.getRow(i).getCell(cellnumForKey).getStringCellValue();
			String value = sh.getRow(i).getCell(cellnumForValue).getStringCellValue();
			map.put(key, value);
			
		}
		return map;
	}
	/**
	 * This method is used to close excel file.
	 */
	public void closeWorkbook() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to read set of data from the excel sheet.
	 * @return
	 */
	public Object[][] readSetOfData(String sheetName) {
		sh = wb.getSheet(sheetName);
		int rowNum = sh.getLastRowNum()+1;
		System.out.println(rowNum);
		int cellNum = sh.getRow(0).getLastCellNum();
		System.out.println(cellNum);
		Object [][] obj = new Object[rowNum][cellNum];
		
		for(int i=0; i<rowNum ; i++) {
			for(int j=0 ; j<cellNum ; j++) {
				obj[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return obj;
	}
}

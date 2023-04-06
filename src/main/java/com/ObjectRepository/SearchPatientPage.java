package com.ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to search patient page.
 * @author sunil
 *
 */
public class SearchPatientPage {

	//Declaration
	@FindBy(xpath = "//input[@id='searchdata']") private WebElement searchField;
	@FindBy(xpath = "//button[@id='submit']") private WebElement submitButton;
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']") private List<WebElement> patients;
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr/td[@class='hidden-xs']/following-sibling::td[last()]/a[contains(@href,'viewid')]") private WebElement viewButton;
	
	//Initialization
	public SearchPatientPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to search a patient.
	 * @param patientFullName
	 */
	public void searchPatient(String patientFullName) {
		searchField.sendKeys(patientFullName);
		submitButton.click();
		List<WebElement> patientNames = patients;
		for(int i=0; i<patientNames.size();i++) {
			String patientName = patientNames.get(i).getText();
			if(patientName.equalsIgnoreCase(patientFullName)) {
				viewButton.click();
				break;
			}	
		}
	}
	
}

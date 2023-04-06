package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to patient details page.
 * @author sunil
 *
 */
public class PatientDetailsPage {

	//Declaration
	@FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr[2]/td[1]") private WebElement patientName;
	
	//Initialization
	public PatientDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used tp get the patient name.
	 * @return
	 */
	public String getPatientName() {
		return patientName.getText();
	}
}

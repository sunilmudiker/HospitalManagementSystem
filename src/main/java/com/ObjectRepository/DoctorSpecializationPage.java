package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to doctor specialization page
 * @author sunil
 *
 */
public class DoctorSpecializationPage {

	//Declaration
	@FindBy(xpath = "//input[@name='doctorspecilization']") private WebElement doctorSpecialization;
	@FindBy(xpath = "//button[@name='submit']") private WebElement submitButton;
	
	//Initialization
	public DoctorSpecializationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to add doctor specialization.
	 * @param doctorSpecialization
	 */
	public void addDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization.sendKeys(doctorSpecialization);
		submitButton.click();
	}
}

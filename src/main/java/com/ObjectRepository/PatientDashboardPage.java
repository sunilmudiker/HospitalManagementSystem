package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*
 * This pom page consists of all the webElements and respective business libraries related to patient dashboard page
 */
public class PatientDashboardPage {
	
	//Declaration
	@FindBy(xpath = "//span[normalize-space(text())='Book Appointment']") private WebElement bookAppointment;
	@FindBy(xpath = "//span[@class='username']") private WebElement patientProfile;
	@FindBy(xpath = "//a[contains(.,'Log Out')]") private WebElement logoutButton;
	@FindBy(xpath = "//span[normalize-space(text())='Medical History']") private WebElement medicalHistoryTab;
	
	//Initialization
	public PatientDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to click book appointment link
	 */
	public void clickBookAppointment() {
		bookAppointment.click();
	}
	/**
	 * This method is used to logout as patient.s
	 */
	public void logoutAsPatient() {
		patientProfile.click();
		logoutButton.click();
	}
	/**
	 * This method is used to click medical history tab.
	 */
	public void clickMedicalHistoryTab() {
		medicalHistoryTab.click();
	}
}	

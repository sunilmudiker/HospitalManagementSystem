package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all webElements and respective business libraries related to doctor appointment page.
 * @author sunil
 *
 */
public class DoctorAppointmentPage {

	//Declaration
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr[last()]/td[last()]") private WebElement cancelAppointmentButton;
	@FindBy(xpath = "//p[@style='color:red;']") private WebElement confirmationMessage;
	@FindBy(xpath = "//table[@id='sample-table-1']/tbody/tr[last()]/td[@class='hidden-xs']")private WebElement patientName;
	
	//Initialization
	public DoctorAppointmentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * this method is used to cancel appointment.
	 */
	public void cancelAppointment() {
		cancelAppointmentButton.click();
	}
	/**
	 * This method is used to get patient name.
	 * @return
	 */
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}
	
	public String getPatientName() {
		return patientName.getText();
	}
	
}

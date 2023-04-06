package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This pom page consists of all WebElements and respective business libraries related to doctor dashboard page.
 * @author sunil
 *
 */
public class DoctorDashboardPage {

	//Declaration
	@FindBy(xpath = "//a[contains(.,'View Appointment History')]") private WebElement myAppointments;
	@FindBy(xpath = "//span[@class='username']") private WebElement doctorProfile;
	@FindBy(xpath = "//a[contains(.,'Log Out')]") private WebElement logoutButton;
	@FindBy(xpath = "//span[normalize-space( text())='Search']") private WebElement searchButton;

	
	
	//Initialization
	public DoctorDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to click my appointments link.
	 */
	
	public void clickMyAppointments() {
		myAppointments.click();
	}
	/**
	 * This method is used to logout as doctor.
	 */
	public void logoutAsDoctor() {
		doctorProfile.click();
		logoutButton.click();
	}
	/**
	 * This method is used to click search tab
	 */
	public void clickSearchTab() {
		searchButton.click();
	}
	
}	

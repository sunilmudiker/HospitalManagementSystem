package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtilities;
/*
 * This pom page consists of all webElements and business libraries related to adminDashBoardPage.
 */
public class AdminDashboardPage {

	//Declaration 
	@FindBy(xpath = "//span[@class='username']") private WebElement adminProfile;
	@FindBy(xpath = "//a[contains(.,'Log Out')]") private WebElement logoutButton;
	@FindBy(xpath = "//div[@class='item-inner']/span[contains(.,'Doctors')]") private WebElement doctorsTab;
	@FindBy(xpath = "//span[contains(.,'Add')]")private WebElement addDoctor;
	@FindBy(xpath = "//p[@class='links cl-effect-1']/a[@href='unread-queries.php']") private WebElement unreadQueries;
	@FindBy(xpath = "//div[@class='item-inner']/span[contains(.,'Doctors')]") private WebElement doctorTab;
	@FindBy(xpath = "//span[normalize-space(text())='Doctor Specialization']") private WebElement doctorSpecialization;
	@FindBy(xpath = "//span[normalize-space(text())='Users']") private WebElement users;
	@FindBy(xpath = "//span[normalize-space(text())='Manage Users']") private WebElement manageUsers;
	
	
	//Initialization
	public AdminDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to logout as admin.
	 */
	public void logout() {
		this.adminProfile.click();
		this.logoutButton.click();
	}
	/*
	 * This method is used to click add doctor.
	 */
	public void clickAddDoctor(WebDriverUtilities wbUtil) {
		doctorsTab.click();
		wbUtil.waitTillVisibilityOfElement(addDoctor);
		addDoctor.click();
	}
	
	public void selectAddDoctor() {
		addDoctor.click();
	}
	/**
	 * This method is used to click new queries link.
	 */
	public void clickNewQueries() {
		unreadQueries.click();
	}
	/**
	 * This method is used to click doctor tab
	 */
	public void clickDoctorTab() {
		doctorTab.click();
	}
	/**
	 * This method is used to click doctor specialization
	 */
	public void clickDoctorSpecialization() {
		doctorSpecialization.click();
	}
	
	public void clickUsers() {
		users.click();
	}
	
	public void clickManageUsers() {
		manageUsers.click();
	}
	
}

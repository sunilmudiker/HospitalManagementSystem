package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.JavaUtilities;
import com.GenericUtilities.WebDriverUtilities;
/*
 * This pom page consists of all webElements and respective business libraries related to add doctor page.
 */
public class AddDoctorPage {
	
	private String doctName;
	private String docEmail;
	//Declaration
	@FindBy(xpath = "//select[@name='Doctorspecialization']") private WebElement doctorSpecialization;
	@FindBy(name = "docname") private WebElement doctorName;
	@FindBy(name = "clinicaddress") private WebElement clinicAddress;
	@FindBy(name = "docfees") private WebElement consultancyFee;
	@FindBy(name = "doccontact") private WebElement contactNumber;
	@FindBy(name = "docemail") private WebElement emailId;
	@FindBy(name = "npass") private WebElement password;
	@FindBy(name = "cfpass") private WebElement confirmPassword;
	@FindBy(id = "submit") private WebElement submitButton;
	
	
	//Initialization
	public AddDoctorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/*
	 * This method is used to add a new doctor.
	 */
	public void addDoctor(WebDriverUtilities wbUtil,String doctorSpecialization, JavaUtilities jUtil,String doctorName,String clinicAddress,String consultancyFee, String email,String password,String confirmPassword) {
		wbUtil.selectDropDown(this.doctorSpecialization, doctorSpecialization);
		doctName = doctorName+jUtil.getRandomNum(5000);
		this.doctorName.sendKeys(doctName);
		this.clinicAddress.sendKeys(clinicAddress);
		this.consultancyFee.sendKeys(consultancyFee);
		this.contactNumber.sendKeys(""+jUtil.getRandomNum(1000000000));
		docEmail = jUtil.getRandomNum(4000)+email; 
		this.emailId.sendKeys(docEmail);
		this.password.sendKeys(password);
		this.confirmPassword.sendKeys(confirmPassword);
		this.submitButton.click();
	}
	
	public String getdoctName() {
		return doctName;
	}
	
	public String getdocEmail() {
		return docEmail;
	}
	
	
}

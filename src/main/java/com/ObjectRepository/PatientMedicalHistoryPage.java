package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtilities;
/**
 * This pom page consists of all the WebElements and respective business libraries related to patient medical history page.
 * @author sunil
 *
 */
public class PatientMedicalHistoryPage {

	//Declaration
	@FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr[2]/td[1]") private WebElement patientName;
	@FindBy(xpath = "//button[.='Add Medical History']") private WebElement addMedicalHistoryButton;
	@FindBy(xpath = "//div[@class='modal-body']")private WebElement body;
	@FindBy(xpath = "//input[@name='bp']") private WebElement bloodPressure;
	@FindBy(xpath = "//input[@name='bs']") private WebElement bloodSugar;
	@FindBy(xpath = "//input[@name='weight']") private WebElement weight;
	@FindBy(xpath = "//input[@name='temp']") private WebElement temperature;
	@FindBy(xpath = "//textarea[@name='pres']") private WebElement prescription;
	@FindBy(xpath = "//button[@name='submit']") private WebElement submitButton;
	
	//Initialization
	public PatientMedicalHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method is used to get patient name
	 * @return
	 */
	public String getPatientName() {
		return patientName.getText();
	}
	/**
	 * This method is used to add medical history of the patient.
	 * @param wbUtil
	 * @param bloodPressure
	 * @param bloodSugar
	 * @param weight
	 * @param temperature
	 * @param prescription
	 */
	public void addMedicalHistory(WebDriverUtilities wbUtil, String bloodPressure, String bloodSugar,String weight,String temperature,String prescription) {
		addMedicalHistoryButton.click();
		wbUtil.waitTillVisibilityOfElement(body);
		this.bloodPressure.sendKeys(bloodPressure);
		this.bloodSugar.sendKeys(bloodSugar);
		this.weight.sendKeys(weight);
		this.temperature.sendKeys(temperature);
		this.prescription.sendKeys(prescription);
		submitButton.click();		
	}
}

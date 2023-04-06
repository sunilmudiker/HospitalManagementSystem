package BaseClassImplemented;
 
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;

public class VerifySpecializationTest extends BaseClass{

	@Test
	public void verifySpecialization() {

		Map<String, String> map = excelUtil.readMultipleData(0, 1,"verifySpecialization");
		//click on admin login
		homePage.clickAdminLogin();
		//login as admin
		adminLoginPage.login(USERNAME, PASSWORD);
		//click on doctor tab
		adminDashboardPage.clickDoctorTab();
		//click on doctor specialization
		adminDashboardPage.clickDoctorSpecialization();
		//add specialization
		doctorSpecializationPage.addDoctorSpecialization(map.get("doctorspecilization"));
		// click on doctor tab
		adminDashboardPage.clickDoctorTab();
		// click on add doctor
		adminDashboardPage.selectAddDoctor();
		//add doctor
		addDoctorPage.addDoctor(wbUtil, map.get("doctorspecilization"), javaUtil, map.get("docName"), map.get("clinicaddress"), map.get("docfees"), map.get("email"),map.get("npass"), map.get("cfpass"));
		String confirmMsg = wbUtil.getTextOfAlert();
		//verify doctor added or not
		Assert.assertTrue(confirmMsg.contains("Successfully"));
//		if(confirmMsg.contains("Successfully"))
//			System.out.println("doctor added successfully");
//		else
//			System.out.println("doctor not added successfully");
		wbUtil.acceptAlert();
		//logout as admin
		adminDashboardPage.logout();
		//click on patient login
		homePage.clickPatientLogin();
		//login as patient
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		//click on book appointment
		patientDashboardPage.clickBookAppointment();
		//book appointment 
		bookAppointmentPage.bookAppointment(wbUtil, map.get("doctorspecilization"), addDoctorPage.getdoctName(), map.get("month"), map.get("year"), map.get("date"));
		String alertMsg = wbUtil.getTextOfAlert();
		//verify appointment booked successfully
		Assert.assertTrue(alertMsg.contains("successfully booked"));
//		if (alertMsg.contains("successfully booked"))
//			System.out.println("appointment booked successfully");
//		else
//			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		//logout as patient
		patientDashboardPage.logoutAsPatient();
		//click on doctor login
		homePage.clickDoctorLogin();
		//login as doctor
		doctorLoginPage.doctorLogin(addDoctorPage.getdocEmail(), map.get("npass"));
		//click on search tab
		doctorDashboardPage.clickSearchTab();
		//search for particular patient
		searchPatientPage.searchPatient(map.get("patientname"));
		//add medical history of the patient
		patientMedicalHistoryPage.addMedicalHistory(wbUtil, map.get("bp"), map.get("sugar"), map.get("weight"), map.get("temp"), map.get("prescription"));
		String msg = wbUtil.getTextOfAlert();
		//verify medical history added.
		Assert.assertTrue(msg.contains("added"));
//		if(msg.contains("added"))
//			System.out.println("added successfully");
//		else
//			System.out.println("not added ");
		wbUtil.acceptAlert();
		//logout as doctor
		doctorDashboardPage.logoutAsDoctor();
		//click on patient login button
		homePage.clickPatientLogin();
		//login as patient
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		//click on medical history
		patientDashboardPage.clickMedicalHistoryTab();
		//click on view medical history
		medicalHistoryPage.clickViewMedicalHistory(map.get("patientname"));		
	}
}

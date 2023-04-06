package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;

public class SearchPatientTest extends BaseClass {

	@Test
	public void searchPatient() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "searchPatient");
		// click on doctor login button
		homePage.clickDoctorLogin();
		// login as doctor
		doctorLoginPage.doctorLogin(map.get("username"), map.get("password"));
		// click on search tab
		doctorDashboardPage.clickSearchTab();
		// search the patient
		searchPatientPage.searchPatient(map.get("searchdata"));
		// verify patient details
		String patientName = patientDetailsPage.getPatientName();
		Assert.assertTrue(patientName.equalsIgnoreCase(map.get("searchdata")));
//		if (patientName.equalsIgnoreCase(map.get("searchdata")))
//			System.out.println("patient details displayed");
//		else
//			System.out.println("patient details not displayed");
		// logout as doctor
		doctorDashboardPage.logoutAsDoctor();
	}
}

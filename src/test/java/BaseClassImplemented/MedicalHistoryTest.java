package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;

public class MedicalHistoryTest extends BaseClass {
	@Test
	public void medicalHistory() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "medicalHistory");
		// click patient login button
		homePage.clickPatientLogin();
		// login as patient
		patientLoginPage.loginAsPatient(map.get("username"), map.get("password"));
		// click medical history tab.
		patientDashboardPage.clickMedicalHistoryTab();
		// click on view medical history of the patient.
		medicalHistoryPage.clickViewMedicalHistory(map.get("patientname"));
		// verify patient medical history
		String verifyPatientName = patientMedicalHistoryPage.getPatientName();
		Assert.assertTrue(verifyPatientName.equalsIgnoreCase(map.get("patientname")));
//		if (verifyPatientName.equalsIgnoreCase(map.get("patientname")))
//			System.out.println("patient medical history displayed");
//		else
//			System.out.println("patient medical history not displayed");
		// logout as patient
		patientDashboardPage.logoutAsPatient();
	}
}

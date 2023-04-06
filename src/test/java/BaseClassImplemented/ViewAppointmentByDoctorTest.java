package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;

public class ViewAppointmentByDoctorTest extends BaseClass {

	@Test
	public void viewAppointment() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "viewAppointment");
		// click on patient login button
		homePage.clickPatientLogin();
		// login as patient
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		// click on book appointment
		patientDashboardPage.clickBookAppointment();
		// book an appointment with doctor.
		bookAppointmentPage.bookAppointment(wbUtil, map.get("doctorspecialization"), map.get("doctorname"),
				map.get("month"), map.get("year"), map.get("date"));
		String alertMsg = wbUtil.getTextOfAlert();
		Assert.assertTrue(alertMsg.contains("successfully booked"));
//		if (alertMsg.contains("successfully booked"))
//			System.out.println("appointment booked successfully");
//		else
//			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		// logout as patient
		patientDashboardPage.logoutAsPatient();
		// click on doctor login
		homePage.clickDoctorLogin();
		// login as doctor
		doctorLoginPage.doctorLogin(map.get("doctorusername"), map.get("doctorpassword"));
		// click my appointments
		doctorDashboardPage.clickMyAppointments();
		// verify appointments
		String patientName = doctorAppointmentPage.getPatientName();
		Assert.assertTrue(patientName.contains(map.get("patientName")));
//		if (patientName.contains(map.get("patientName")))
//			System.out.println("appointment details displayed successfully");
//		else
//			System.out.println("appointment details not displayed successfully");
		// logout as doctor
		doctorDashboardPage.logoutAsDoctor();
	}
}

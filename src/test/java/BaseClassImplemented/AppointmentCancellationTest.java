package BaseClassImplemented;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.GenericUtilities.BaseClass;

public class AppointmentCancellationTest extends BaseClass {

	@Test
	public void cancelAppointment() {
		Map<String, String> map = excelUtil.readMultipleData(0, 1, "appointmentCancel");
		// click patient login button
		homePage.clickPatientLogin();
		// login as patient
		patientLoginPage.loginAsPatient(map.get("patientusername"), map.get("patientpassword"));
		// book appointment
		patientDashboardPage.clickBookAppointment();
		bookAppointmentPage.bookAppointment(wbUtil, map.get("doctorspecialization"), map.get("doctorname"),
				map.get("month"), map.get("year"), map.get("date"));
		String alertMsg = wbUtil.getTextOfAlert();
		Assert.assertTrue(alertMsg.contains("successfully booked"));
//		if (alertMsg.contains("successfully booked"))
//			System.out.println("appointment booked successfully");
//		else
//			System.out.println("appointment not booked successfully");
		wbUtil.acceptAlert();
		// logout as patient.
		patientDashboardPage.logoutAsPatient();
		// click doctor login button
		homePage.clickDoctorLogin();
		// login as doctor.
		doctorLoginPage.doctorLogin(map.get("doctorusername"), map.get("doctorpassword"));
		// Canceling appointment.
		doctorDashboardPage.clickMyAppointments();
		doctorAppointmentPage.cancelAppointment();
		wbUtil.acceptAlert();
		String cancelMsg = doctorAppointmentPage.getConfirmationMessage();
		Assert.assertTrue(cancelMsg.contains("canceled"));
//		if (cancelMsg.contains("canceled"))
//			System.out.println("appointment cancelled successfully");
//		else
//			System.out.println("appointment not cancelled successfully");
		// logout as doctor
		doctorDashboardPage.logoutAsDoctor();
	}

}

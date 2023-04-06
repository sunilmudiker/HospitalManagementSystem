package practicePackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImp implements ITestListener {
	public ExtentReports report; public ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		Reporter.log("testScript execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName);
		Reporter.log(methodName+ " has passed!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//Screenshot Implementation
		test.addScreenCaptureFromPath("path-> dst.getAbsolutePath()");
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(methodName+" has failed!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, result.getThrowable());
		test.log(Status.SKIP, methodName);
		Reporter.log(methodName+" has skipped!");
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReport/repo.html");	
		htmlReport.config().setDocumentTitle("HospitalManagementSystem");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("HMS");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("OS", "WINDOWS10");
		report.setSystemInfo("Reporter", "Sunil M R");
		report.setSystemInfo("URL", "http://rmgtestingserver/domain/Hospital_Management_System/");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}

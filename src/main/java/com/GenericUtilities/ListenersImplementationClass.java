package com.GenericUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementationClass implements ITestListener {
public 	ExtentReports reports;
public 	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = reports.createTest(methodName);
		Reporter.log(methodName+" testscript execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+" passed");
		Reporter.log(methodName+"  executed successfully!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
//		EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);
//		File src = edriver.getScreenshotAs(OutputType.FILE);
//		File dest  = new File(".\\screenshot\\sc.png");
//		try {
//			FileUtils.copyFile(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		test.addScreenCaptureFromPath(dest.getAbsolutePath());
		test.addScreenCaptureFromPath(new WebDriverUtilities().getScreenshotOfPage(BaseClass.sdriver, methodName));
//		test.log(Status.FAIL, methodName + " failed!!");
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(" execution failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+" skipped");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(" execution skipped");
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReport/report.html");
		htmlReport.config().setDocumentTitle("HospitalManagementSystem");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("HMS");
		
		reports = new ExtentReports();
		reports.attachReporter(htmlReport);
		reports.setSystemInfo("Browser", "chrome");
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Reporter", "sunil");
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}

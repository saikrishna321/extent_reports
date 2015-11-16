package com.test.basetest;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.factory.ComplexReportFactory;


public class BaseTest extends TestListenerAdapter {

	WebDriver driver;
	public ExtentTest testReporter;
	private String filenameOfReport = System.getProperty("user.dir") + "/index.html";

	@BeforeMethod
	public void beforeMethod(Method caller) {
		ComplexReportFactory.getTest(caller.getName(), "This is a simple test from complex factory");
	}

	@AfterMethod
	public void afterMethod(Method caller) {
		ComplexReportFactory.closeTest(caller.getName());

	}

	/*
	 * After suite will be responsible to close the report properly at the end
	 * You an have another afterSuite as well in the derived class and this one
	 * will be called in the end making it the last method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() {
		ComplexReportFactory.closeReport();
	}
    
	@Override
	public void onTestSuccess(ITestResult tr) {
		System.out.println(((ITestResult) tr).getMethod().getMethodName() + "--Test method success\n");
//		ExtentReports extent = createReport();
//		ExtentTest test = extent.startTest(tr.getMethod().getMethodName(), "-");
		testReporter = ComplexReportFactory.getTest();
		testReporter.log(LogStatus.PASS, "-");
		//flushReports(extent, test);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println(tr.getMethod().getMethodName() + "--Test method failed\n");
		//ExtentReports extent = createReport();
		//ExtentTest test = extent.startTest(tr.getMethod().getMethodName(), "Test failed, click here for further details");
		//ExtentTest testReporter = ComplexReportFactory.getTest();
		// step log
		testReporter =  ComplexReportFactory.getTest();
		testReporter.log(LogStatus.FAIL, "Failure trace Selenium: " + tr.toString());
		testReporter.log(LogStatus.INFO, "Snapshot below: " + testReporter.addScreenCapture("/Users/saikrisv/Downloads/addtext_com_MjA1MjUxNDkyODA.png"));
		//flushReports(extent, test);
	
	}

	private ExtentReports createReport() {
		ExtentReports extent = new ExtentReports(filenameOfReport, false);
		extent.config().reportName("My first extentReport report");
		extent.config().reportHeadline("See my awesome passed tests!");
		return extent;
	}

	private void flushReports(ExtentReports extent, ExtentTest test) {
		// ending test
		extent.endTest(test);
		// writing everything to document
		extent.flush();
	}

	private String getDateTime() {
		Date date = Calendar.getInstance().getTime();

		// Display a date in day, month, year format
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
		String today = formatter.format(date);
		return today;
	}
}

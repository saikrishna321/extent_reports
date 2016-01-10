package com.test.sample;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest extends TestListenerAdapter {
	public ExtentTest testReporter;

	@BeforeMethod
	public void beforeMethod(Method caller) {
		ExtentTestManager.startTest(caller.getName(), "This is a simple test.")
				.assignCategory(Thread.currentThread().getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.isSuccess()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "<pre>" + getStackTrace(result.getThrowable()) + "</pre>");
			System.out.println("******" + result.getThrowable().toString());

		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
		}

		ExtentTestManager.endTest();
		// ExtentManager.getInstance().flush();
	}

	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	@AfterSuite
	public void afterSuite() {
		ExtentManager.getInstance().flush();
	}
}

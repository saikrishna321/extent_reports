package com.test.sample;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports instance;
	
	public static synchronized ExtentReports getInstance() {
		if (instance == null) {
			System.out.println(System.getProperty("user.dir"));
			instance = new ExtentReports(System.getProperty("user.dir") + "/Extent.html");
		}
		
		return instance;
	}
}

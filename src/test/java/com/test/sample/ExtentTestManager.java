package com.test.sample;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.Properties;


public class ExtentTestManager {  // new
    public static Properties prop = new Properties();

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public static ExtentReports extent = ExtentManager.getExtent();

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description,
        String deviceId) {
        ExtentTest test = extent.createTest(name, description);

        if (deviceId != null)
            test.assignCategory(deviceId);
            extentTest.set(test);
        return getTest();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        return createTest(name, description, String.valueOf(Thread.currentThread().getId()));
    }

    public synchronized static ExtentTest createTest(String name) {
        return createTest(name, "Sample Test");
    }
}

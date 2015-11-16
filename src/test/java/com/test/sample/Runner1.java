package com.test.sample;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Runner1 {
	public static void main(String[] args) {
      	List<String> suites = new ArrayList<String>();
      	TestNG testng = new TestNG();
        suites.add("testng.xml");
		testng.setTestSuites(suites);
        testng.addListener("com.test.basetest.BaseTest");
        testng.setSuiteThreadPoolSize(5);
        testng.setOutputDirectory("path to output");
        testng.run();

}
}

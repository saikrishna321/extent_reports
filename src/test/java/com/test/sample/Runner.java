package com.test.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.testng.TestNG;
import org.testng.annotations.Test;

public class Runner {
	@Test
	public void testReports() {
		// TODO Auto-generated method stub
		List<Class> testCases = new ArrayList<Class>();
		testCases.add(Sample2Test.class);
		testCases.add(SampleTest.class);
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		for (final Class testFile : testCases) {
			executorService.submit(new Runnable() {
				public void run() {
					System.out.println("Running test file: " + testFile.getName());
					testRunnerTestNg(testFile);

				}
			});
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// baseTest.convertXmlToJSon();
		System.out.println("ending");
	}
	public static void testRunnerTestNg(Class arg) {
		TestNG test = new TestNG();
		test.setTestClasses(new Class[] { arg });
		test.run();
	}
}

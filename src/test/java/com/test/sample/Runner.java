package com.test.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class Runner {
    List<Thread> threads = new ArrayList<Thread>();

    @Test
    public void testReports() throws InterruptedException {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(System.getProperty("user.dir") + "/testng.xml");
        testng.setTestSuites(suites);
        testng.run();
        System.out.println(testng.hasFailure());
    }
}

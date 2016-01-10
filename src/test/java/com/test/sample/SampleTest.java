package com.test.sample;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

	
	@Test
	public void testApp(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		//testReporter.assignCategory("Smoke");
		AssertJUnit.assertTrue(false);
	}
	
	@Test
	public void testApp1(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		AssertJUnit.assertTrue(true);
	}
	
	@Test
	public void testApp2(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		AssertJUnit.assertTrue(false);
	}
	
	
	@Test
	public void testApp3(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		AssertJUnit.assertTrue(true);
	}
}

package com.test.sample;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.test.basetest.BaseTest;

public class SampleTest extends BaseTest {

	
	@Test
	public void testApp(){
		testReporter.assignCategory("Smoke");
		AssertJUnit.assertTrue(false);
	}
	
	@Test
	public void testApp1(){
		AssertJUnit.assertTrue(true);
	}
	
	@Test
	public void testApp2(){
		AssertJUnit.assertTrue(false);
	}
	
	
	@Test
	public void testApp3(){
		AssertJUnit.assertTrue(true);
	}
}

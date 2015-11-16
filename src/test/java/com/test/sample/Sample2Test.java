package com.test.sample;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.test.basetest.BaseTest;

public class Sample2Test extends BaseTest {

	
	@Test
	public void testApp4(){
		AssertJUnit.assertTrue(false);
	}
	
	@Test
	public void testApp5(){
		AssertJUnit.assertTrue(true);
	}
	
	@Test
	public void testApp6(){
		AssertJUnit.assertTrue(false);
	}
	
	
	@Test
	public void testApp7(){
		AssertJUnit.assertTrue(true);
	}
}

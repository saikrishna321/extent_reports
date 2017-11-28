package com.test.sample;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Sample2Test extends BaseTest {
	@Test
	public void testApp4(){
		
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		AssertJUnit.assertTrue(true);
		
	}
	
	@Test
	public void testApp5(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		AssertJUnit.assertTrue(true);
	}
	
	@Test
	public void testApp6(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		//AssertJUnit.assertTrue(false);
		//AssertJUnit.assertEquals(true, false);
		AssertJUnit.assertEquals("sai", "sai");
		
	}
	
	
	@Test
	public void testApp7(){
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		
		AssertJUnit.assertTrue(true);
	}
}

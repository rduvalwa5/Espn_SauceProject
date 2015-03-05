package com.saucelabs;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestNflPage  extends PageObjects {
	
	public Boolean getPage(WebDriver driver, String url) throws InterruptedException{
		Boolean foundPage = false;
		assertEquals("current url does not equal " + url, url,driver.getCurrentUrl());
		driver.findElement(By.linkText("NFL")).click();
	    for (int second = 0;second<10; second++) {
	    	if(nflTitle.equals(driver.getTitle()))
	    	{
	    		foundPage = true;
	    	}
	    	else{
	    		Thread.sleep(1000);
	    		System.out.println("Page landed " + driver.getCurrentUrl());
	    	}
	    }
	    return foundPage;
	}
	
	public void testPage(WebDriver driver)
	{
	    assertEquals(nflTitle, driver.getTitle());
	    assertEquals(nflUrl,driver.getCurrentUrl());
	}

}

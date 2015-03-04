package com.saucelabs;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;

public class TestHomePage {
	private String homeTitle = "ESPN: The Worldwide Leader In Sports";
	
	public Boolean getPage(WebDriver driver, String url) throws InterruptedException{
		Boolean foundPage = false;
		driver.get(url);
	    for (int second = 0;second<10; second++) {
	    	if (homeTitle.equals(driver.getTitle()))
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
	
	public void testHomePage(WebDriver driver)
	{
	    assertEquals(homeTitle, driver.getTitle());
	}

}

package com.saucelabs;
/*
 * Firefox 35
 * selenium-server-standalone-2.44.0.jar
 * 
 * Note I am still integration Saucelab
 * I am not using Maven which would import dependencies used by Saucelab
 * that will come next.
 * I am using Ant which I wanted to try
 */
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// Maven will do this
//import com.saucelabs.junit.Parallelized;
//import com.saucelabs.junit.ConcurrentParameterized;
//import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class Test_NavEspn {
	  private WebDriver driver;
	  private String baseUrl = "";
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String browserName = "firefox"; //firefox, chrome, internet explorer, safari, opera, iPad, iPhone, android
	  private String osName =  "MAC";          // MAC, WIN8, XP, WINDOWS, ANY, ANDROID
//	  public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("rduvalXXXX", "XXX87483-1bfd-4635-87a6-7b90546396c9");
	  private TestHomePage myHome = new TestHomePage();
	  private TestNflPage myNfl = new TestNflPage();	  
	  @Before
	  public void setUp() throws Exception {
	/* Links on capabilities
	 * https://code.google.com/p/selenium/wiki/DesiredCapabilities
	 * http://www.browserstack.com/automate/capabilities
	 */
		  
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName );
       capabilities.setCapability(CapabilityType.PLATFORM, osName);
       capabilities.setCapability("name", "Test_NavEspn");
       driver = new FirefoxDriver();
       baseUrl = "http://espn.go.com/";
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testEspn() throws Exception {
	    assertTrue("Failed to find Home Page " + baseUrl ,myHome.getPage(driver,baseUrl));
	    myHome.testHomePage(driver);
	    assertTrue(myNfl.getPage(driver, baseUrl));
	    myNfl.testPage(driver);
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	}


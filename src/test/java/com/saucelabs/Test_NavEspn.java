package com.saucelabs;
/*
 * Firefox 35
 * selenium-server-standalone-2.44.0.jar
 * 
 * Note I am still integration Saucelab
 * I am not using Maven which would import dependencies used by Saucelab
 * that will come next.
 * I am using Ant which I wanted to try
 * /Users/rduvalwa2/DevTools/eclipse-luna/workspace/Java/Learning/Espn_SauceProject
 */
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;

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











import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;


@RunWith(ConcurrentParameterized.class)
public class Test_NavEspn implements SauceOnDemandSessionIdProvider {
	  public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("rduvalwa5", "81187483-1bfd-4635-87a6-7b90546396c9");
	  @Rule
	  public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	  private String browser; //Name; // = "firefox"; //firefox, chrome, internet explorer, safari, opera, iPad, iPhone, android
	  private String os; //Name; // =  "ANY"; 
	  private String version; // = "35";
	  private String sessionId;	
	  private WebDriver driver;
	  private String baseUrl = "";
//	  private StringBuffer verificationErrors = new StringBuffer();
         // MAC, WIN8, XP, WINDOWS, ANY, ANDROID
	  private TestHomePage myHome = new TestHomePage();
	  private TestNflPage myNfl = new TestNflPage();	
  

	  public Test_NavEspn(String os, String version, String browser) 
	    {	
		  	super();
	        this.os = os;
	        this.version = version;
	        this.browser = browser;
	    }
	    
      @ConcurrentParameterized.Parameters
        public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 8.1", "11", "internet explorer"});
        browsers.add(new String[]{"OSX 10.8", "6", "safari"});
        browsers.add(new String[]{"OSX 10.10", "8", "safari"});
        browsers.add(new String[]{"Linux", "34", "firefox"});
        return browsers;
    }	    	  

	/* Links on capabilities
	 * https://code.google.com/p/selenium/wiki/DesiredCapabilities
	 * http://www.browserstack.com/automate/capabilities
	 */
/*
	    @Before
	    public void setUp() throws Exception {

	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
	        capabilities.setCapability(CapabilityType.PLATFORM, "mac");
//	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
//	        capabilities.setCapability(CapabilityType.VERSION, "35");
//	        capabilities.setCapability(CapabilityType.PLATFORM,"Windows 8.1");
//	        capabilities.setCapability(CapabilityType.PLATFORM,"Windows 7");
//	        capabilities.setCapability(CapabilityType.PLATFORM,"Linux");	        
	        capabilities.setCapability("name", "EspnTest");
	        driver = new RemoteWebDriver(
	                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
	                capabilities);
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

	    }
*/
	  @Before
	  public void setUp() throws Exception {
		  
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
	        if (version != null) {
	            capabilities.setCapability(CapabilityType.VERSION, version);
	        }
	        capabilities.setCapability(CapabilityType.PLATFORM, os);
	        capabilities.setCapability("name", "Sauce Sample Test");
	        this.driver = new RemoteWebDriver(
	                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
	                capabilities);
	        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
	  }
	  
	  @Test
	  public void testEspn() throws Exception {
		baseUrl = "http://espn.go.com/";
		driver.get(baseUrl);
	    assertTrue("Failed to find Home Page " + baseUrl ,myHome.getPage(driver,baseUrl));
	    myHome.testHomePage(driver);
	    assertTrue(myNfl.getPage(driver, baseUrl));
	    myNfl.testPage(driver);
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	  /**
	     *
	     * @return the value of the Sauce Job id.
	     */
	  
	   public String getSessionId() {
	        return sessionId;
	    }
	}


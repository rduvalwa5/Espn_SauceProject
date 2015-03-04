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
import com.saucelabs.junit.ConcurrentParameterized;

public class Test_NavEspn {
	  private WebDriver driver;
	  private String version = "35";
	  private String baseUrl = "";
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String browserName = "firefox"; //firefox, chrome, internet explorer, safari, opera, iPad, iPhone, android
	  private String osName =  "ANY";          // MAC, WIN8, XP, WINDOWS, ANY, ANDROID
//	  public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("rduvalXXXX", "XXX87483-1bfd-4635-87a6-7b90546396c9");
	  private TestHomePage myHome = new TestHomePage();
	  private TestNflPage myNfl = new TestNflPage();	
	  public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("rduvalwa5", "81187483-1bfd-4635-87a6-7b90546396c9");
	  private String sessionId;	  
	    /**
	     * Constructs a new instance of the test.  The constructor requires three string parameters, which represent the operating
	     * system, version and browser to be used when launching a Sauce VM.  The order of the parameters should be the same
	     * as that of the elements within the {@link #browsersStrings()} method.
	     * @param os
	     * @param version
	     * @param browser
	     * @return 
	     */
	    public void SampleSauceTest(String os, String version, String browser) 
	    {	//super();
	        os = os;
	        version = version;
	        browser = browser;
	    }
	    
/*
 *     @ConcurrentParameterized.Parameters
        public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 8.1", "11", "internet explorer"});
        browsers.add(new String[]{"OSX 10.8", "6", "safari"});
        return browsers;
    }	    
 */
	    
	    
	    
	    
	    /**
	     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the {@link #browser},
	     * {@link #version} and {@link #os} instance variables, and which is configured to run against ondemand.saucelabs.com, using
	     * the username and access key populated by the {@link #authentication} instance.
	     *
	     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
	     */
	    @Before
	    public void setUp() throws Exception {

	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
	        capabilities.setCapability(CapabilityType.VERSION, "35");
//	        capabilities.setCapability(CapabilityType.PLATFORM, "mac");
	        capabilities.setCapability(CapabilityType.PLATFORM,"Windows 8.1");
	        capabilities.setCapability("name", "EspnTest");
	        capabilities.setCapability("name", "EspnTest");
	        driver = new RemoteWebDriver(
	                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
	                capabilities);
	        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

	    }


		/* Links on capabilities
		 * https://code.google.com/p/selenium/wiki/DesiredCapabilities
		 * http://www.browserstack.com/automate/capabilities
		 */

/*	    
	  @Before
	  public void setUp() throws Exception {
	       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName );
       capabilities.setCapability(CapabilityType.PLATFORM, osName);
       capabilities.setCapability("name", "Test_NavEspn");
       driver = new FirefoxDriver();
       baseUrl = "http://espn.go.com/";
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
*/
	  
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


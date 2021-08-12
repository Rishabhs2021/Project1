package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.helpers.SyncActions;
import com.utilities.TimeOuts;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements TimeOuts {
	
	private static final Logger logger = LogManager.getLogger(BaseTest.class.getName());
	
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	@BeforeClass(alwaysRun=true)
	public void initializeBaseSetup(ITestContext context) {
		
//		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().version("91.0.4472.101").setup();
		
		driver = new ChromeDriver();
		context.setAttribute("driver", driver);
		driver.manage().window().maximize();
		(new SyncActions(driver)).setImplicitWait(IMPLICITWAIT);
	}
	
	
	@AfterClass( alwaysRun = true)
	public void CloseBrowser()  {
		if (driver != null) {
			driver.quit();
		}
	}
	
}

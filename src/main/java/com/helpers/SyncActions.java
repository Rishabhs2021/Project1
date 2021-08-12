/*************************************** PURPOSE **********************************

 - This class contains all synchronization methods
*/


package com.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.utilities.TimeOuts;


public class SyncActions implements TimeOuts {
	
	private static final Logger logger = LogManager.getLogger(SyncActions.class.getName());
	private WebDriver driver;
	
	public SyncActions(WebDriver driver) {
		this.driver = driver;
	}
	
	public void nullifyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
	}

	public void setImplicitWait(int waitTimeInSeconds) {
		driver.manage().timeouts().implicitlyWait(waitTimeInSeconds, TimeUnit.SECONDS);
	}
	
	public boolean isElementPresent(By locator, int waitTime) {    	
    	boolean bFlag = false;	
    	nullifyImplicitWait();
    	logger.info("Waiting for presence of element " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 
			logger.info("---- element present -----");
			if(driver.findElement(locator).isDisplayed() || driver.findElement(locator).isEnabled()) {
				bFlag = true;
				logger.info("Element " + locator + " is displayed");
			}
		}catch (NoSuchElementException e) {
			logger.info("Element " + locator + " was not found in DOM in time - "+waitTime+" Seconds - NoSuchElementException");			
		}catch (TimeoutException e) {
			logger.info("Element " + locator + " was not displayed in time - "+waitTime+" Seconds"+" - TimeoutException");
		}catch (Exception e) {
			logger.error("Element " + locator + " is not found - " + e);
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}
	
	public boolean isElementVisible(By locator, int... optionWaitTime) {
		int waitTime =  getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait(); 
		logger.info("Waiting until element " + locator+" is visible");
		try {			
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			setImplicitWait(IMPLICITWAIT); 
			if(driver.findElement((locator)).isDisplayed()) {
				bFlag = true;
				logger.info("Element " + locator + " is displayed");
			}
		}catch (NoSuchElementException e) {	
			logger.info("Element " + locator + " was not displayed in time - "+waitTime+" Seconds - NoSuchElementException");			
		}catch (TimeoutException e){
			logger.info("Element " + locator + " was not displayed in time - "+waitTime+" Seconds - TimeoutException");
		}catch (Exception e) {	
			logger.error("Element " + locator + " was not displayed - " + e);	
		}
		return bFlag;
	}

	public boolean waitUntilElementDisappears(By locator,String friendlyWebElementName,int... optionWaitTime) {   
		int waitTime =  getWaitTime(optionWaitTime);
		boolean isNotVisible = false;
		logger.info("Waiting until " + friendlyWebElementName+" is invisible");
		try {						
			nullifyImplicitWait(); 
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); 	
			isNotVisible = true;    
			logger.info(friendlyWebElementName + " is disappeared");
		}catch(Exception e) {           
			logger.error(friendlyWebElementName + " is not disappearing in time - "+waitTime + " - " + e);			
			Assert.fail(friendlyWebElementName + " is not disappearing in time - "+waitTime );
		}
		setImplicitWait(IMPLICITWAIT); 
		return isNotVisible;		
    }
	
	public void waitForPageToLoad() {
		logger.info("Waiting for page to be loaded...");
		try {
			int waitTime = 0;
			boolean isPageLoadComplete = false;
			do {

				isPageLoadComplete = ((String)((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
				System.out.print(".");
				Thread.sleep(500);
				waitTime++;
				if(waitTime > 500) {
					break;
				}
			}while(!isPageLoadComplete);

			if(!isPageLoadComplete) {	
				logger.error("Unable to load webpage with in default timeout 250 seconds");
				Assert.fail("unable to load webpage with in default timeout 250 seconds");
			}
		}catch(Exception e) {		
			logger.error("Unable to load web page - " + e);
			Assert.fail("unable to load webpage");
		}
	}
	
	public int getWaitTime(int[] optionalWaitArray) {
		if(optionalWaitArray.length<=0) {
			return MEDIUMWAIT;
		}else {
			return optionalWaitArray[0];
		}
	}
	
	public boolean waitUntilClickable(By locator, String friendlyWebElementName,int... optionWaitTime) {    	
		int waitTime =  getWaitTime(optionWaitTime);
		boolean bFlag = false;
    	nullifyImplicitWait();
		try {
			logger.info("Waiting until " + friendlyWebElementName+" is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			 
			if(driver.findElement((locator)).isDisplayed()) {
				bFlag = true;
				logger.info(friendlyWebElementName + " is displayed and is clickable");
			}
		}catch (NoSuchElementException e) {
			logger.error("Unable to find " + friendlyWebElementName +" in DOM in time "+waitTime+" Seconds - NoSuchElementException");
			Assert.fail("Unable to find " + friendlyWebElementName +" in DOM in time "+waitTime+" Seconds - NoSuchElementException");
		}catch (TimeoutException e) {
			logger.error(friendlyWebElementName + "was not displayed in time - "+waitTime+" Seconds - TimeoutException");
			Assert.fail(friendlyWebElementName + " was not displayed in time - "+waitTime+" Seconds - TimeoutException");
		}catch (Exception e) {
			logger.error(friendlyWebElementName + "was not displayed - " + e);
			Assert.fail(friendlyWebElementName + " was not displayed");
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}
	
    	
}
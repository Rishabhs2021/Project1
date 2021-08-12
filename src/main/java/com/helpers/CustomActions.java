/*************************************** PURPOSE **********************************

 - This class contains all UserAction methods
 */

package com.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class CustomActions extends SyncActions {

	//Local WebDriver instance
	WebDriver driver;
	private static final Logger logger = LogManager.getLogger(CustomActions.class.getName());

	//Constructor to initialize the local WebDriver variable with the WebDriver variable that,
	//has been passed from each PageParts Java class
	public CustomActions(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public void customClick(By locator, String friendlyWebElementName,int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime =  getWaitTime(optionWaitTime);
			if(waitUntilClickable(locator, friendlyWebElementName,waitTime)) {
				//scrollIntoElementView(locator,friendlyWebElementName);//Go Quo
				WebElement element = driver.findElement(locator);
				element.click();		
				logger.info("Clicked on the  " + friendlyWebElementName);
			}else {
				logger.error(friendlyWebElementName + " is not clickable in time - "+waitTime+" Seconds");
				Assert.fail( friendlyWebElementName + " is not clickable in time - "+waitTime+" Seconds");
			}
		}catch(StaleElementReferenceException e) {
			logger.error(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
			Assert.fail(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
		}catch (NoSuchElementException e) {
			logger.error(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException");	
			Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException");
		}catch(Exception e) {
			logger.error( friendlyWebElementName + " was not clickable" + " - " + e);		
			Assert.fail(friendlyWebElementName + " was not found on the web page");
		}
	}


	public void customActionsClick(By locator,String friendlyWebElementName,int waitTime) {
		
		try {
			if(isElementVisible(locator, waitTime)) {
				WebElement element=driver.findElement(locator);
				Actions builder = new Actions(driver);
				builder.moveToElement(element).click().build().perform();
				logger.info("Clicked on " + friendlyWebElementName);
			}else {     
				logger.error(friendlyWebElementName +" was not visible to click in time - "+waitTime+" Seconds");
				Assert.fail(friendlyWebElementName +" was not visible to click in time - "+waitTime+" Seconds");
			}
		}catch(StaleElementReferenceException e) {
			logger.error(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
			Assert.fail(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
		}catch (NoSuchElementException e) {
			logger.error(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException"); 
			Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException");
		}catch(Exception e) {
			logger.error("Unable to click the cursor on " + friendlyWebElementName + " - " + e); 
			Assert.fail(friendlyWebElementName+" was not visible on the web page");
		}
	}

	public void customType(By locator, String text,String friendlyWebElementName, int... optionWaitTime) {
		
		int waitTime=0;
		try {
			waitTime =  getWaitTime(optionWaitTime);
			if(isElementPresent(locator, waitTime)) {
				scrollIntoElementView(locator,friendlyWebElementName);
				WebElement element=driver.findElement(locator);
				element.sendKeys(text);
				logger.info("Entered - '" + text + " into " + friendlyWebElementName);
			}else {
				logger.error("Unable to enter " + text + " in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
				Assert.fail("Unable to enter " + text + " in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
			}
		}catch(StaleElementReferenceException e) {
			logger.error("Text " + text + " to be entered in the   " + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Text " + text + " to be entered in the   " + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
		}catch (NoSuchElementException e) {
			logger.error("Text " + text + " to be entered in the   " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
			Assert.fail("Text " + text + " to be entered in the   " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
		}catch(Exception e) {
			logger.error("Unable to enter '" + text + "' text in   -"+ friendlyWebElementName + " - " + e);
			Assert.fail("Unable to enter '" + text + "' text in  -"+ friendlyWebElementName+" Some Exception");
		}
	}

	public void mouseHover(By locator,String friendlyWebElementName,int waitTime) {
		
		try {
			if(isElementVisible(locator, waitTime)) {
				Actions builder = new Actions(driver);
				WebElement HoverElement = driver.findElement(locator);
				builder.moveToElement(HoverElement).build().perform();
				logger.info("Hovered on " + friendlyWebElementName);
			}else {	    
				logger.error("Element was not visible to hover ");
				Assert.fail(friendlyWebElementName+" was not visible to hover ");
			}
		}catch(StaleElementReferenceException e) {
			logger.error(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
			Assert.fail(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
		}catch (NoSuchElementException e) {
			logger.error(friendlyWebElementName + " was not found in DOM in time - "+waitTime);	
			Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime);
		}catch(Exception e) {
			logger.error("Unable to hover the cursor on " + friendlyWebElementName + " - " + e);	
			Assert.fail("Unable to hover the cursor on " + friendlyWebElementName );
		}
	}

	public String customGetText(By locator,String friendlyWebElementName,int waitTime) {
		String sValue =null;
		try {
			if(isElementPresent(locator, waitTime)) {
				sValue = driver.findElement(locator).getText();
			}else {
				Assert.fail("Unable to find "+ friendlyWebElementName+" in time - "+waitTime);
			}

		}catch(StaleElementReferenceException e) {			
			logger.error(friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
			Assert.fail(friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
		}catch (NoSuchElementException e) {	    	
			logger.error(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");	
			Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");			
		}catch(Exception e) {
			logger.error("Unable to get the text from "+ friendlyWebElementName + " - " + e);
			Assert.fail("Unable to find "+ friendlyWebElementName);
		}		
		return sValue;		
	}

	public void scrollIntoElementView(By locator,String friendlyWebElementName) {
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(locator));
		}catch(StaleElementReferenceException e) {			
			logger.error(friendlyWebElementName +" is not attached to the page document");
			Assert.fail(friendlyWebElementName +" is not attached to the page document");			
		}catch (NoSuchElementException e) {	    	
			logger.error(friendlyWebElementName + " was not found in DOM");	
			Assert.fail(friendlyWebElementName +" was not found in DOM");			
		}catch(Exception e) {
			logger.error("Unable to scroll the page to find "+ friendlyWebElementName + " - " + e);
			Assert.fail("Unable to scroll the page to find "+ friendlyWebElementName);			
		}
	}

	public void deleteAllCookies() {
		
		try {
			driver.manage().deleteAllCookies();
		}catch(Exception e) {
			logger.error("Some exception occured while deleting all cookies, exception message: " + e);
			Assert.fail("Some exception occured while deleting all cookies");
		}
	}

}

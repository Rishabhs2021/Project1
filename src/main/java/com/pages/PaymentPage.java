package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.PaymentPageLocators;

public class PaymentPage extends CustomActions implements PaymentPageLocators {
	
	private static final Logger logger = LogManager.getLogger(PaymentPage.class.getName());
	
	private WebDriver driver;
	
	public PaymentPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public String getSpecificProductUnitPrice(String productName) {
		String text = customGetText(By.xpath(String.format(SPECIFIC_PRODUCT_UNITPRICE, productName)), "Unit price of "+productName+" in cart page", 5);
		logger.info("Unit price of "+productName+" in cart page is " + text);
		return text;
	}
	
	public HomePage navigateToHomePage() {
		customClick(YOUR_LOGO, "Logo in payment page");
		return new HomePage(driver);
	}
	
	public LandingPage signOutOfApplication() {
		customClick(SIGNOUT_BTN, "Sign out button in Home Page");
		return new LandingPage(driver);
	}
}

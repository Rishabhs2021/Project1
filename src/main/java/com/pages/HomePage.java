package com.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.helpers.CustomActions;
import com.pagelocators.HomePageLocators;

public class HomePage extends CustomActions implements HomePageLocators {
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public HomePage verifyHomePage() {
		boolean isYourLogoVisible = isElementVisible(YOUR_LOGO);
		Assert.assertTrue(isYourLogoVisible, "Home page is not displaying");
		return this;
	}
	
	
	public SearchPage searchForItem(String productName) {
		
		customType(SEARCH_FIELD, productName, "Search field in home page");
		customClick(SEARCH_MAGNIFIER, "Search button in home page");
		return new SearchPage(driver);
	}
	
	public LandingPage signOutOfApplication() {
		customClick(SIGNOUT_BTN, "Sign out button in Home Page");
		return new LandingPage(driver);
	}
}

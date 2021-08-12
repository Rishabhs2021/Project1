package com.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.helpers.CustomActions;
import com.pagelocators.LandingPageLocators;

public class LandingPage extends CustomActions implements LandingPageLocators {
	private WebDriver driver;
	
	public LandingPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public LandingPage verifyLandingPage() {
		boolean isYourLogoVisible = isElementVisible(YOUR_LOGO);
		Assert.assertTrue(isYourLogoVisible, "Home page is not displaying");
		return this;
	}
	
	public LoginPage navigateToLoginPage() {
		customClick(LOGIN_BUTTON, "Sign in button in the landing page");
		return new LoginPage(driver);
	}
}

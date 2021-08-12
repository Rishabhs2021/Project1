package com.pages;

import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.LoginPageLocators;

public class LoginPage extends CustomActions implements LoginPageLocators {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public HomePage signInToApplication() {
		customType(EMAIL, "rishabhsaharanqa@gmail.com", "Email field in the login page");
		customType(PASSWORD, "12345", "Password field in the login page");
		customClick(SIGNIN_BTN, "SignIn Button in the login page");
		return new HomePage(driver);
	}
}

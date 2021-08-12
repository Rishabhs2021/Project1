package com.pages;

import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.CheckoutPageLocators;

public class CheckoutPage extends CustomActions implements CheckoutPageLocators {
	
	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
}

package com.pages;

import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.AddressPageLocators;

public class AddressPage extends CustomActions implements AddressPageLocators {
	
	private WebDriver driver;
	
	public AddressPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public ShippingPage proceedToCheckout() {
		customClick(PROCEEDADDRESS, "Proceed to checkout button in cart page");
		return new ShippingPage(driver);
	}
}

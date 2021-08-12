package com.pages;

import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.ShippingPageLocators;

public class ShippingPage extends CustomActions implements ShippingPageLocators {
	
	private WebDriver driver;
	
	public ShippingPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public PaymentPage proceedToCheckout() {
		customClick(CHECKBOX, "Terms and conditions checkbox in shipping page");
		customClick(PROCESSCARRIER, "Proceed to checkout button in shipping page");
		return new PaymentPage(driver);
	}
}

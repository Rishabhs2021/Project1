package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.CartPageLocators;

public class CartPage extends CustomActions implements CartPageLocators {
	
	private static final Logger logger = LogManager.getLogger(CartPage.class.getName());
	
	private WebDriver driver;
	
	
	
	public CartPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public String getSpecificProductUnitPrice(String productName) {
		String text = customGetText(By.xpath(String.format(SPECIFIC_PRODUCT_UNITPRICE, productName)), "Unit price of "+productName+" in cart page", 5);
		logger.info("Unit price of "+productName+" in cart page is " + text);
		return text;
	}
	
	public AddressPage proceedToCheckout() {
		customClick(PROCEED_TO_CHECKOUT_BTN, "Proceed to checkout button in cart page");
		return new AddressPage(driver);
	}
	
	public String getTotalProductsLabelName() {
		return customGetText(TOTAL_PRODUCTS_LABEL, "Total Products Label Name in cart page", 5);
	}
	
	public CartPage increaseProductQty(String productName, int qty) {
		String initialPrice = customGetText(TOTAL_PRODUCTS_PRICE, "Total Products Price in cart page", 5).replaceAll("[$ ]", "");
		System.out.println("initialPrice: " + initialPrice);
		for(int i = 0; i < qty; i++) {
			customClick(By.xpath(String.format(SPECIFIC_PRODUCT_PLUSICON, productName)), "Plus icon in cart page");
			waitUntilElementDisappears(By.xpath(String.format(TOTAL_PRODUCTS_PRICE_CONDITION, initialPrice)), "Total Product Qty in cart page");
		}
		String finalPrice = customGetText(TOTAL_PRODUCTS_PRICE, "Total Products Price in cart page", 5).replaceAll("[$ ]", "");
		System.out.println("finalPrice: " + finalPrice);
		return this;
	}
	
	public double getTotalProductsPrice() {
		String text = customGetText(TOTAL_PRODUCTS_PRICE, "Total Products Price in cart page", 5).replaceAll("[$ ]", "");
		double totalPrice = Double.parseDouble(text);
		System.out.println("Total Products Price in cart page: " + totalPrice);
		return totalPrice;
	}
	
	public double getTotalShippingPrice() {
		String text = customGetText(TOTAL_SHIPPING, "Total Shipping Price in cart page", 5).replaceAll("[$ ]", "");
		double totalPrice = Double.parseDouble(text);
		System.out.println("Total Shipping Price in cart page: " + totalPrice);
		return totalPrice;
	}
	
	public double getTotalCartPrice() {
		String text = customGetText(TOTAL_PRICE, "Total Cart Price in cart page", 5).replaceAll("[$ ]", "");
		double totalPrice = Double.parseDouble(text);
		System.out.println("Total Cart Price in cart page: " + totalPrice);
		return totalPrice;
	}
	
	public LandingPage signOutOfApplication() {
		customClick(SIGNOUT_BTN, "Sign out button in Home Page");
		return new LandingPage(driver);
	}
	
}

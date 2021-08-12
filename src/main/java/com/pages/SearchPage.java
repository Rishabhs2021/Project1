package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.helpers.CustomActions;
import com.pagelocators.SearchPageLocators;

public class SearchPage extends CustomActions implements SearchPageLocators {
	
	private static final Logger logger = LogManager.getLogger(SearchPage.class.getName());
	
	private WebDriver driver;
	
	private String firstProductPrice = null, firstProductName = null;
	
	public SearchPage(WebDriver driver) {		
		super(driver);
		this.driver = driver;
    }
	
	public String getFirstProductPrice() {
		firstProductPrice = customGetText(FIRST_PRODUCT_PRICE, "Product Price of the first product", 5);
		logger.info("First product price: " + firstProductPrice);
		return firstProductPrice;
	}
	
	public String getFirstProductName() {
		firstProductName = customGetText(FIRST_PRODUCT_NAME, "Product Name of the first product", 5);
		logger.info("First product name: " + firstProductName);
		return firstProductName;
	}


	public CartPage addFirstItemToCart() {
		
		mouseHover(FIRST_PRODUCT_NAME, "First Product Name in search results", 5);
		customActionsClick(FIRST_ADD_TO_CART_BTN, "Add to cart button of first item", 5);
		customClick(PROCEED_TO_CHECKOUT_BTN, "Proceed to checkout button in the popup");
		return new CartPage(driver);
	}
}

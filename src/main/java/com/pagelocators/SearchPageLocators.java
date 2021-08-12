package com.pagelocators;

import org.openqa.selenium.By;

public interface SearchPageLocators {
	
	public static final By FIRST_ADD_TO_CART_BTN = By.xpath("//ul[contains(@class,'product_list')]/li[1]//a[@title='Add to cart']");
	public static final By FIRST_PRODUCT_PRICE = By.xpath("//ul[contains(@class,'product_list')]/li[1]//div[contains(@class,'right-block')]//span[contains(@class,'product-price') and not(contains(@class,'old-price'))]");
	public static final By FIRST_PRODUCT_NAME = By.xpath("//ul[contains(@class,'product_list')]/li[1]//div[contains(@class,'right-block')]//a[contains(@class,'product-name')]");
	public static final By PROCEED_TO_CHECKOUT_BTN = By.cssSelector("a[title='Proceed to checkout']");
	
	
}

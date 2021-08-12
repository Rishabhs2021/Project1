package com.pagelocators;

import org.openqa.selenium.By;

public interface PaymentPageLocators {
	
	public static final By YOUR_LOGO = By.id("header_logo");
	public static final String SPECIFIC_PRODUCT_UNITPRICE = "//p[@class='product-name']/a[contains(text(),'%s')]/ancestor::td[1]/following-sibling::td[@class='cart_unit']/span[@class='price']/span[contains(@class,'price') and not(contains(@class,'percent')) and not(contains(@class,'old-price'))]";
	public static final By SIGNOUT_BTN = By.cssSelector("a.logout");
}

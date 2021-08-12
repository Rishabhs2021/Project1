package com.pagelocators;

import org.openqa.selenium.By;

public interface CartPageLocators {
	
	public static final String SPECIFIC_PRODUCT_UNITPRICE = "//p[@class='product-name']/a[contains(text(),'%s')]/ancestor::td[1]/following-sibling::td[@class='cart_unit']/span[@class='price']/span[contains(@class,'price') and not(contains(@class,'percent')) and not(contains(@class,'old-price'))]";
	public static final By CART_TOTAL = By.id("total_price");
	public static final By PROCEED_TO_CHECKOUT_BTN = By.cssSelector("p.cart_navigation a[title='Proceed to checkout']");
	public static final By TOTAL_PRODUCTS_LABEL = By.xpath("//table[@id='cart_summary']/tfoot/tr[1]/td[@class='text-right']");
	public static final By TOTAL_PRODUCTS_PRICE = By.xpath("//table[@id='cart_summary']/tfoot/tr[1]/td[@class='price']");
	public static final By TOTAL_SHIPPING = By.xpath("//table[@id='cart_summary']/tfoot/tr[@class='cart_total_delivery']/td[@class='price']");
	public static final By TOTAL_PRICE = By.xpath("//table[@id='cart_summary']/tfoot/tr[@class='cart_total_price'][2]/td[@class='price']");
	public static final String TOTAL_PRODUCTS_PRICE_CONDITION = "//table[@id='cart_summary']/tfoot/tr[1]/td[@class='price' and not(contains(text(),%s))]";
	public static final String SPECIFIC_PRODUCT_PLUSICON = "//p[@class='product-name']/a[contains(text(),'%s')]/ancestor::td[1]/following-sibling::td[contains(@class,'cart_quantity')]//a[contains(@class,'button-plus')]";
	public static final String TOTAL_PRODUCT_QTY_CONDITION = "//p[@class='product-name']/a[contains(text(),'%s')]/ancestor::td[1]/following-sibling::td[contains(@class,'cart_quantity')]/input[contains(@class,'cart_quantity_input') and @size!='%s']";
	public static final By SIGNOUT_BTN = By.cssSelector("a.logout");
}

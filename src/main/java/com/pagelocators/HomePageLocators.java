package com.pagelocators;

import org.openqa.selenium.By;

public interface HomePageLocators {
	
	public static final By YOUR_LOGO = By.id("header_logo");
	public static final By SEARCH_FIELD = By.id("search_query_top");
	public static final By SEARCH_MAGNIFIER = By.name("submit_search");
	public static final By SIGNOUT_BTN = By.cssSelector("a.logout");
	
}

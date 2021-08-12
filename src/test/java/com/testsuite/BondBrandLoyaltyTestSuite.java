package com.testsuite;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.helpers.SyncActions;
import com.pages.AddressPage;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.LandingPage;
import com.pages.LoginPage;
import com.pages.PaymentPage;
import com.pages.SearchPage;
import com.pages.ShippingPage;

public class BondBrandLoyaltyTestSuite extends BaseTest {
	
	LandingPage landingPage;
	
	@BeforeMethod (alwaysRun=true)
	public void setUp() {
		landingPage = new LandingPage(getDriver());
		getDriver().manage().deleteAllCookies();
		getDriver().get("http://automationpractice.com/index.php");
		(new SyncActions(getDriver())).waitForPageToLoad();
		landingPage.verifyLandingPage();
	}
	
	@DataProvider(name = "GetProductNames")
	public String[][] getProductNames() {
		
		String[][] productNames = {
			{"Blouse"},
			{"Printed Dress"},
			{"Faded Short Sleeve T-shirts"}
		};
		return productNames;
	}
	
	@Test(priority=1)
	public void testSpecificProductPriceValidation() {
		
		LoginPage loginPage = landingPage.navigateToLoginPage();
		HomePage homePage = loginPage.signInToApplication();
		SearchPage searchPage = homePage.searchForItem("Blouse");
		String expProductPrice = searchPage.getFirstProductPrice().replaceAll("$ ", "");
		
		CartPage cartPage = searchPage.addFirstItemToCart();
		String actualProductPrice = cartPage.getSpecificProductUnitPrice("Blouse");
		
		Assert.assertEquals(actualProductPrice, expProductPrice, 
				"Actual Product price '"+actualProductPrice+"' is not equal to expected product price '"+expProductPrice+"' from search results page");
		
		AddressPage addressPage = cartPage.proceedToCheckout();
		ShippingPage shippingPage = addressPage.proceedToCheckout();
		PaymentPage paymentPage = shippingPage.proceedToCheckout();
		
		String finalProductPrice = paymentPage.getSpecificProductUnitPrice("Blouse");
		Assert.assertEquals(finalProductPrice, expProductPrice, 
				"Final Product price '"+finalProductPrice+"' is not equal to expected product price '"+expProductPrice+"' from search results page");
		landingPage = paymentPage.signOutOfApplication();
	}
	
	@Test(priority=2, dataProvider = "GetProductNames")
	public void testMultipleProductsPricesValidation(String productName) {
		
		LoginPage loginPage = landingPage.navigateToLoginPage();
		HomePage homePage = loginPage.signInToApplication();
		SearchPage searchPage = homePage.searchForItem(productName);
		String expProductPrice = searchPage.getFirstProductPrice();
		String fullProductName = searchPage.getFirstProductName().trim();
		
		CartPage cartPage = searchPage.addFirstItemToCart();
		String actualProductPrice = cartPage.getSpecificProductUnitPrice(fullProductName);
		
		Assert.assertEquals(actualProductPrice, expProductPrice, 
				"In Cart Page, Actual Product price '"+actualProductPrice+"' is not equal to expected product price '"+expProductPrice+"' from search results page");
		
		AddressPage addressPage = cartPage.proceedToCheckout();
		ShippingPage shippingPage = addressPage.proceedToCheckout();
		PaymentPage paymentPage = shippingPage.proceedToCheckout();
		
		String finalProductPrice = paymentPage.getSpecificProductUnitPrice(fullProductName);
		Assert.assertEquals(finalProductPrice, expProductPrice, 
				"In Payment Page, Final Product price '"+finalProductPrice+"' is not equal to expected product price '"+expProductPrice+"' from search results page");
		
		landingPage = paymentPage.signOutOfApplication();
		
	}
	
	@Test(priority=3)
	public void testLabelNameInCartPage() {
		
		String expLabelName = "Total Products Price";
		
		LoginPage loginPage = landingPage.navigateToLoginPage();
		HomePage homePage = loginPage.signInToApplication();
		SearchPage searchPage = homePage.searchForItem("Blouse");
		CartPage cartPage = searchPage.addFirstItemToCart();
		String actualLabel = cartPage.getTotalProductsLabelName();
		
		Assert.assertEquals(actualLabel, expLabelName, "In Cart Page, Actual Label '"+actualLabel+"' is not equal to expected label '"+expLabelName+"'");
	}
	
	@Test(priority=4)
	public void testCalculationsInCartPage() {
		
		LoginPage loginPage = landingPage.navigateToLoginPage();
		HomePage homePage = loginPage.signInToApplication();
		SearchPage searchPage = homePage.searchForItem("Blouse");
		CartPage cartPage = searchPage.addFirstItemToCart();
		
		double actTotalProductPrice = cartPage.getTotalProductsPrice();
		double actTotalShippingPrice = cartPage.getTotalShippingPrice();
		double actTotalCartPrice = cartPage.getTotalCartPrice();
		
		double expTotalPrice = actTotalProductPrice + actTotalShippingPrice;
		
		Assert.assertEquals(actTotalCartPrice, expTotalPrice, 
				"In cart page, actual Total Cart price '"+actTotalCartPrice+"' is not equal to expected total cart price '"+expTotalPrice+"'");
		
		landingPage = cartPage.signOutOfApplication();
	}
	
	@AfterMethod
	public void tearDown() {
		
		
	}
	
	
}

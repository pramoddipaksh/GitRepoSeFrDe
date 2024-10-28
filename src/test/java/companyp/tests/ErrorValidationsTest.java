package companyp.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import companyp.TestComponents.BaseTest;
import companyp.TestComponents.Retry;
import companyp.pageobjects.CartPage;
import companyp.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest { // extend parent class BaseTest so that all elements of BaseTest can be
													// used here

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)  
// to retry to execute failed case, how many times mentioned in that class method; mostly for Flaky test this retry is used to execute many times
	public void loginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		landingPage.loginApplication("pramoddipaksh@gmail.com", "P@ssw0r00d"); // calling method with values passed to method def available in class LandingPage
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMesssage());
	}

	@Test
	public void productErrorValidations() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("pramoddshirale@gmail.com", "P@ssw0rd"); // calling method with values passed to method def available in class LandingPage
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage = productCatalogue.goTocartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName); // this single line code handles below star comment lines of code
		Assert.assertTrue(match);	
	}
	
}

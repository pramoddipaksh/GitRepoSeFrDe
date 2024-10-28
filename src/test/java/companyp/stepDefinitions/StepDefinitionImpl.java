package companyp.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import companyp.TestComponents.BaseTest;
import companyp.pageobjects.CartPage;
import companyp.pageobjects.CheckoutPage;
import companyp.pageobjects.ConfirmationPage;
import companyp.pageobjects.LandingPage;
import companyp.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Section 25: Framework Part 8 - Integrating Cucumber Wrapper into Selenium Framework

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
//	this (.+) is used above line to denote its any value catching in this parameter
//	in above bracket, starting "^" and ending "$" is used bcoz its an regular expression 
	public void Logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);

	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goTocartPage();

		Boolean match = cartPage.verifyProductDisplay(productName); // this single line code handles below star comment
																	// lines of code
		Assert.assertTrue(true);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();

	}

	@Then("{string} message displayed on the confirmation page")
	public void message_displayed_on_the_confirmation_page(String string) {
		String confirmedMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase(string));
//		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMesssage());
		driver.close();
	}
	

}



/* Notes:
// there is an chrome extension "tidy gherkin" which will create above methods structure automatically by pasting the code from .feature file
// it will create and shows code structure, methods, arguments. take those add code accordingly to the need


// SubmitOrder.feature class description:
//created a package cucumber and right click + File > given name as PurchaseOrder.feature extension to create feature file
//Section 25: Framework Part 8 - Integrating Cucumber Wrapper into Selenium Framework
*/
package companyp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyp.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {  
// the class 'AbstractComponent' acts as parent class for these page classes; inheriting parent methods to child classes 
// whatever elements, methods declared in this class 'AbstractComponent' are now available to this class 'LandingPage'
	
	WebDriver driver;

//	driver.findElement(By.cssSelector(".totalRow button")).click(); // click on Checkout button
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
// all the products title added from the Cart page
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	
	public CartPage(WebDriver driver) // constructor called from another class 'StandAloneTest' in which its object created
											
	{
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}	
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(product->
		product.getText().equalsIgnoreCase(productName));
		// cssSelector("b") is where actual product name exist in DOM
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);		
	}
	


}

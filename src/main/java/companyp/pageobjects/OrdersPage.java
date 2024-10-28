package companyp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyp.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {  
// the class 'AbstractComponent' acts as parent class for these page classes 
// whatever elements, methods declared in this class 'AbstractComponent' are now available to this class 'LandingPage'
	
	WebDriver driver;

//	driver.findElement(By.cssSelector(".totalRow button")).click(); // click on Checkout button
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
// all the products title added from the Cart page
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProductNames;
//	list of all product names columns from Orders page
	
	
	public OrdersPage(WebDriver driver) // constructor called from another class 'StandAloneTest' in which its object created
											
	{
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}	
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match = orderProductNames.stream().anyMatch(product->
		product.getText().equalsIgnoreCase(productName));
		// cssSelector("b") is where actual product name exist in DOM
		return match;
	}
}

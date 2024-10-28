package companyp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyp.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) // constructor called from another class 'StandAloneTest' in which its object created
											
	{
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'; this need to be mentioned in every child class 
		
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}

//	WebElement userEmail; = driver.findElement(By.id("userEmail")); // this was changed into Page factory as shown below code
	// PageFactory
	@FindBy(id = "userEmail") // for 'FindBy' method, to know where the driver is, it uses constructor having 'initElements' which takes care driver
	WebElement userEmail;


	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //captured path of all products from page and stored in List web element variable 'list'
	// PageFactory
	@FindBy(css = ".mb-3") // used css syntax
	List<WebElement> products; // products has multiple elements hence instead of WebElement single element, used 'List<WebElement>' for multi-elements 

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3"); // page factory only for 'find element'; hence here this way to use element
	
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() { // this return list of products hence 'List<WebElement>'
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->  // used stream() concept here;
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); // if it returns nothing then orElse(null) used
		// cssSelector("b") is where actual product name exist in DOM
		return prod;
	}
	
	public void addProductTocart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		prod.findElement(addTocart).click(); // the :last-of-type in above line gives the last button i.e. Add to Cart 
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
		
	}
	

	
	

}

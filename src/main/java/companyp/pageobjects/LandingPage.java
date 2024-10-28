package companyp.pageobjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyp.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {  
// the class 'AbstractComponent' acts as parent class for these page classes 
// whatever elements, methods declared in this class 'AbstractComponent' are now available to this class 'LandingPage'
	
	WebDriver driver;

	public LandingPage(WebDriver driver) // constructor called from another class 'StandAloneTest' in which its object created
											
	{
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}

//	WebElement userEmail; = driver.findElement(By.id("userEmail")); // this was changed into Page factory as shown below code

	// PageFactory
	@FindBy(id = "userEmail") // for 'FindBy' method, to know where the driver is, it uses constructor having 'initElements' which takes care driver
	WebElement userEmail;

	@FindBy(id = "userPassword")  // press CTRL+Click on '@FindBy' to see syntaxes of all locators
	WebElement userPwd;

	@FindBy(id = "login")
	WebElement loginBtn;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	// Actions on web element done using below way
	public ProductCatalogue loginApplication(String email, String pwd) {
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		loginBtn.click();
		
		// if we know next page which appears then directly create the obj here only
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}
	
	public String getErrorMesssage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {		
		driver.get("https://rahulshettyacademy.com/client");
	}

}

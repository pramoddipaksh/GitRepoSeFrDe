package companyp.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import companyp.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) // constructor called from another class 'StandAloneTest' in which its object created
	
	{
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}
	
//	driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind")
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
//	driver.findElement(By.cssSelector(".action__submit")).click(); // clicks on "Place Order" button  
	@FindBy(css = ".action__submit")
	WebElement submit;
	
//	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); // clicks India option
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName)
	{
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(results);
		selectCountry.click();
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // wait until India option in the drop-down search box displayed
	
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver); // after click on submit button(i.e. place order), the next page opens is confirmation page where order summary seen. hence use this way
		
	}
	
//we will achieve Encapsulation by making the web elements as private and public methods so that only within that class only its accessible. its implementation is hidden to outside classes.	

}

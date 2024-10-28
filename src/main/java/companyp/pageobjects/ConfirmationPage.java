package companyp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyp.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	
	WebDriver driver;
	

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		super(driver); // this created to pass 'driver' to parent class 'AbstractComponent'
		// Initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}
	
//	driver.findElement(By.cssSelector(".hero-primary"))
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getconfirmationMessage()
	{
		
//	String successOrderMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();	/		
		return confirmationMessage.getText();
		
	}
	
	
	
	
}

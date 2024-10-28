package companyp.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



//Section 18: Framework Part 1  - Create Maven Project and Prepare Functional End to end Test

//import companyp.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;



public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		
//		by using below code, all the files related to chromedriver() automatically downloaded.
//		we have mentioned dependencies of "WebDriverManager" in the pom.xml file which downloads  		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//create object for another class 'LandingPage' and pass driver
		// import package containing LandingPage as above imported
//		LandingPage landingPage = new LandingPage(driver); 
		
		driver.findElement(By.id("userEmail")).sendKeys("pramoddipaksh@gmail.com"); // login page-enter username
		driver.findElement(By.id("userPassword")).sendKeys("P@ssw0rd"); // login page-enter password
		driver.findElement(By.id("login")).click(); // login page-click login button
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); //explicit wait used to wait until all products are displayed on the page
				
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //captured path of all products from page and stored in List web element variable 'list'

		WebElement prod = products.stream().filter(product->  // used stream() concept here;
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); // if it returns nothing then orElse(null) used
		// cssSelector("b") is where actual product name exist in DOM
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
		// the :last-of-type in above line gives the last button i.e. Add to Cart 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // explicit wait used to wait until toast message(product added successfully) displayed after add to cart clicked
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // this explicit wait is for "loading" icon appear when add to cart clicked
				
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // click on "Cart" button on top page
		
		// all the products title added from the Cart page
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//the 'anyMatch'-> Returns whether any elements of this stream match the provided predicate
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); // if matched then return true or false
		System.out.println("Cart product matched: " +match); // assert check if anyMatch returning true or not. otherwise test fail 
		Assert.assertTrue(true);
		
		driver.findElement(By.cssSelector(".totalRow button")).click(); // click on Checkout button
		
		// handle country drop-down list and select India from multi_select drop-down list
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // wait until India option in the drop-down search box displayed
		
		// now click on searched option "India" from displayed list
		// cssSelector of 'India' from searched multi select option using "nth-of-type" : .ta-item:nth-of-type(2)
		// xpath of 'India' from searched multi select option: (//button[contains(@class,'ta-item')])[2]
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); // clicks India option
		
		driver.findElement(By.cssSelector(".action__submit")).click(); // clicks on "Place Order" button  
		
		String successOrderMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();	// get confirmed order success msg from Order page	
		System.out.println(successOrderMsg);
		
		Assert.assertTrue(successOrderMsg.equalsIgnoreCase("Thankyou for the order.")); // checking actual message with expected msg using assert(true)
		
		driver.close();
		
	}

}

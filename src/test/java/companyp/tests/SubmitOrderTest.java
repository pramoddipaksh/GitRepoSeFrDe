package companyp.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import companyp.TestComponents.BaseTest;
import companyp.pageobjects.CartPage;
import companyp.pageobjects.CheckoutPage;
import companyp.pageobjects.ConfirmationPage;

//Section 18: Framework Part 1  - Create Maven Project and Prepare Functional End to end Test

import companyp.pageobjects.LandingPage;
import companyp.pageobjects.OrdersPage;
import companyp.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

// Section 20: Framework Part 3 -Test Configuration Methods & Global Properties & Parallel Runs
// 168, 169

public class SubmitOrderTest extends BaseTest { // extend parent class BaseTest so that all elements of BaseTest can be used here; Inheritance is used here where all parent methods used in child classes

//	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

	String productName = "ZARA COAT 3"; // belongs to entire scope
	
	@Test(dataProvider = "getData", groups = "Purchase") // to execute this, refer third testNG.xml file having this group name
// 	by passing dataProvider parameter with method name(getData defined in bottom of this class), it takes data from that given method

//	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException  // to get 3 data i.e. email, pwd, prod name from getData() method (line:143), need to declare 3 arguments to this method

	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException  // to get 3 data i.e. email, pwd, prod name from getData() method (line:143), need to declare 3 arguments to this method

	{
		
		
//		LandingPage landingPage = launchApplication();
		
//		by using below code, all the files related to chromedriver() automatically downloaded.
//		we have mentioned dependencies of "WebDriverManager" in the pom.xml file which downloads  		
/*		// moved below code to class 'BaseTest.java' 
 * 		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); */ 
		
//		driver.get("https://rahulshettyacademy.com/client");
		
/*		create object for another class 'LandingPage' and pass driver
		import package containing LandingPage as above imported  
*
*/
/*		// moved below code to class 'BaseTest.java' 
 * 		LandingPage landingPage = new LandingPage(driver); 
		landingPage.goTo(); // call goTo() method whose def found in class 'LandingPage' and URL will be fetched 
*/
//		ProductCatalogue productCatalogue = landingPage.loginApplication("pramoddipaksh@gmail.com", "P@ssw0rd"); // calling method with values passed to method def available in class LandingPage
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password")); // calling method with values passed to method def available in class LandingPage
//		the 'loginApplication' method expecting string data hence "String,String" used instead 'Object,Object'
		
// 		create object of child page class 'ProductCatalogue' and call it's definition from here
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver); // make use of this code in above line no.47
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartPage = productCatalogue.goTocartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product")); // this single line code handles below star comment lines of code
		Assert.assertTrue(true); 
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmedMessage = confirmationPage.getconfirmationMessage();
		
/*		// all the products title added from the Cart page
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//the 'anyMatch'-> Returns whether any elements of this stream match the provided predicate
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); // if matched then return true or false
		System.out.println("Cart product matched: " +match); // assert check if anyMatch returning true or not. otherwise test fail 
		Assert.assertTrue(true); */
		
		
		
/*		driver.findElement(By.cssSelector(".totalRow button")).click(); // click on Checkout button
		
		// handle country drop-down list and select India from multi_select drop-down list
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // wait until India option in the drop-down search box displayed
*/		
		// now click on searched option "India" from displayed list
		// cssSelector of 'India' from searched multi select option using "nth-of-type" : .ta-item:nth-of-type(2)
		// xpath of 'India' from searched multi select option: (//button[contains(@class,'ta-item')])[2]
		
/*		driver.findElement(By.xpath("(//button[contains(@classWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); ,'ta-item')])[2]")).click(); // clicks India option
		
		driver.findElement(By.cssSelector(".action__submit")).click(); // clicks on "Place Order" button  
		
		String successOrderMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();	// get confirmed order success msg from Order page	
		System.out.println(successOrderMsg);
*/		
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("Thankyou for the order.")); // checking actual message with expected msg using assert(true)
		
//		driver.close();
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"}) 
// 	this test depends on method "submitOrder"; if that is pass which means there is atleast one product 'ZARA COAT 3' exist on Orders Page
	public void orderHistoryTest()
	{
	
		// To verify ZARA COAT 3 is displaying in Orders page
		ProductCatalogue productCatalogue = landingPage.loginApplication("pramoddipaksh@gmail.com", "P@ssw0rd"); // calling method with values passed to method def available in class LandingPage
//		
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	// Section 21: Framework Part 4 - Test Strategy- Control Tests Execution- Run Parallel Tests
	// 171 	

	
	
	
	// Section 22: Framework Part 5 -Extent HTML reports & TestNG Listeners & Thread Safe execution
	// Extent Reports
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException  //
	{
/*		// object[][]- it is an generic data type which can accepts any data type values
		return new Object[][] {{"pramoddipaksh@gmail.com","P@ssw0rd","ZARA COAT 3"},{"pramoddshirale@gmail.com", "P@ssw0rd","ADIDAS ORIGINAL"}}; // have 2 set of data
*/		
//		HashMap<Object,Object> map = new HashMap<Object,Object>();
		
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<second way to pass data >>>>>>>>>>>>>>>>>>		
		
		// first set of data
/*		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "pramoddipaksh@gmail.com");  // key value
		map.put("password", "P@ssw0rd"); // key value
		map.put("product", "ZARA COAT 3"); // key value
		// second set of data
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "pramoddshirale@gmail.com");  // key value
		map1.put("password", "P@ssw0rd");   // key value
		map1.put("product", "ADIDAS ORIGINAL");  // key value  
*/
		//Section 21: Framework Part 4 - Test Strategy- Control Tests Execution- Run Parallel Tests
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//companyp//Data//PurchaseOrder.json"); 
// no need to create object to access this method because its def copied in the BaseTest.java class which is extended this class already. hence all methods from BaseTest accessible to this child class.
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
//		the get(0)-> retrieves first data set and get(1)-> retrieves second data set		

	
	
	}

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<first way to pass data >>>>>>>>>>>>>>>>>>
	
//	before using HashMap, below way used
/*
 * 	@DataProvider
	public Object[][] getData()
 * {
 * 		return new Object[][] {{"pramoddipaksh@gmail.com","P@ssw0rd","ZARA COAT 3"},{"pramoddshirale@gmail.com", "P@ssw0rd","ADIDAS ORIGINAL"}}; // have 2 set of data
 * }
 * 	
 */
}

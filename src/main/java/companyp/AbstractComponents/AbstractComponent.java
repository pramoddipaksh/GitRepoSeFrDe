package companyp.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import companyp.pageobjects.CartPage;
import companyp.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver; // created local variable of type WebDriver

	public AbstractComponent(WebDriver driver) { // constructor created initialize driver
		// TODO Auto-generated constructor stub
		this.driver = driver; // this current driver now available to whole this class received from
		PageFactory.initElements(driver, this); // accepts 2 parameters 'driver' and 'this'.

	}

//	driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // click on "Cart" button on top page
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersHeader;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	// wait for web element to appear
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public CartPage goTocartPage() {

// 		driver.findElement(By.cssSelector(".totalRow button")).click(); // click on Checkout button
		cartHeader.click(); // take you to Cart page

		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

	public OrdersPage goToOrdersPage() {
		ordersHeader.click(); // take you to Orders page

		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;

	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

		Thread.sleep(1000); // use below code if its's taking time to appear
		// 4 seconds
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(ele)); // this explicit wait is
		 * for "loading" icon appear when add to cart clicked
		 */

	}

}

package companyp.TestComponents;

import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import companyp.pageobjects.LandingPage;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		// properties class
		Properties prop = new Properties();
//		the 'System.getProperty' dynamically gets the user's path whichever system it is. this is to use global data file on everyone's system		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//companyp//resources//GlobalData.properties");
//		the above passed path converted into file stream object because load method expects parameter of type stream 		
		prop.load(fis);
		
//		below line is replacement of line no. 46 code below
//		below is a ternary condition applied in which if first cond=true then second argument executes(from maven cmd prompt argument passed browsername value) and if first cond=false then third argument executes(from GlobalData.properties takes browsername value)		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		*global data can be mentioned in .properties file and can be used at run time execution. e.g. browserName:chrome-firefox-edge; environments:qa-staging-prod; urls:qa url-staging url-prod url; timeout*
		
		
//		String browserName = prop.getProperty("browser");  // now using properties object you can access any properties from properties file GlobalData.properties
		
//		if(browserName.equalsIgnoreCase("chrome"))   // if chrome condition true then execution starts on chrome browser following its code
		if(browserName.contains("chrome"))  // contains replaced because of headless thing with match partially as well if argument pass from maven only chrome then in both cond chrome string will match
		{			
			// chrome browser
//Section 23: Framework Part 6 - Test Execution from Maven & Integration with Jenkins CI/CD [184]			
		ChromeOptions options = new ChromeOptions(); //using 'ChromeOptions()' object we can tweak existing chrome browser as required
			
		WebDriverManager.chromedriver().setup();
		
		if(browserName.contains("headless")) { // this cond will only run if browsername passed as headless otherwise it'll run with headly
		options.addArguments("headless"); // using 'ChromeOptions()' object we can pass argument as 'headless' to run in headless mode
		}
		
		driver = new ChromeDriver(options);  // now provide 'options' variable to chromedriver which run in headless 

		driver.manage().window().setSize(new Dimension(1440,900)); // this helps to run in full screen; was added due to screen window during execution will not fit hence size fixed
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
//		driver.manage().window().maximize();			}
		}	
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C://Users//Namita//Downloads//geckodriver-v0.35.0-win-aarch64//geckodriver.exe");
			driver = new FirefoxDriver();
			//firefox browser
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C://Users//Namita//Downloads//edgedriver_win64//msedgedriver.exe");
			driver = new EdgeDriver();
			//microsoft edge browser
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		driver.manage().window().maximize();
		return driver;
	}
	
	// Section 21: Framework Part 4 - Test Strategy- Control Tests Execution- Run Parallel Tests
	// json data handling method
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException { // filePath of json file
		
//	 	read json to string
			String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8); // the 'StandardCharsets.UTF_8' used due to method 'readFileToString' showing depreaciated 
//		use this 'System.getProperty("user.dir")+' to get path dynamically instead of giving local machine path 		

//			String to HashMap through "Jackson databind"(which converts string content to HashMap)
			
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
//				the method 'readValue' reads the string and convert to HashMap; at the end List is returned	
			});
			
			return data;		
			
		}
	
	
	//take screenshot
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver; // cast 
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File file = new File(System.getProperty("user.dir")+ "//reports//" +testcaseName+ ".png");
		FileUtils.copyFile(source, file); // source and destination need to pass
		return System.getProperty("user.dir")+ "//reports//" +testcaseName+ ".png";
		
		
	}
	
	
	@BeforeMethod(alwaysRun = true)  // 'alwaysRun = true' denotes it'll run always irrespective of anything
	public LandingPage launchApplication() throws IOException
	{
		
		// method which is applicable to all the test cases
		
		driver = initializeDriver();
		
		landingPage = new LandingPage(driver); 
		landingPage.goTo(); // call goTo() method whose def found in class 'LandingPage' and URL will be fetched 
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void teadDown()
	{
		driver.close();
	}
	
}

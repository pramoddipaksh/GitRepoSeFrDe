package companyp.ExtentReports;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// Section 22: Framework Part 5 -Extent HTML reports & TestNG Listeners & Thread Safe execution
// 175

public class ExtentReportDemo {
	
	ExtentReports extent;
	
	@BeforeMethod
	public void config()
	{
		
		//ExtentReport, ExtentSparkReporter
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Report"); // set report name
		reporter.config().setDocumentTitle("Test Results"); // document title/tab
		
		extent = new ExtentReports();
		extent.attachReporter(reporter); // whatever  config done in ExtentSparkReporter is all passed using it's object to main class ExtentReports
		extent.setSystemInfo("SDET", "Pramod S");
		System.out.println("GIT Purpose Data Added1");
		System.out.println("GIT Purpose Data Added2");
		
		System.out.println("GIT Purpose Data Added3");
		System.out.println("GIT Purpose Data Added4");
		System.out.println("GIT Purpose Data Added5");
		System.out.println("GIT Purpose Data Added6");
	
		
	}
	
	private void method5() {
		// TODO Auto-generated method stub

		System.out.println("new class text: class-ExtentReport-method5");
	}
	
	private void method6() {
		// TODO Auto-generated method stub

		System.out.println("new class text: class-ExtentReport-method6");
	}
	
	
	@Test
	public void initialDemo() {		
		
		
		ExtentTest test = extent.createTest("Initial Demo");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());	
		
		driver.close();
		test.fail("Result do not match"); // for fail case
		
		extent.flush(); // report will get generate; update pass/fail; just used to see fail case in report example. real time not using this. actual fail testcase  
			
		
	}
	
	

}

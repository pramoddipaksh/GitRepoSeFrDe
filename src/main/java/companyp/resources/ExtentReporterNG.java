package companyp.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Section 22: Framework Part 5 -Extent HTML reports & TestNG Listeners & Thread Safe execution
// 176

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
		
		//ExtentReport, ExtentSparkReporter classes		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Report"); // set report name
		reporter.config().setDocumentTitle("Test Results"); // document title/tab
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); // whatever  config done in ExtentSparkReporter is all passed using it's object to main class ExtentReports
		extent.setSystemInfo("SDET", "Pramod S");
		
		
		return extent;
		
		
		
		
	}
	
	

}

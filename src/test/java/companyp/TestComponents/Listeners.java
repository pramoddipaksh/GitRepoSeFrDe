package companyp.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import companyp.resources.ExtentReporterNG;

// Section 22: Framework Part 5 -Extent HTML reports & TestNG Listeners & Thread Safe execution
//176

// the 'ITestListener' always listens 
public class Listeners extends BaseTest implements ITestListener {  //these are interfaces - ITestListener
	
	ExtentTest test;
	
	ExtentReports extent = ExtentReporterNG.getReportObject();   //Section 22:178
	
	// Section 22: 178
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  // *Thread safe* (to avoid concurrency control); Section 22:178
//	For example, the class below generates unique identifiers local to eachthread.A thread's id is assigned the first time it invokes ThreadId.get()and remains unchanged on subsequent calls
// Each thread holds an implicit reference to its copy of a thread-localvariable as long as the thread is alive and the ThreadLocalinstance is accessible; after a thread goes away, all of its copies ofthread-local instances are subject to garbage collection (unless otherreferences to these copies exist).	
// this is how *parallel test execution* handled without any concurrency issue. hence, in extent reporting each test generates reports without another test overridden  	
	@Override
	public void onTestStart(ITestResult result) {
		// not implemented
		
		test = extent.createTest(result.getMethod().getMethodName()); // to get the title of method name; not class name
		
		extentTest.set(test); // create unique thread id(ErrorValidations)->test as part of thread safe
		
	// the "set" will create unique id and "get" will pull that unique id	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// not implemented
		test.log(Status.PASS, "Test Passed");	
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// not implemented
		extentTest.get().fail(result.getThrowable()); // give all the error message
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		getTestClass()-> get the class from testNG.xml, from test tag the class its referring
//		getRealClass()-> it will go to actual real class i.e. SubmitOrderTest.java
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		
		
		//1. take screenshot; 2. attach it to report
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// not implemented
	}

	@Override
	public void onFinish(ITestContext context) {
		// not implemented
		extent.flush();
	}

}

// this Listeners.java class file path given into the testNG.xml file to execute this 

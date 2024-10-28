package companyp.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{ // testNg interface 'IRetryAnalyzer', whenever test fails then it will execute this until cond false  

// useful for flaky tests where in first attempt some test fails but in second attempt pass 
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {  //whenever test failure it'll come here as well to recheck do I need to re-run
		// TODO Auto-generated method stub
		// write the logic here how many times need to re-run
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	

}

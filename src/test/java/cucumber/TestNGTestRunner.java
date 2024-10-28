package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Section 25: Framework Part 8 - Integrating Cucumber Wrapper into Selenium Framework
// cucumber to run-> depend on either testNG or jUnit runner
// depend on which assertions code having like testNG or jUnit, that runner be selected

@CucumberOptions(features="src/test/java/cucumber",glue="companyp.stepDefinitions",monochrome=true, tags="@Regression", plugin= {"html:target/cucumber.html"})   
//similar to @Test; give path where feature exists and stepDefinitions file path; monochrome=true makes reports in readable format; plugin will vreate report in html format in given path/folder
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	// to run the feature file - test runner
	// the "AbstractTestNGCucumberTests" provided by cucumber having all the wrapper test
	
	

}

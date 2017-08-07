package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features", 
		glue={"StepDefinition"}, 
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:F:\\Selenium\\Workspace_Selenium\\CucumberAutomation\\Reports\\cucumber-extent\\report.html"})
public class TestRunner {
	
	@AfterClass
	public static void reportSetup()
	{
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
		
	}
	

}
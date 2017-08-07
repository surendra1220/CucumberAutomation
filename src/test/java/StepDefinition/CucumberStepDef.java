package StepDefinition;

import gherkin.formatter.model.ScenarioOutline;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import runner.Constants;
import runner.Repository;
import runner.WebActions;

import java.util.*;

public class CucumberStepDef extends Repository{
	
	
	public static WebDriver driver;
	public String ChromeURL = null;
	public Scenario scenario;
	public String scenarioName;
	ExtentReports extent;
	ExtentTest logger;

	//WebActions actions = new WebActions();

	@Before
	public void before(Scenario scenario) throws Exception {
		this.scenario = scenario;
		scenarioName = scenario.getName();
		System.out.println("Scenario name is :" + scenarioName);
		WebActions.getData("Learning", scenarioName);
				
		WebActions.InitiateBrowser(WebActions.browserName);	
		scenario.write("Browser initiated");
		WebActions.getURL("URL");
		scenario.write("Launched Application URL");
		WebActions.wait("30");
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
		scenario.write("Finished scenario");
		if(scenario.isFailed())
		{
			scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
		}
		System.out.println("Test Environment Destroyed");
		System.out.println("---------------------------------------");
		extent.flush();
		extent.close();
		driver.close();
		driver.quit();
	}
	
	
/*	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}*/
	
	@Given ("^The test case to automate is '(.*)'$")
	public void SetUp(String testcaseName) throws Exception 
	{
		scenario.write("Getting data from test data sheet");
		WebActions.getData("Learning", testcaseName);
		WebActions.InitiateBrowser(WebActions.browserName);		
		WebActions.getURL("URL");
		WebActions.wait("30");
	}
	
	@When ("^user clicks on Login button$")
	public void ClickOnLogin() throws Exception 
	{
		WebActions.wait("30");
		WebActions.Click(FlipkartLogin_Link);
		//WebActions.WaitTillElementFound(Username_txt);
		//WebActions.WaitTillElementFound();
		/*WebDriverWait wait = (new WebDriverWait(driver, 30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@autocomplete='on']/div[3]/button")));*/
		System.out.println("Popup verified");
	}
	
	@And ("^user enters Username and Password$")
	public void userCredentials() throws Exception 
	{
		//WebActions.wait("30");
		Thread.sleep(5000);
		WebActions.Click(Username_txt);
		WebActions.type(Username_txt, "Username");
		WebActions.Click(Password_txt);
		WebActions.type(Password_txt, "Password");
		WebActions.Click(FlipkartSubmit_Button);	
		/*WebDriverWait wait = (new WebDriverWait(driver, 30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Track Order')]")));*/
		
		/*Wait wait = new FluentWait(driver)    
		.withTimeout(30, SECONDS)    
		.pollingEvery(5, SECONDS)   
		.ignoring(NoSuchElementException.class);
		WebElement foo = wait.until(new Function() {    
		public WebElement apply(WebDriver driver) {    
		return driver.findElement(By.id("foo"));    
		}
		});*/
		
//		System.out.println("Track order link verified");
//		WebActions.Click(TrackOrder_Link);
		
	}
	
	/*@Given ("^The test case to automate is new value '(.*)'$")
	public void userCredentials(String testcaseName) throws Exception 
	{
		//WebActions.elementlocator(FirstName_txt);
		WebActions.type(FirstName_txt, "Last Name");
		WebActions.wait("10");
		//WebActions.Click(Login_Button);
		WebActions.Click(Login_ID);
		
		//driver.findElement(By.xpath("asdfjasdjfkasjdfjkas"))
		
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void setUp() throws Throwable
	{
		System.setProperty("webdriver.gecko.driver", "F:\\Selenium\\Jars\\geckodriver_14.exe");
		//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
		driver = new FirefoxDriver();
	}
	
	public void LaunchChrome(String ChromeURL) throws Throwable {		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Libraries\\chromedriver_win32\\chromedriver_win32_2.exe");
		driver = new ChromeDriver();
		//Launch Signup link
		//driver.get("https://book2.spicejet.com/");
		driver.get(ChromeURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Given("^User launches spicejet application111$")
	public void AppLaunch() throws Throwable {		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Libraries\\chromedriver_win32\\chromedriver_win32_2.exe");
		driver = new ChromeDriver();
		//Launch Signup link
		driver.get("https://book.spicejet.com/Register.aspx");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click on PAN Card No text to page down
		driver.findElement(By.xpath(Repository.PANCardNo)).click();
		
		String mainWindow = driver.getWindowHandle();
		String secondWindow = "";
		
		//Click on Terms and Conditions link
		driver.findElement(By.xpath(TermsandConditions)).click();
		
		java.util.Set<String> allwindows = driver.getWindowHandles();
		for(String childwindow : allwindows)
		{
			if(!childwindow.equals(mainWindow))
			{
				driver.switchTo().window(childwindow);
				secondWindow = childwindow;
			}
			
		}
		
		//Click on Register Now button
		driver.findElement(By.xpath(RegisterNow)).click();
		
		java.util.Set<String> allwindows1 = driver.getWindowHandles();
		for(String thirdwindow : allwindows1)
		{
			if(!thirdwindow.equals(secondWindow) && !thirdwindow.equals(mainWindow))
			{
				driver.switchTo().window(thirdwindow);
			}
			
		}
		
		driver.findElement(By.xpath(SubmitButton));
		
		driver.switchTo().window(mainWindow);
		
		
//		String text = driver.findElement(By.xpath(LoginHere_Link)).getText();
//		System.out.println(text);
		
		driver.findElement(By.xpath(SubmitButton));
		System.out.println("Moved to first window");
		
		//driver.close();
		

		

	}
	
	@Given("^User launches spicejet application$")
	public void AppLaunch1() throws Throwable {		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Libraries\\chromedriver_win32\\chromedriver_win32_2.exe");
		driver = new ChromeDriver();
		//Launch Signup link
		driver.get("https://book.spicejet.com/Register.aspx");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click on PAN Card No text to page down
		driver.findElement(By.xpath(Repository.PANCardNo)).click();
		
		String mainWindow = driver.getWindowHandle();
		String secondWindow = "";
		
		//Click on Terms and Conditions link
		driver.findElement(By.xpath(TermsandConditions)).click();
		
		java.util.Set<String> allwindows = driver.getWindowHandles();
		for(String childwindow : allwindows)
		{
			if(!childwindow.equals(mainWindow))
			{
				driver.switchTo().window(childwindow);
				secondWindow = childwindow;
			}
			
		}
		
		//Click on Register Now button
		driver.findElement(By.xpath(RegisterNow)).click();
		
		
		for(String thirdwindow : driver.getWindowHandles())
		{
			if(!allwindows.contains(thirdwindow))
			{
				driver.switchTo().window(thirdwindow);
			}
			
		}
		
		driver.findElement(By.xpath(SubmitButton));
		
		driver.switchTo().window(mainWindow);
		
		
//		String text = driver.findElement(By.xpath(LoginHere_Link)).getText();
//		System.out.println(text);
		
		driver.findElement(By.xpath(SubmitButton));
		System.out.println("Moved to first window");
		
		//driver.close();
		

		

	}
	
	@Given("^Spicejet examples$")
	public void spicejetExamples() throws Throwable {
		//LaunchChrome("https://surendra:surendra@www.engprod-charter.net/");
		
		Actions act = new Actions(driver);
		 act.moveToElement(driver.findElement(By.xpath(Menu_Link))).perform();
		 act.moveToElement(driver.findElement(By.xpath(Travel_Link))).build().perform();
		//driver.findElement(By.xpath(Travel_Link))).click().build().perform();
		driver.findElement(By.xpath(SpecialAss_Link)).click();
		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Libraries\\chromedriver_win32\\chromedriver_win32_2.exe");
		driver = new ChromeDriver();
		//Launch Signup link
		//driver.get("https://book2.spicejet.com/");
		driver.get("https://surendra:surendra@www.engprod-charter.net/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	

*/	


}

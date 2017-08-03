package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.Set;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import runner.Repository;
import runner.WebActions;

import java.util.*;

public class CucumberStepDef extends Repository{
	
	
	public static WebDriver driver;
	public String ChromeURL = null;
	public Scenario scenario;
	public String scenarioName;

	//WebActions actions = new WebActions();
	
	

	//@Before
	//@Given("^Spicejet is the test case$")
/*	public void before(Scenario scenario) throws Exception {
	    this.scenario = scenario;
	    scenarioName = scenario.getName();
	    System.out.println(scenarioName);
}*/
	
	@Given ("^'(.*)' is the test case$")
	public void SetUp(String testcaseName) throws Exception 
	{
		WebActions.getData("Sheet1", testcaseName);
		WebActions.InitiateBrowser(WebActions.browserName);
		
		WebActions.getURL();
		WebActions.wait("30");

		/*driver.findElement(By.xpath()).sendKeys(WebActions.testdata.get("Last Name"));
		driver.findElement(By.xpath(xpathExpression))*/
		
		//WebActions.elementlocator(FirstName_txt);
		WebActions.type(FirstName_txt, "Last Name");
		WebActions.wait("10");
		//WebActions.Click(Login_Button);
		WebActions.Click(Login_ID);
		
		//driver.findElement(By.xpath("asdfjasdjfkasjdfjkas"))
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

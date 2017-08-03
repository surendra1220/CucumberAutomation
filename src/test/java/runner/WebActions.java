package runner;

import java.io.File;
import java.lang.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;







import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import StepDefinition.CucumberStepDef;

public class WebActions extends CucumberStepDef{
	
	//public static WebDriver driver;
	public static String browserName = null;
	public static String Drivers = "F:\\Selenium\\Workspace_Selenium\\CucumberAutomation\\Drivers";
	public static HashMap<String, String> testdata = new HashMap<String, String>();
	

	public static HashMap<String, String> getData(String sheetName,String ScenarioName) throws Exception{

		FileInputStream file=new FileInputStream("E:\\TestData.xlsx");
		XSSFWorkbook work = new XSSFWorkbook(file);
		XSSFSheet sheet=work.getSheet(sheetName);
		 
		int rowcount=sheet.getPhysicalNumberOfRows();
		int colCount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int i=0;i< rowcount; i++)
		{
			if(sheet.getRow(i).getCell(0).getStringCellValue().equals(ScenarioName) && sheet.getRow(i).getCell(1).getStringCellValue().equals("yes"))
			{
				//System.out.println("Value from excel sheet is " +sheet.getRow(i).getCell(i).getStringCellValue());
				for(int k=0;k< colCount; k++)
				{
					testdata.put(sheet.getRow(0).getCell(k).getStringCellValue(), sheet.getRow(i).getCell(k).getStringCellValue());
				}
				break;
			}			
		}
		return testdata;		
	}
	
	public static void InitiateBrowser(String browserName) throws Exception
	{
		browserName = WebActions.testdata.get("Browser");
		
		if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", Drivers+"/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "/Drivers/geckodriver_12.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Drivers+"/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();			
		} 
	}
	
	public static void getURL() throws Exception
	{
		try{
		driver.get(Constants.URL);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void wait(String time) throws Exception
	{
		try {
			driver.manage().timeouts()
					.implicitlyWait(Integer.parseInt(time), TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static String elementlocator(String locator)
	{
		if(locator.contains("xpath")){
			locator = locator.substring(6);
			System.out.println(locator);		
		}else if(locator.contains("id")){
			locator = locator.substring(3);
			System.out.println(locator);
		}else if(locator.contains("css")){
			locator = locator.substring(4);
			System.out.println(locator);
		}
		return locator;
		
	}
	
	public static String elementlocator1(String locator) {
		String[] locators = locator.split("=");
		String loc1 = locators[0];
		String loc2 = locators[1];
		if (loc1.contains("xpath")) {
			// locator = locator.substring(6);
			System.out.println("local 2 is " + loc2);

		}
		return loc2;
		// return null;

	}
	
	public static void type(String locator, String value) {
		String[] locators = locator.split("=");
		String loc1 = locators[0];
		System.out.println("local 1 is :"+loc1);
		//String loc2 = locators[1];
		String loc = elementlocator(locator);
		if (loc1.equals("xpath")) {
			driver.findElement(By.xpath(loc)).sendKeys(
					WebActions.testdata.get(value));
		}else if(loc1.equals("id")){
			driver.findElement(By.id(loc)).sendKeys(
					WebActions.testdata.get(value));
		}else if(loc1.equals("css")){
			driver.findElement(By.cssSelector(loc)).sendKeys(
					WebActions.testdata.get(value));
		}
		
		
	
	}
	
	/*public static void type(String locator, String value) {
		String loc = elementlocator(locator);
		driver.findElement(By.xpath(loc)).sendKeys(
				WebActions.testdata.get(value));
	}*/

	public static void Click(String locator)
	{
		String[] locators = locator.split("=");
		String loc1 = locators[0];
		System.out.println("local 1 is :"+loc1);
		//String loc2 = locators[1];
		String loc = elementlocator(locator);
		if (loc1.equals("xpath")) {
			driver.findElement(By.xpath(loc)).click();
		}else if(loc1.equals("id")){
			driver.findElement(By.id(loc)).click();
		}else if(loc1.equals("css")){
			driver.findElement(By.cssSelector(loc)).click();
		}
	}
	
}

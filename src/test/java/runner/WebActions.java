package runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WebActions {
	
	
	public static HashMap<String, String> testdata = new HashMap<String, String>();
	

	public static HashMap<String, String> getData(String sheetName,String ScenarioName) throws Exception{
		
		
		FileInputStream file=new FileInputStream("E:\\TestData.xlsx");
		XSSFWorkbook work = new XSSFWorkbook(file);
		XSSFSheet sheet=work.getSheet(sheetName);
		 
		int rowcount=sheet.getPhysicalNumberOfRows();
		int colCount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println(rowcount);
		
		for(int i=0;i< rowcount; i++)
		{
			//System.out.println(sheet.getRow(i).getCell(i).getStringCellValue());
			if(sheet.getRow(i).getCell(0).getStringCellValue().equals(ScenarioName) && sheet.getRow(i).getCell(1).getStringCellValue().equals("yes"))
			{
				//System.out.println("Value from excel sheet is " +sheet.getRow(i).getCell(i).getStringCellValue());
				for(int k=0;k< colCount; k++)
				{
					//System.out.println(k);
					testdata.put(sheet.getRow(0).getCell(k).getStringCellValue(), sheet.getRow(i).getCell(k).getStringCellValue());
				}
				break;
			}
			
		}
		return testdata;
		
	}
	
}

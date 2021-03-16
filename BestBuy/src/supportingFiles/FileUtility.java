package supportingFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtility {	
	static FileInputStream file ;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static Map<String,String> testData = new HashMap<String,String>(); 
	static Map<String,String> OR = new HashMap<String,String>();
	static FileWriter fw;
	static BufferedWriter WriteLine;
	static String strReportLog;
	 
	 public static boolean readData(String strFileName, String strTestCaseName) 
	 {			 
		boolean blnFlag = false;
		int intcounter = 0;
		// Path of the Excel File
		try
		{
			String strPath = System.getProperty("user.dir");			
			strPath = new File(strPath+"/..").getCanonicalPath();	
			strPath = strPath +"\\BestBuy\\TestData\\"+strFileName;
			File excelFile = new File(strPath);
			blnFlag = excelFile.exists();
			if(blnFlag)
			{
				// Read the data	
				file = new FileInputStream(excelFile);
				workbook = new XSSFWorkbook(file);
				int intNumberofWorkbooks = workbook.getNumberOfSheets();
				for (int i =0; i<intNumberofWorkbooks ; i++)
				{
					sheet = workbook.getSheetAt(i);					
					Iterator<Row> rowIterator = sheet.iterator();					
					while(rowIterator.hasNext()) {
						if(intcounter!=0)
							break;
		                Row row = rowIterator.next();
		                String nametocheck = row.getCell(0).getStringCellValue();
		                // If the first value is the test case name
		                if(strTestCaseName.equals(nametocheck))
		                {	
		                Iterator<Cell> cellIterator = row.cellIterator();		                
		                while(cellIterator.hasNext()) 
		                	{	                    
		                		Cell cell = cellIterator.next();
		                	
			                   // Create the key value = column heading		                    
			                    	int inttoprow = sheet.getTopRow();
			                    	XSSFRow toprow = sheet.getRow(inttoprow);			                    	
			                    	int intcolindex = cell.getColumnIndex();			                    	
			                    	String strKey = toprow.getCell(intcolindex).getStringCellValue();
			                    //  Get the value for the key	
			                    	
			                    	 String strValue = cell.getStringCellValue();
			                    	testData.put(strKey, strValue);                   	
			                    		
			                    	intcounter ++;           
			                   
		                }
		                
		                }
		                		                
					}
					if(intcounter!=0)
						break;
				}
				blnFlag = intcounter!=0;
				if(!blnFlag)
				{
					System.out.println("The testcasename passed '"+strTestCaseName+"' does not exist in any of the worksheets in '"+strPath+".");
				}
				file.close();
				
			}
			else
			{
				System.out.println("The specified file ("+strPath+") does not exist");			
			}
		}
		catch(Exception e)
		{
			blnFlag = false;
			System.out.println("Exception ("+e.toString()+") occurred while reading the data from excel.");		
		}
		
		return blnFlag;
	 }
	 
	 public static boolean readOR(String strFileName) 
	 {			 
		boolean blnFlag = false;
	
		// Path of the Excel File
		try
		{
			String strPath = System.getProperty("user.dir");			
			strPath = new File(strPath+"/..").getCanonicalPath();	
			strPath = strPath +"\\BestBuy\\Objects\\"+strFileName;
			File excelFile = new File(strPath);
			blnFlag = excelFile.exists();
			if(blnFlag)
			{
				// Read the data	
				file = new FileInputStream(excelFile);
				workbook = new XSSFWorkbook(file);
				int intNumberofWorkbooks = workbook.getNumberOfSheets();
				for (int i =0; i<intNumberofWorkbooks ; i++)
				{
					sheet = workbook.getSheetAt(i);					
					Iterator<Row> rowIterator = sheet.iterator();					
					while(rowIterator.hasNext()) {
						
		                Row row = rowIterator.next();
		                String logicalName = row.getCell(0).getStringCellValue();
		                String locatorValue = row.getCell(3).getStringCellValue();
		                OR.put(logicalName, locatorValue);
		                
		                }
		                		                
					}			
								
			}
			else
			{
				System.out.println("The specified file ("+strPath+") does not exist");			
			}
			file.close();
					
		}
		catch(Exception e)
		{
			blnFlag = false;
			System.out.println("Exception ("+e.toString()+") occurred while reading the data from excel.");		
		}
		
		return blnFlag;
	 }
	 
	 public static String getTestData(String strKey)
	 {
		 String strValue = "";
		 try
		 {
			 strValue =  testData.get(strKey);
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception ("+e.toString()+") occurred while retrieving value.");	
		 }
		 return strValue;
	 }
	 
	 public static String getOR(String strKey)
	 {
		 String strValue = "";
		 try
		 {
			 strValue =  OR.get(strKey);
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception ("+e.toString()+") occurred while retrieving value.");	
		 }
		 return strValue;
	 }
	 
	 public static boolean createStepLog()
	 {
		 boolean blnFlag = false;
		 try
		 {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy MM dd_HH mm ss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String strFileName = "StepLog"+strDate+".txt";
			String strPath = System.getProperty("user.dir");			
			strPath = new File(strPath+"/..").getCanonicalPath();	
			strReportLog = strPath +"\\BestBuy\\StepLog\\"+strFileName;
			File repportLog = new File(strReportLog);
			blnFlag = !(repportLog.exists());
			if(blnFlag)
			{
				repportLog.createNewFile();
				fw = new FileWriter(repportLog.getAbsoluteFile());
				WriteLine = new BufferedWriter(fw);
			}
			else
			{
				System.out.println("The file ("+strReportLog+") already exists.Please delete or rename it and try running again.");	
			}			
		 }
		 catch(Exception e)
		 {
			blnFlag = false; 
			System.out.println("Exception ("+e.toString()+") occurred while creating the report log.");	
		 }
		 return blnFlag;
		 
	 }
	 
	 
	 public static boolean writetoLog(String strContent)
	 {
		 boolean blnFlag = false;
		 try
		 {
			 System.out.println(strContent);
			 WriteLine.write(strContent+"\n");
			 blnFlag = true;
		 }
		 catch(Exception e)
		 {
			blnFlag = false; 
			System.out.println("Exception ("+e.toString()+") occurred while writing to the report log ("+strReportLog+").");	
		 }
		 return blnFlag;
		 
	 }
	 
	 
	 public static boolean tearDown()
	 {
		 boolean blnFlag = false;
		 try
		 {
			 WriteLine.close();	
			 blnFlag = true;
		 }
		 catch(Exception e)
		 {
			blnFlag = false; 
			System.out.println("Exception ("+e.toString()+") occurred.");	
		 }
		 return blnFlag;
		 
	 }

}



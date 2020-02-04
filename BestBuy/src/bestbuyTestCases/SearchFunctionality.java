package bestbuyTestCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import supportingFiles.*;


public class SearchFunctionality extends Variables{
		
	@BeforeClass 
	public static void testSuiteSetup() {
		// Initialise the variables
				
		UtilityFunctions.setpath();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		// Set up OR from the OR Sheet
		blnFlag = FileUtility.readOR("ORSheet.xlsx");
		if(!blnFlag)
			assertEquals(true,blnFlag);
		
		// Create Step log
		blnFlag = FileUtility.createStepLog();
		if(!blnFlag)
			assertEquals(true,blnFlag);
	} 
	 
	  
	@Before
	public void testSetUp() {
		String strMethodName = name.getMethodName();
		FileUtility.writetoLog("Running Test Case: "+strMethodName+"\n");
		
		// Set up test data from datasheet
		boolean blnFlag = FileUtility.readData("TestData.xlsx", strMethodName);
		if(!blnFlag)
			assertEquals(true,blnFlag);
		
		// Reset step counter
		Stepnumber = 1;
		
	}
	
	@Rule 
	public TestName name = new TestName();
	
	@Test
	public void InvalidZipCode() {
		
		String strURL = "https://www.bestbuy.com/";
		// Test data used in script
		String strValuetoEnter = FileUtility.getTestData("strValuetoEnter");
		String strExactProduct = FileUtility.getTestData("strExactProduct");
		String strZipCode = FileUtility.getTestData("strZipCode");
				
		// *************************Launch Browser***********************************
		strStepDescription = "Launch the Browser URL and verify launched browser.";
		strExpectedResult = "The Browser should be launched successfully.";
		strActualResult = "The Browser is launched successfully.";
		blnFlag = BusinessFunctions.launchBrowser(strURL);
		Report.Print();
		
		// *************************Search and click with product name***********************************
		strStepDescription = "Search and click on the product name.";
		strExpectedResult = "The product should be searched and clicked successfully.";
		strActualResult = "The product is searched and clicked successfully.";
		blnFlag = BusinessFunctions.searchclickverify(strValuetoEnter,strExactProduct);
		Report.Print();		
		
		// *************************Add and go to Cart***********************************
		strStepDescription = "Add the product to Cart and go to Cart.";
		strExpectedResult = "The exact product should be added to Cart successfully.";
		strActualResult = "The exact product is added to cart successfully.";
		blnFlag = BusinessFunctions.addgoCheckCart();
		Report.Print();
		
		// ***Check invalid Zip code error**************
		strStepDescription = "Enter invalid zip code.";
		strExpectedResult = "There should be error for invalid zip code";
		strActualResult = "There is error for invalid zip code.";
		blnFlag = BusinessFunctions.updatecheckZipCodeError(strZipCode);
		Report.Print();
		
	}
	
	
	
	@After
	public void testTearDown() {
			
	}
	
	@AfterClass 
	public static void testSuiteTearDown() {
		// Quit driver instance
		driver.quit();	
		// Clean up
		FileUtility.tearDown();
	}

}


	
	
	






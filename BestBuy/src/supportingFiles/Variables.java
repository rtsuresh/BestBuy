package supportingFiles;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class Variables {
	

			// Timeout- Generic (in seconds)
		public static final int intTimeOut = 10;
		public static final String strLocator = "XPATH";
		
		// Variables used in Script
		public static WebDriver driver;
		
		public static class CustomError{
			public static String description = "";
		}
		public static String strStepDescription;
		public static String strActualResult;
		public static String strExpectedResult;
		public static boolean blnFlag ;
		public static StringBuffer verificationErrors = new StringBuffer();
		public static int Stepnumber= 1;
		public static String chromedriverpath;
		

}

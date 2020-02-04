package supportingFiles;

import static org.junit.Assert.*;


public class Report extends Variables{	
	
	public static void Print()
	{
		FileUtility.writetoLog("Step Number: "+Stepnumber);
		FileUtility.writetoLog("Step Description: "+ strStepDescription);
		FileUtility.writetoLog("Step Actual Result: "+strActualResult);
		FileUtility.writetoLog("Step Expected Result: "+strExpectedResult);			
		if(blnFlag)
		{			
			FileUtility.writetoLog("Test Step Pass\n");					
		}	
		else
		{
			FileUtility.writetoLog("Test Step Fail");
			FileUtility.writetoLog(CustomError.description+"\n");		
		}
		assertEquals(true,blnFlag);
		Stepnumber++;
	}

}


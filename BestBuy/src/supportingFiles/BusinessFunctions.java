package supportingFiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BusinessFunctions extends UtilityFunctions{
	
	// Launch the Home Page
	public static boolean launchBestBuy(String strURL)
	{
		boolean blnFlag = false;
		
		blnFlag = launchBrowser(strURL);
		if(blnFlag)
		{			
			blnFlag = verifyExist(strLocator,FileUtility.getOR("objLogo"),20);
			if(!blnFlag)
				CustomError.description = "The Home Page is not displayed successfully. (Exception Details:"+CustomError.description+")";
		}
		else
			CustomError.description = "The Browser did not launch successfully. (Exception Details:"+CustomError.description+")";
				
		return blnFlag;		
	}
	
	//Product Search and click
	public static boolean searchclickverify(String strValuetoEnter,String strExactProduct)
	{
		boolean blnFlag = false;	
		handleCountry();
		blnFlag = productSearch(strValuetoEnter);
		
			if(blnFlag)
				blnFlag = clickItem(strExactProduct);
			else
				CustomError.description = "The product click failed.(Exception Details:"+CustomError.description+")";
		
		
		return blnFlag;
	}
	
	//Handle country
	public static boolean handleCountry()
	{
		boolean blnFlag = false;	
		blnFlag = Click(strLocator, FileUtility.getOR("imgUS"));	
		blnFlag = Click(strLocator, FileUtility.getOR("btnClose"));
		
		return blnFlag;
	}
	
	// Product Search
	public static boolean productSearch(String strValuetoEnter)
	{		
		boolean blnFlag = false;		
	
		
		blnFlag = setText(strLocator, FileUtility.getOR("txtSearch"), strValuetoEnter);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(blnFlag)
		{		
			blnFlag = Click(strLocator, FileUtility.getOR("imgclickSearch"));		
			if(blnFlag)
			{
				blnFlag = verifySearchResult(strValuetoEnter);	
			}
			else
				CustomError.description = "The search image was not clicked. (Exception Details:"+CustomError.description+")";						
		}
		else
			CustomError.description = "The product '"+strValuetoEnter+"' was not input successfully in the search box. (Exception Details:"+CustomError.description+")";
	
		
		return blnFlag;		
	}
	
	// Verify if product searched is displayed
	public static boolean verifySearchResult(String strValuetoEnter)
	{
		boolean blnFlag = false;			
		
		
		blnFlag = verifyExist (strLocator, FileUtility.getOR("objItemCount"), 15);		
		
		if(!blnFlag)
		CustomError.description = "The product '"+strValuetoEnter+"' was not input successfully in the search box. (Exception Details:"+CustomError.description+")";	
		
		return blnFlag;		
	}
	
	
	// Click on item mentioned
	public static boolean clickItem(String strExactProduct)
	{
		boolean blnFlag = false;			
		
		try
		{
			driver.findElement(By.xpath("//a[text()='"+strExactProduct+"']")).click();
			blnFlag = true;
		}
		catch(Exception e)
		{
			blnFlag = false;
			CustomError.description = "The product '"+strExactProduct+"' was not clicked successfully  (Exception Details:"+CustomError.description+")";
		}
		
		return blnFlag;			
	}
	
	public static boolean addgoCheckCart()
	{
		boolean blnFlag = false;			
		blnFlag = addtoCart();
		if(blnFlag)
		{
			blnFlag = goToCart();			
			if(blnFlag)
			{
				blnFlag = verifyCart();
				if(!blnFlag)
					CustomError.description = "The Cart verification successful. (Exception Details:"+CustomError.description+")";
			}
			else
				CustomError.description = "The Go to Cart button was not successful. (Exception Details:"+CustomError.description+")";
		}
		else
			CustomError.description = "The Add to Cart is not successful. (Exception Details:"+CustomError.description+")";
		
		return blnFlag;			
	}
	
	// Add to Cart 
	public static boolean addtoCart()
	{
		boolean blnFlag = false;			
		blnFlag = verifyExist (strLocator, FileUtility.getOR("imgAddtoCart"), 5);
		if(blnFlag)
		{
			blnFlag = Click(strLocator, FileUtility.getOR("imgAddtoCart"));			
			if(!blnFlag)
				CustomError.description = "The Add to Cart button was not clicked successfully. (Exception Details:"+CustomError.description+")";
		}
		else
			CustomError.description = "The Add to Cart button was not displayed successfully. (Exception Details:"+CustomError.description+")";
		
		return blnFlag;			
	}
	
	// Verify Cart 
	public static boolean verifyCart()
	{
		boolean blnFlag = false;			
		blnFlag = verifyExist (strLocator, FileUtility.getOR("objItemAddedtoCart"), 15);
		
		if(!blnFlag)
			CustomError.description = "The Add to Cart button was not clicked successfully. (Exception Details:"+CustomError.description+")";
		
		return blnFlag;			
	}
	
	// Go to Cart 
	public static boolean goToCart()
	{
		boolean blnFlag = false;			
		blnFlag = Click(strLocator, FileUtility.getOR("lnkGotoCart"));		
		
		if(!blnFlag)
			CustomError.description = "The Go to Cart button was not clicked successfully. (Exception Details:"+CustomError.description+")";
		
		return blnFlag;			
	}
	
	public static boolean updatecheckZipCodeError(String strZipCode)
	{
		boolean blnFlag = false;			
		blnFlag = updateZipCode(strZipCode);		
		
		if(blnFlag)
		{
			blnFlag = checkZipCodeError();
			if(!blnFlag)
				CustomError.description = "The zip code error was not correct. (Exception Details:"+CustomError.description+")";
		}
		else
			CustomError.description = "The update zip code was not successful. (Exception Details:"+CustomError.description+")";
		
		return blnFlag;	
	}
	
	// Update Zip code Location
	public static boolean updateZipCode(String strZipCode)
	{
		blnFlag = false;
		blnFlag = verifyExist (strLocator, FileUtility.getOR("lnkUpdateLocation"), 15);
		if(blnFlag)
		{
			blnFlag = Click(strLocator, FileUtility.getOR("lnkUpdateLocation"));			
			if(blnFlag)
			{
				// Enter zip code
				setText(strLocator, FileUtility.getOR("txtLocation"), strZipCode);
				blnFlag = Click(strLocator, FileUtility.getOR("lnkUpdate"));	
				if(!blnFlag)
					CustomError.description = "The link Update was not clicked successfully. (Exception Details:"+CustomError.description+")";
			}
			else
				CustomError.description = "The link Update Location was not clicked successfully. (Exception Details:"+CustomError.description+")";
		}
		else
			blnFlag = true;
										
		return blnFlag;				
	}
	
	// Check Zip code Location error
	public static boolean checkZipCodeError()
	{
		blnFlag = false;
		blnFlag = verifyExist (strLocator, FileUtility.getOR("objZipError"), 15);
	
		if(!blnFlag)
			CustomError.description = "The Zip Code error was not found successfully. (Exception Details:"+CustomError.description+")";
			
										
		return blnFlag;				
	}
	
	
	
	
}


package com.imbb.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage extends BasePage {
	
	@FindBy(how=How.CSS, css="a.btn.default")
	@CacheLookup
	WebElement uploadprofilepic;
	
	@FindBy(how=How.CSS, css="a.btn.default")
	@CacheLookup
	WebElement checkdeletelink;

	public ProfilePage(WebDriver driver)
	{
		super(driver);
	}
	
	public void uploadProfilePic(String logopath)
	{	
		waitForVisible(driver,uploadprofilepic);
		uploadprofilepic.sendKeys(logopath);
		
	}
	
	public boolean deletePicLink()
	{	
		waitForVisible(driver,checkdeletelink);
		return checkdeletelink.isDisplayed();
	}
	
}

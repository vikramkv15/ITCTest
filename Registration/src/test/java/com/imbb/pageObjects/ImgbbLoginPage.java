package com.imbb.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ImgbbLoginPage extends BasePage {
	
	
	@FindBy(how=How.ID, id="login-subject")
	@CacheLookup
	WebElement loginusrid;
	
	@FindBy(how=How.ID, id="login-password")
	@CacheLookup
	WebElement loginpwd;
	
	@FindBy(how=How.XPATH, xpath="//button[@type='submit']")
	@CacheLookup
	WebElement submitbtn;
	
	public ImgbbLoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void userName(String usname)
	{
		loginusrid.sendKeys(usname);
	}
	
	public void passwordKey(String pwd)
	{
		loginpwd.sendKeys(pwd);
	}
	
	public ImgbbHomePage clickSubmitButton() 
	{
		submitbtn.click();
		return new ImgbbHomePage(driver);
	}
}

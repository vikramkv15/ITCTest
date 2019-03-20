package com.imbb.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ImgbbSignUpPage extends BasePage{
	
	
	@FindBy(how=How.ID, id="signup-email")
	@CacheLookup
	WebElement emailid;
	
	@FindBy(how=How.ID, id="signup-username")
	@CacheLookup
	WebElement username;
	
	@FindBy(how=How.ID, id="signup-password")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.XPATH, xpath="//button[@type='submit']")
	@CacheLookup
	WebElement createbutton;
	
	@FindBy(how=How.CSS, css="div.header.default-margin-bottom")
	@CacheLookup
	WebElement emailsent;
	
	@FindBy(how=How.ID, id="top-bar-signin")
	@CacheLookup
	WebElement loginbutton;
	
	public ImgbbSignUpPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void emailId(String email)
	{

		emailid.sendKeys(email);
	}
	
	public void userName(String usname)
	{
		username.sendKeys(usname);
	}
	
	public void passwordKey(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void createAccountButton() 
	{
		createbutton.click();
	}
	
	public void loginButton() 
	{
		loginbutton.click();
	}
	
	public void emailSentMsg() 
	{
		emailsent.getText();
	}

}

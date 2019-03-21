package com.imbb.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ImgbbHomePage extends BasePage {

	@FindBy(how = How.CSS, css = "a.edit-link")
	@CacheLookup
	WebElement editprofilelink;

	@FindBy(how = How.ID, id = "user-background-upload")
	@CacheLookup
	WebElement backgroundpicbutton;

	@FindBy(how = How.ID, id = "change-background-cover")
	@CacheLookup
	WebElement changebackgroundpicbutton;

	@FindBy(how = How.CSS, css = "a.edit-link")
	@CacheLookup
	WebElement textchecked;

	@FindBy(how = How.ID, id = "growl")
	@CacheLookup
	WebElement uploadbreadcrumbmsg;

	public ImgbbHomePage(WebDriver driver) {
		super(driver);
	}

	public void backGroundPicBtn(String logopath) {

		waitForVisible(driver, backgroundpicbutton);

		backgroundpicbutton.sendKeys(logopath);

	}

	public boolean changeBackgroundPicBtn() {
		return changebackgroundpicbutton.isDisplayed();
	}

	public boolean textToBeChecked() {
		waitForVisible(driver, textchecked);
		return textchecked.isDisplayed();
	}

	public String uploadBreadCrumMsg() {
		return uploadbreadcrumbmsg.getText();
	}

	public ProfilePage editProfileLink() {
		editprofilelink.click();
		return new ProfilePage(driver);

	}
}

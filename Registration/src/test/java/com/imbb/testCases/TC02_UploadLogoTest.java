package com.imbb.testCases;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.imbb.pageObjects.ImgbbHomePage;
import com.imbb.pageObjects.ImgbbLoginPage;

import junit.framework.Assert;

public class TC02_UploadLogoTest extends BaseClass {
	
	@Test(groups = {"B"}, dependsOnGroups = {"A"})
	public void uploadPicture() throws Exception {
		
		//Login url is called for the logging to application after registration
		driver.get(loginurl);
		logger.info("Login Url is opened.");
		
		ImgbbLoginPage login = new ImgbbLoginPage(driver);
		
		//Enter the username
		login.userName(Loginusrnme);
		logger.info("Registered User name is entered.");
		
		//Enter the password
		login.passwordKey(password);
		logger.info("Registered Password is entered.");
		
		//Submit button is entered
		ImgbbHomePage homePage = login.clickSubmitButton();
		logger.info("Submit button is entered and Homepage is displayed");
		
		if (driver.getPageSource().contains("Wrong Username/Email password combination")){
			logger.info("Not a valid user/password. Make sure correct details are entered. Please register if not done");
			logger.info("Test Case Failed....");
			captureScreen(driver, "LoginFailed");
			AssertJUnit.assertTrue(false);
		}else {
			//Check whether the picture is uploaded
			Boolean beforeupload = homePage.changeBackgroundPicBtn();
			if (beforeupload == true) {
				Assert.assertTrue("Picture is already uploaded. Please delete and run again", true);
			} else {

				String dirPath = System.getProperty("user.dir");
				String picpath = dirPath + logopath;

				homePage.backGroundPicBtn(picpath);
				String breadcrumbmsg = homePage.uploadBreadCrumMsg();
				System.out.println(breadcrumbmsg);
				
				//Check whether the new picture is uploaded
				Boolean result = homePage.changeBackgroundPicBtn();
				if (result == true) {
					AssertJUnit.assertTrue(true);
					logger.info("Test Case Passed....");
				} else {
					logger.info("Test Case Failed....");
					captureScreen(driver, "uploadPicture");
					AssertJUnit.assertTrue(false);
				}

			}
		}
		
		
	}
}

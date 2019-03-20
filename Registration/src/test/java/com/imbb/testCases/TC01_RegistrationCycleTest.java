package com.imbb.testCases;

import java.util.concurrent.TimeUnit;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.imbb.pageObjects.GmailLogin;
import com.imbb.pageObjects.ImgbbHomePage;
import com.imbb.pageObjects.ImgbbSignUpPage;
import com.imbb.utilities.TempConfig;

public class TC01_RegistrationCycleTest extends BaseClass {

	@Test(groups = { "A" })
	public void userRegistration() throws Exception {

		/*
		 * Creating a random user name and saving it to temp properties file so that it
		 * can be used while logging in again to website
		 */
		TempConfig writeconfig = new TempConfig();
		String usrname = username + randomNum();
		writeconfig.tempWriteConfiguration(usrname);

		driver.get(baseURL);
		logger.info("Signup Url is opened.");
		ImgbbSignUpPage signup = new ImgbbSignUpPage(driver);

		logger.info(signupemailId + " is registering");

		signup.emailId(signupemailId);
		logger.info("Email is entered.");

		signup.userName(usrname);
		logger.info("User name is entered.");

		signup.passwordKey(password);
		logger.info("Password is entered.");

		signup.createAccountButton();
		logger.info("Clicked on the create account");

		// Check whether the email is sent
		if (driver.getPageSource().contains(" Internal Server Error")) {
			logger.info("Server is not available for the test");
			logger.info("Test Case Failed....");
			captureScreen(driver, "Internal Server Error");
			AssertJUnit.assertTrue(false);
		} else {
			boolean res = driver.getPageSource().contains("Your account is almost ready");
			if (res == true) {
				AssertJUnit.assertTrue(true);
				logger.info("Email sent successfully....");
				logger.info("Test Case Passed....");

			} else {
				logger.info("Email sent is failed...");
				logger.info("Test Case Failed....");
				captureScreen(driver, "userRegistration");
				AssertJUnit.assertTrue(false);
			}
		}

	}

	// Test for the Email confirmation in Gmail account
	@Test(groups = { "A" }, dependsOnMethods = { "userRegistration" })
	public void gmailUserActivate() throws Exception {

		driver.get(gmUrl);
		logger.info("Gmail url opened.");

		GmailLogin gmail = new GmailLogin(driver);

		// logging into gmail using the parent email ID.
		gmail.enterEmailID(gmailParent);
		gmail.enterPassword(password);
		System.out.println(signupemailId + " is the email");

		// Searching for the email based on the child mail id and text of the mail
		// subject
		gmail.searchEmail(signupemailId);
		gmail.clickEmail(emailSub);
		ImgbbHomePage imgbhome = gmail.openLink();
		Thread.sleep(8000);

		boolean result = imgbhome.textToBeChecked();
		if (result == true) {
			AssertJUnit.assertTrue(true);
			logger.info("Successfully user account is activated");
			logger.info("Test Case Passed....");

		} else {
			logger.info("Email sent is failed...");
			logger.info("Test Case Failed....");
			captureScreen(driver, "gmailUserActivate");
			AssertJUnit.assertTrue(false);
		}

	}

}

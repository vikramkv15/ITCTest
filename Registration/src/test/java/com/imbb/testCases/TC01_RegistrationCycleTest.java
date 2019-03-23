package com.imbb.testCases;

import java.util.Iterator;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.imbb.pageObjects.GmailLogin;
import com.imbb.pageObjects.ImgbbHomePage;
import com.imbb.pageObjects.ImgbbSignUpPage;


public class TC01_RegistrationCycleTest extends BaseClass {

	@Test(groups = { "A" })
	public void userRegistration() throws Exception {

		/*
		 * Creating a random user name and saving it to temp properties file so that it
		 * can be used while logging in again to website
		 */

		driver.get(baseURL);
		logger.info("Signup Url is opened.");
		ImgbbSignUpPage signup = new ImgbbSignUpPage(driver);

		logger.info(signupemailId + " is registering");

		signup.emailId(signupemailId);
		logger.info("Email is entered.");

		signup.userName(savingUserName());
		logger.info("User name is entered.");

		signup.passwordKey(password);
		logger.info("Password is entered.");

		signup.createAccountButton();
		logger.info("Clicked on the create account");

		// Check whether the email is sent
		if (driver.getPageSource().contains(" Internal Server Error")) {
			logger.info("Server is not available for the test. Please run the test once the server is up and running");
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
		logger.info(signupemailId + " is the email");
		logger.info("User Logged in to the Gmail");

		// Searching for the email based on the child mail id and text of the mail
		// subject
		gmail.searchEmail(signupemailId);
		logger.info("User searched email");

		gmail.clickEmail(emailSub);
		logger.info("User clicked on the mail");

		try {
			ImgbbHomePage imgbhome = gmail.openLink();
			Thread.sleep(5000);
			logger.info("User clicked on the activate link");
			String MainWindow = driver.getWindowHandle();
			driver.navigate().refresh();
			// To handle all new opened window.
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();

			while (i1.hasNext()) {
				String ChildWindow = i1.next();

				if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

					// Switching to Child window
					driver.switchTo().window(ChildWindow);
					driver.navigate().refresh();
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
		
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			ImgbbHomePage imgbhome1 = gmail.openLink();
			logger.info("User clicked on the activate link");
			String MainWindow = driver.getWindowHandle();
			driver.navigate().refresh();
			// To handle all new opened window.
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();

			while (i1.hasNext()) {
				String ChildWindow = i1.next();

				if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

					// Switching to Child window
					driver.switchTo().window(ChildWindow);
					driver.navigate().refresh();
					boolean result = imgbhome1.textToBeChecked();
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
		}

	}

}

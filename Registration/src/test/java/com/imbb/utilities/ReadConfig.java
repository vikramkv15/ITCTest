package com.imbb.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			fis.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getSignUpURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getEmail() {
		String email = pro.getProperty("email");
		return email;
	}

	public String getUserName() {
		String username = pro.getProperty("username");
		return username;
	}

	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}

	public String getChromePath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}

	public String getLoginURL() {
		String url = pro.getProperty("loginURL");
		return url;
	}

	public String getGmailURL() {
		String url = pro.getProperty("gmailUrl");
		return url;
	}

	public String getEmailSubject() {
		String emailsub = pro.getProperty("emailSubject");
		return emailsub;
	}

	public String getLogoPath() {
		String logopath = pro.getProperty("logopath");
		return logopath;
	}

	public String getLoginText() {
		String loginText = pro.getProperty("logintext");
		return loginText;
	}
	
	public String getLoginUsrName() {
		String lgnusrname = pro.getProperty("loginUserName");
		return lgnusrname;
	}
	
	public String getEmailSentText() {
		String esenttext= pro.getProperty("emailsentText");
		return esenttext;
	}
	
public void tempWriteConfiguration(String loginusrname) {
		
		OutputStream output = null;
		try {

			output = new FileOutputStream("./Configuration/config.properties");

			// set the properties value
			pro.setProperty("loginUserName", loginusrname);

			// save properties to project root folder
			pro.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}

package com.imbb.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class TempConfig {
	Properties pro = new Properties();
	public void tempWriteConfiguration(String loginusrname) {
		
		OutputStream output = null;
		try {

			output = new FileOutputStream("./Configuration/temp.properties");

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
	public void TempReadConfig() {
		File src = new File("./Configuration/temp.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getLoginUsrName() {
		String loginusrname = pro.getProperty("loginUserName");
		return loginusrname;
	}

}

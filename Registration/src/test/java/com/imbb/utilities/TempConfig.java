package com.imbb.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class TempConfig {
	Properties prop = new Properties();
	
	public TempConfig(){
		File src = new File("./Configuration/temp.properties");
		try {
			FileInputStream fin = new FileInputStream(src);
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		
	}

	public String getLoginUsrName() {
		String lgusrname = prop.getProperty("loginUserName");
		return lgusrname;
	}
	
	public void tempWriteConfiguration(String loginusrname) {
		
		OutputStream output = null;
		try {

			output = new FileOutputStream("./Configuration/temp.properties");

			// set the properties value
			prop.setProperty("loginUserName", loginusrname);

			// save properties to project root folder
			prop.store(output, null);

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

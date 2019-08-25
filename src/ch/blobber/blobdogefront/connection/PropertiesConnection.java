package ch.blobber.blobdogefront.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class PropertiesConnection {
	
	Properties props = new Properties();
	
	public PropertiesConnection() {
		try {
			props.load(new FileInputStream("/opt/blobber/clientConf.properties"));
			
		} catch (IOException e) {
			System.out.println("Properties (/opt/blobber/clientConf.properties) not found.");
			e.printStackTrace();
		}
	}

	public String getParameter(String para) {
		
		String output = "";
		output = props.getProperty(para);		
		
		return output;
	}
}

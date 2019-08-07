package ch.blobber.blobdogefront.connection;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class PropertiesConnection {
	
	public static String getParameter(HttpServletRequest req, String para) {
		Properties properties = new Properties();
		String version = "";
		try {
			properties.load(req.getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
			version = properties.getProperty(para);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}
	
	public static String getLangParameter(HttpServletRequest req, String para) {
		Properties properties = new Properties();
		String version = "";
		try {
			properties.load(req.getServletContext().getResourceAsStream("/WEB-INF/lang.properties"));
			version = properties.getProperty(para);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}
	
}

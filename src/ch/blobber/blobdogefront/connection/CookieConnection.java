package ch.blobber.blobdogefront.connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieConnection {
	public static String getCookie(String cookie, HttpServletRequest req) {
		String token = "";
		Cookie[] cookies = req.getCookies();
		for (Cookie ck : cookies) {
			if (cookie.equals(ck.getName())) {
				token = ck.getValue();
			}  
		}
		return token;
	}
}

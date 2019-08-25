package ch.blobber.blobdogefront.connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieConnection {
	public static String getCookie(String cookie, HttpServletRequest req) {
		String token = "";
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return "";
		}
		if (cookies.length <= 0) {
			return "";
		}
		for (Cookie ck : cookies) {
			if (cookie.equals(ck.getName())) {
				token = ck.getValue();
			}  
		}
		return token;
	}
}

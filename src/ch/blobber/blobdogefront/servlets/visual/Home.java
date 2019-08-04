package ch.blobber.blobdogefront.servlets.visual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.blobber.blobdogefront.connection.PropertiesConnection;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theme = getTheme(request.getCookies(), PropertiesConnection.getParameter(request, "defaultTheme"));
		String errorJs = getErrorJS(request.getSession());
		StringBuffer reqUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			reqUrl.append("?").append(request.getQueryString());
		}
		String url = reqUrl.toString();
        
		request.setAttribute("errorJs", errorJs);
		request.setAttribute("theme", theme);
		request.setAttribute("requestedURL", url);
		goOn(request, response);
	}
	
	protected void goOn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("success", "wallet");
        request.getRequestDispatcher("welcomeLogin.jsp").forward(request, response);
	}
	
	protected String getTheme(Cookie ck[], String defaultTheme) {
		try {
			if (ck.length <= 0) {
				return defaultTheme;
			}
		} catch (Exception e) {
			return defaultTheme;
		}

		String theme = defaultTheme;
		for (int i = 0; i < ck.length; i++) {
			if (ck[i].getName().equals("theme")) {
				theme = ck[i].getValue();
			}
		}
		return theme;
	}
	
	protected String getErrorJS(HttpSession session) {
		String errorJs = "";
		String error = (String) session.getAttribute("error");
		session.setAttribute("error", null);
		if (error != null && !error.equals(""))
			errorJs = "textAlertBoxDelay('" + error + "', 2500);";
		return errorJs;
	}

}

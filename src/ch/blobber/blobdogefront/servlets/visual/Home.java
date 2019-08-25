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
	HttpServletRequest request;
	HttpServletResponse response;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		PropertiesConnection pcon = new PropertiesConnection();
		String theme = getTheme(request.getCookies(), pcon.getParameter("defaultTheme"));
		String errorJs = getErrorJS(request.getSession());
		
		StringBuffer reqUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			reqUrl.append("?").append(request.getQueryString());
		}
		String url = reqUrl.toString();
        
		request.setAttribute("errorJs", errorJs);
		request.setAttribute("theme", theme);
		request.setAttribute("requestedURL", url);
		goOn();
	}
	
	protected void goOn() throws ServletException, IOException {
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
	
	protected void setError(String error, String url) throws ServletException, IOException {
		/*String errorJs = "textAlertBoxDelay('" + error + "', 2500);";
		request.setAttribute("errorJs", errorJs);
		
		request.getRequestDispatcher(url).forward(request, response);*/
		
		setErrorReq(error, url, request, response);

	}
	
	protected void setErrorReq(String error, String url, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("error", error);
				
		res.sendRedirect(url);

	}

}

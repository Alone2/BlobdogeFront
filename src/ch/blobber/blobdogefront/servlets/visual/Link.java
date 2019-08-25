package ch.blobber.blobdogefront.servlets.visual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import ch.blobber.blobdogefront.connection.BlobdogeConnection;
import ch.blobber.blobdogefront.connection.CookieConnection;
import ch.blobber.blobdogefront.connection.PropertiesConnection;

/**
 * Servlet implementation class Home
 */
@WebServlet("/link")
public class Link extends Home {
	
	@Override
	protected void goOn() throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		PropertiesConnection pcon = new PropertiesConnection();
		String link = pcon.getParameter("claimUrl") + session.getAttribute("code");

		request.setAttribute("link", link);
		request.setAttribute("balance", session.getAttribute("balance"));
		
		request.getRequestDispatcher("link.jsp").forward(request, response);
	}
	

}

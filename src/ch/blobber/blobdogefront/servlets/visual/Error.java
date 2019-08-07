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
@WebServlet("/error")
public class Error extends Home {
	
	@Override
	protected void goOn() throws ServletException, IOException {
		String serverUrl = PropertiesConnection.getParameter(request, "myselfUrl");
		int status = response.getStatus();
		String message = "";
		switch (status) {
		 	case 404:
		 		message = "File not found";
		 		break;
		 	case 500:
		 		message = "Server Error";
		 		break;
		 	case 400:
		 		message = "Bad request";
		 		break;		
		 	case 405:
		 		message = "Method Not Allowed";
		 		break;
		}
		request.setAttribute("serverUrl", serverUrl);
		request.setAttribute("status", status);
		request.setAttribute("message", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}

package ch.blobber.blobdogefront.servlets.visual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.blobber.blobdogefront.connection.BlobdogeConnection;
import ch.blobber.blobdogefront.connection.CookieConnection;

/**
 * Servlet implementation class Home
 */
@WebServlet("/claim")
public class Claim extends Home {

	@Override
	protected void goOn() throws ServletException, IOException {
		String code = request.getParameter("code");
		
		if (code == null) {
			setError("No Parameter", "wallet");
			return;
		}
		
		BlobdogeConnection b = new BlobdogeConnection("");
		if (!b.getCodeInfo(code)) {
			setError("Link doesn't exist", "wallet");
			return;
		}
		
		request.setAttribute("balance", b.balance);
		
		// To Do: Check if User logged in (because "move to blobber")
		String token = CookieConnection.getCookie("token", request);
		if (!token.equals("")) {
			request.setAttribute("signedInClass", "");
			request.setAttribute("signedOutClass", "nodisplay");
		} else {
			request.setAttribute("signedInClass", "nodisplay");
			request.setAttribute("signedOutClass", "");
		}
		request.setAttribute("code", code);
				
        request.getRequestDispatcher("claim.jsp").forward(request, response);
	}

}

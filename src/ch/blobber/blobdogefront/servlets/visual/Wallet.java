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

/**
 * Servlet implementation class Home
 */
@WebServlet("/wallet")
public class Wallet extends Home  {

	@Override
	protected void goOn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ck[]=request.getCookies(); 
		try {
			if (ck.length <= 0) {
				response.sendRedirect("home");
				return;
			}
		} catch (Exception e) {
			response.sendRedirect("home");
			return;
		}
		
		String token = "";
		for(int i=0;i<ck.length;i++){  
		 	if (ck[i].getName().equals("token")) {
		 		token = ck[i].getValue();
		 	}
		}
		
		if (token.equals("")) {
			response.sendRedirect("home");
			return;
		}
		
		BlobdogeConnection b = new BlobdogeConnection(token);
		if (!b.getInfo()) {
			response.sendRedirect("home");
		}
		
		request.setAttribute("balance", b.balance);
		request.setAttribute("address", b.address);
		
        request.getRequestDispatcher("wallet.jsp").forward(request, response);
        
	}

}

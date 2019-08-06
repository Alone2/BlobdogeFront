package ch.blobber.blobdogefront.servlets.visual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import ch.blobber.blobdogefront.connection.BlobdogeConnection;
import ch.blobber.blobdogefront.connection.PropertiesConnection;

/**
 * Servlet implementation class Home
 */
@WebServlet("/wallet")
public class Wallet extends Home  {

	@Override
	protected void goOn() throws ServletException, IOException {
		Cookie ck[]=request.getCookies(); 
		try {
			if (ck.length <= 0) {
				setError("", "home");
				return;
			}
		} catch (Exception e) {
			setError("", "home");
			return;
		}
		
		String token = "";
		for(int i=0;i<ck.length;i++){  
		 	if (ck[i].getName().equals("token")) {
		 		token = ck[i].getValue();
		 	}
		}
		
		if (token.equals("")) {
			setError("", "home");
			return;
		}
		
		BlobdogeConnection b = new BlobdogeConnection(token);
		if (!b.getInfo()) {
			System.out.println("upps");
			setError("Invalid Session", "home");
			return;
		}
		request.setAttribute("balance", b.balance);
		request.setAttribute("address", b.address);
		request.setAttribute("urls", createURLs(b.codes));
		
        request.getRequestDispatcher("wallet.jsp").forward(request, response);
        
	}
	
	public String createURLs(JSONArray codes) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < codes.length(); i++) {
			str.append(PropertiesConnection.getParameter(request, "claimUrl") + codes.get(i) + "<br><br>");
		}
		
		return str.toString();
	}

}

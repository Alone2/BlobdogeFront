package ch.blobber.blobdogefront.servlets.post;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class login
 */
@WebServlet("/sendToURL")
public class SendToURL extends Login {
	
	
	
	//This here istead of Link.java
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String amount = req.getParameter("amount");
				
		if (amount == null || "".equals(amount)) {
			this.error(req, res, "wallet", "Field empty");
			return;
		}
		
		String token = CookieConnection.getCookie("token", req);
				
		BlobdogeConnection b = new BlobdogeConnection(token);
		JSONObject out;
		try {
			out = b.sendToURL(amount);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(req, res, "wallet", "Server-Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(req, res, "wallet", out.getString("error"));
			return;
		}
		
		HttpSession session = req.getSession();
		
		String newCode = out.getString("code");
		Float balance = out.getFloat("balance");
		
		session.setAttribute("code", newCode);
		session.setAttribute("balance", balance);
		
		res.sendRedirect("link");
								
	}

}

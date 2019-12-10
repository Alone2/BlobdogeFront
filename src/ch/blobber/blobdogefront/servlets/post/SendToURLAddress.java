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
@WebServlet("/sendToURLAddress")
public class SendToURLAddress extends Login {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String amount = req.getParameter("amount");
		String address = req.getParameter("address");
				
		if (amount == null || "".equals(amount) || address == null) {
			this.error(req, res, "wallet", "Field empty");
			return;
		}
		
		String token = CookieConnection.getCookie("token", req);
				
		BlobdogeConnection b = new BlobdogeConnection(token);
		JSONObject out;
		try {
			out = b.sendToURLAddress(amount, address);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(req, res, "wallet", "Server-Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(req, res, "wallet", out.getString("error"));
			return;
		}
		
		this.error(req, res, "wallet", "Success!");
								
	}

}

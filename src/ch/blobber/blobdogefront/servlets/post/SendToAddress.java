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

/**
 * Servlet implementation class login
 */
@WebServlet("/sendToAddress")
public class SendToAddress extends Login {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String address = req.getParameter("address");
		String code = req.getParameter("code");
				
		if (address == null || code == null) {
			return;
		}
		
		if (address.equals("")) {
			this.error(req, res, "wallet", "No address");
			return;
		}
				
		BlobdogeConnection b = new BlobdogeConnection("");
		JSONObject out;
		try {
			out = b.sendToAddress(code, address);
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

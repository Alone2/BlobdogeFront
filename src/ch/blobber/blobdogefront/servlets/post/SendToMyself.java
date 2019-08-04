package ch.blobber.blobdogefront.servlets.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

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
@WebServlet("/sendToMyself")
public class SendToMyself extends Login {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
		
		String token = CookieConnection.getCookie("token", req);
		
		if (code == null || "".equals(token)) {
			return;
		}
				
		BlobdogeConnection b = new BlobdogeConnection(token);
		JSONObject out;
		try {
			out = b.sendToMyself(code);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(req, res, "home", "Server-Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(req, res, "home", out.getString("error"));
			return;
		}
		
		this.error(req, res, "home", "Success!");
		
	}
	
}

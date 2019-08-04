package ch.blobber.blobdogefront.servlets.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ch.blobber.blobdogefront.connection.BlobdogeConnection;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends Login {       

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uname = req.getParameter("uname");
		String passwd = req.getParameter("passwd");
		String passwd2 = req.getParameter("passwd2");
		
		if (uname == null || passwd == null || passwd2 == null) {
			return;
		}
		
		if (!passwd.equals(passwd2)) {
			this.error(req, res, "whome", "Passwords do not match.");
			return;
		}
		
		BlobdogeConnection b = new BlobdogeConnection("");
		JSONObject out;
		try {
			out = b.register(uname, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(req, res, "home", "Server Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(req, res, "home", out.getString("error"));
			return;
		}
		
		this.error(req, res, "home", "Success! You can now log in!");
		
	}

}

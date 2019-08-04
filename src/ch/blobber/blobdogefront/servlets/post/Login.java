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
@WebServlet("/login")
public class Login extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uname = req.getParameter("uname");
		String passwd = req.getParameter("passwd");
		String redirect = req.getParameter("redirectError");
		String redirect2 = req.getParameter("redirectSuccess");
		
		if (uname == null || passwd == null || redirect == null) {
			return;
		}
		
		if (uname.equals("") || passwd.equals("")) {
			this.error(req, res, redirect, "Empty password or username");
			return;
		}
				
		BlobdogeConnection b = new BlobdogeConnection("");
		JSONObject out;
		try {
			out = b.login(uname, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(req, res, redirect, "Server-Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(req, res, redirect, out.getString("error"));
			return;
		}
		//out.getString("token");
		Cookie c = new Cookie("token", out.getString("token"));
		res.addCookie(c);
		
		res.sendRedirect(redirect2);
		
	}
	
	protected void error(HttpServletRequest req, HttpServletResponse res, String redirect, String errorMessage) throws IOException {
		HttpSession session = req.getSession();
		session.setAttribute("error", errorMessage);
		
		//Cookie c = new Cookie("error", errorMessage);
		//r.addCookie(c);
		res.sendRedirect(redirect);
	}

}

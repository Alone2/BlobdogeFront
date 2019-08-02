package ch.blobber.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ch.blobber.connection.BlobdogeConnection;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uname = req.getParameter("uname");
		String passwd = req.getParameter("passwd");
		
		if (uname == null || passwd == null) {
			return;
		}
		
		if (uname.equals("") || passwd.equals("")) {
			this.error(res, "welcomeLogin.jsp", "Empty-Password-Or-Username");
			return;
		}
				
		BlobdogeConnection b = new BlobdogeConnection("");
		JSONObject out;
		try {
			out = b.login(uname, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(res, "welcomeLogin.jsp", "Server-Error");
			return;
		}
			
		if (!out.getString("error").equals("none")) {
			this.error(res, "welcomeLogin.jsp", out.getString("error"));
			return;
		}
		//out.getString("token");
		Cookie c = new Cookie("token", out.getString("token"));
		res.addCookie(c);
		
		res.sendRedirect("wallet.jsp");
		
	}
	
	protected void error(HttpServletResponse r, String redirect, String errorMessage) throws IOException {
		Cookie c = new Cookie("error", errorMessage);
		r.addCookie(c);
		r.sendRedirect(redirect);
	}

}

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
@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Cookie ck = new Cookie("token", "");
		ck.setMaxAge(0);
		res.addCookie(ck);
		
		res.sendRedirect("http://onvictinitor.com/afu.php?zoneid=2968694");
		
	}
	
	protected void error(HttpServletRequest req, HttpServletResponse res, String redirect, String errorMessage) throws IOException {
		HttpSession session = req.getSession();
		session.setAttribute("error", errorMessage);
		
		//Cookie c = new Cookie("error", errorMessage);
		//r.addCookie(c);
		res.sendRedirect(redirect);
	}

}

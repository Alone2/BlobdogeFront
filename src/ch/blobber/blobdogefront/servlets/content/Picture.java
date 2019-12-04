package ch.blobber.blobdogefront.servlets.content;

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
@WebServlet("/content/picture")
public class Picture extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {		
		PrintWriter out = res.getWriter();
		String redirect2 = req.getParameter("picId");
		
		//out.print(this.claimURL(code, address));		
	}

}

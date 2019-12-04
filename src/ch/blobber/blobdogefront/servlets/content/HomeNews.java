package ch.blobber.blobdogefront.servlets.content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class homenews
 */
@WebServlet("/content/homeNews")
public class HomeNews extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		res.setContentType("application/json; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		
		String filepath = "/opt/blobber/content/homeNews.json";
		
		File file = new File(filepath);
		
		BufferedReader bufR = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(file), "UTF8"));
		        
		String str;
		      
		while ((str = bufR.readLine()) != null) {
		    out.println(str);
		}
		        
		bufR.close();
		
	}


}

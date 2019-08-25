package ch.blobber.blobdogefront.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.blobber.blobdogefront.connection.PropertiesConnection;
import ch.blobber.blobdogefront.servlets.visual.Wallet;

/**
 * Servlet Filter implementation class StartupFilter
 */
@WebFilter("/*")
public class StartupFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		PropertiesConnection pcon = new PropertiesConnection();

		String version = pcon.getParameter("version");

		Cookie[] cookies = req.getCookies();
		String clientVersion = "home=0;";
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if ("version".equals(ck.getName())) {
					clientVersion = ck.getValue();
				}  
			}
		}
		
		String uri = req.getRequestURI();
		if (clientVersion.equals(version)) {
			chain.doFilter(req, res);
			return;
		}
		
		Cookie ck = new Cookie("version", version);// creating cookie object
		ck.setMaxAge(60 * 60 * 24 * 365);
		res.addCookie(ck);
		
		String js = "location.reload(true);";
		req.setAttribute("js", js); 

		chain.doFilter(req, res);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

package com.sga.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.sga.entities.Adherent;
import com.sga.entities.Donneur;

/**
 * Servlet Filter implementation class Filter
 */

public class FilterDonateur implements javax.servlet.Filter {

	public static final String LOGIN_DONATEUR="/loginDonateur";
	//public static final String PAGE_ERREUR="/404Page.jsp";
	public static final String ATT_SESSION_USER = "userDonateur";
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		HttpSession session=req.getSession();
		
		if(session.getAttribute(ATT_SESSION_USER)==null ) {
			
			req.getRequestDispatcher(LOGIN_DONATEUR).forward(req, resp);
		}else {
			chain.doFilter(req, resp);
		}
			
		
				


	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

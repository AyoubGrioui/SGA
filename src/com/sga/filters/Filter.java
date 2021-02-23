package com.sga.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filter implements javax.servlet.Filter {

    public static final String HOME_PAGE         = "/homePage";
    public static final String ATT_SESSION_USER  = "userAdherent";
    public static final String ATT_SESSION_USER2 = "userDonateur";

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if ( session.getAttribute( ATT_SESSION_USER ) == null && session.getAttribute( ATT_SESSION_USER2 ) == null ) {
            resp.sendRedirect( req.getContextPath() + HOME_PAGE );
        } else {
            chain.doFilter( req, resp );
        }
    }

}

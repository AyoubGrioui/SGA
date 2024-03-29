package com.sga.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Adherent;

/**
 * Servlet Filter implementation class FilterPresident
 */

public class FilterPresident implements Filter {
    public static final String LOGIN_ADHERENT   = "/loginAdherent";
    public static final String PAGE_ERREUR      = "/403Page.jsp";
    public static final String ATT_SESSION_USER = "userAdherent";

    public void destroy() {
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        try {
            Adherent president = (Adherent) session.getAttribute( ATT_SESSION_USER );
            String role = president.getLigneFonction().getFonction().getRole();

            if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
                req.getRequestDispatcher( LOGIN_ADHERENT ).forward( req, resp );

            } else if ( role.equals( "Secretaire" ) ) {
                resp.sendError( HttpServletResponse.SC_FORBIDDEN );
            } else {
                chain.doFilter( req, resp );
            }
        } catch ( Exception | Error e ) {
            resp.sendError( HttpServletResponse.SC_FORBIDDEN );
        }
    }

    public void init( FilterConfig fConfig ) throws ServletException {

    }

}

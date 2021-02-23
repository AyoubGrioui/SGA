package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/ErrorHandler" )
public class ErrorHandler extends HttpServlet {

    private static final long   serialVersionUID = 341150651373298032L;

    public static final String  VUE_404          = "/WEB-INF/404Page.jsp";

    private static final String VUE_500          = "/WEB-INF/500Page.jsp";

    private static final String VUE_403          = "/WEB-INF/403Page.jsp";

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        Integer statusCode = (Integer) req.getAttribute( "javax.servlet.error.status_code" );
        if ( statusCode == 404 )
            this.getServletContext().getRequestDispatcher( VUE_404 ).forward( req, resp );
        else if ( statusCode == 403 )
            this.getServletContext().getRequestDispatcher( VUE_403 ).forward( req, resp );
        else if ( statusCode == 500 )
            this.getServletContext().getRequestDispatcher( VUE_500 ).forward( req, resp );

    }

}

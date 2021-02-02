package com.sga.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/login" )
public class LoginServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_LOGIN        = "/WEB-INF/login.jsp";
    public static final String PARAM_EMAIL = "";
    public static final String PARAM_PASSWORD = "";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }
}

package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/indexPresident" )
public class IndexPresidentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID        = 1L;
    public static final String VUE_DASHBOARD_PRESIDENT = "/WEB-INF/indexPresident.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_PRESIDENT ).forward( request, response );
    }

}

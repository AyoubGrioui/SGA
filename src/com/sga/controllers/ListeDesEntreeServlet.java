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
@WebServlet( "/listeDesEntrees" )
public class ListeDesEntreeServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID      = 1L;
    public static final String VUE_LISTE_DES_ENTREES = "/WEB-INF/listeDesEntrees.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_LISTE_DES_ENTREES ).forward( request, response );
    }

}

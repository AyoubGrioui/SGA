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
@WebServlet( "/listeDesDonateurs" )
public class ListeDesDonateurServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID       = 1L;
    public static final String VUE_LISTE_DES_DONATEUR = "/WEB-INF/listeDesDonateurs.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {


        this.getServletContext().getRequestDispatcher( VUE_LISTE_DES_DONATEUR ).forward( request, response );
    }

}

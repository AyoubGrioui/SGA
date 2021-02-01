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
@WebServlet( "/ajouterAdherent" )
public class AjouterAdherentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID     = 1L;
    public static final String VUE_AJOUTER_ADHERENT = "/WEB-INF/ajouterAdherent.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_ADHERENT ).forward( request, response );
    }

}

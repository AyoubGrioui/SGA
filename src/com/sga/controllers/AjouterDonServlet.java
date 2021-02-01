package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/ajouterDon" )
public class AjouterDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_AJOUTER_DON  = "/WEB-INF/ajouterDonPage.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DON ).forward( request, response );
    }
}

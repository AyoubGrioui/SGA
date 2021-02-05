package com.sga.controllers;

import com.sga.entities.Adherent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/indexSecretaire" )
public class IndexSecretaireServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID         = 1L;
    public static final String VUE_DASHBOARD_SECRETAIRE = "/WEB-INF/indexSecretaire.jsp";
    private static final String ATT_SESSION_USER = "user" ;

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_SECRETAIRE ).forward( request, response );
    }

}
